/*
 * GamesServer.java
 *
 * Created on Jun 03, 2003, 11:01 PM
 */

package com.giogo.game;

import com.giogo.Service;
import com.giogo.GameInfo;
import com.giogo.IGamesServer;
import com.giogo.GiogoGameLogon;
import com.giogo.GameParticipantsInfo;
import com.inexum.IPC.Client;
import com.inexum.IPC.IOperation;
import com.inexum.IPC.ServiceRegistry;
import com.inexum.util.ConfigurationManager;

import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import java.net.URLClassLoader;
import java.net.InetAddress;
import java.rmi.RMISecurityManager;

/**
 *
 * @author  Giogo
 * @version 1.0
 */

public class GamesServer implements IGamesServer
{
    final static ConfigurationManager c_rsrcs = ConfigurationManager.Instance();
    private static GamesServer    gmInstance;

    private Map             mRegisteredGameSpecs = new HashMap();
    private ListeningServer mServer = new ListeningServer();
    private Client          mGiogoClient = new Client();
    private int             mLastUsedGamePort = 13000;
    private String          mServerName;

    public static GamesServer Instance()
    {
        if(gmInstance == null)
        {
            gmInstance = new GamesServer();
            gmInstance.initialize();
        }
        return(gmInstance);
    }

    public void initialize()
    {
        loadGameSpecs();
        startServer();
        logon();
    }
    
    protected GamesServer()
    {
    }

    protected void startServer()
    {
        try
        {
            mLastUsedGamePort = Integer.parseInt(
                                    c_rsrcs.getString("game.server.port"));
        }
        catch(java.util.MissingResourceException e){}
        catch(Exception e)
        {
            e.printStackTrace();
            if(com.inexum.util.Debug.on)
                com.inexum.util.Debug.out(e);
        }
        while(true)
        {
            try
            {
                mServer.open(mLastUsedGamePort);
                mServer.start();
                mServer.setName("Game Server");

                if(com.inexum.util.Debug.on)
                    com.inexum.util.Debug.out("Listenning on port '" + mLastUsedGamePort + "'");
                break;
            }
            catch(java.io.IOException e)
            {
                if(com.inexum.util.Debug.on)
                    com.inexum.util.Debug.out("Port '" + mLastUsedGamePort + "' is busy!");
                mLastUsedGamePort++;
            }
        }
        ServiceRegistry registry = ServiceRegistry.getDefaultInstance();
        registry.setServiceForName(this, "com.giogo.IGamesServer");
    }

    public void logon()
    {
        String gameHost = "localhost";
        
        String giogoHost = gameHost;
        int giogoPort    = 10101;
        try
        {
            try
	    {
            	giogoPort = Integer.parseInt(
                                    c_rsrcs.getString("giogo.server.port"));
            }
            catch(java.util.MissingResourceException e){}
            try
	    {
            	giogoHost = c_rsrcs.getString("giogo.server.host");
            }
            catch(java.util.MissingResourceException e){}
            try
	    {
            	gameHost = InetAddress.getLocalHost().getHostName();
            	gameHost = c_rsrcs.getString("game.server.host");
            }
            catch(java.util.MissingResourceException e){}
	}
        catch(Exception e)
        {
            e.printStackTrace();
            if(com.inexum.util.Debug.on)
                com.inexum.util.Debug.out(e);
        }
        if(com.inexum.util.Debug.on)
          com.inexum.util.Debug.out("connecting to: '" + giogoHost + "@" +
                                    giogoPort + "' as '" +
                                    gameHost + "@" + mLastUsedGamePort + "'");
        try
        {
            mGiogoClient.connect(giogoHost, giogoPort);
            mServerName = (String)mGiogoClient.execute(
                                    new GiogoGameLogon(gameHost, mLastUsedGamePort,
                                                                registeredGames()));
        }
        catch(java.io.IOException e)
        {
            e.printStackTrace();
            if(com.inexum.util.Debug.on)
                com.inexum.util.Debug.out(e);
        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
            if(com.inexum.util.Debug.on)
                com.inexum.util.Debug.out(e);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            if(com.inexum.util.Debug.on)
                com.inexum.util.Debug.out(e);
        }
    }
    
    public String[] registeredGames()
    {
        String[] games = new String[mRegisteredGameSpecs.size()];
        Iterator at = mRegisteredGameSpecs.values().iterator();
        int index = 0;
        while(at.hasNext())
        {
            games[index] = ((Specification)at.next()).name();
            index++;
        }
        return(games);
    }
    
    public void register(Specification game) throws ClassCastException,
                                                IllegalArgumentException,
                                                NullPointerException
    {
        try
        {
            mRegisteredGameSpecs.put(game.name(), game);
        }
        catch(UnsupportedOperationException e)
        {
            e.printStackTrace();
            if(com.inexum.util.Debug.on)
                com.inexum.util.Debug.out(e);
        }
    }

    public boolean isRegistered(String gameName)
                                                throws ClassCastException, 
                                                        NullPointerException
    {
        return(mRegisteredGameSpecs.containsKey(gameName));
    }

    public void unregister(String gameName)
                                                throws ClassCastException, 
                                                        NullPointerException
    {
        try
        {
             mRegisteredGameSpecs.remove(gameName);
        }
        catch(UnsupportedOperationException e)
        {
            e.printStackTrace();
            if(com.inexum.util.Debug.on)
                com.inexum.util.Debug.out(e);
        }
    }

    public GameInfo initiateGame(Map parameters) throws Exception
    {
        String gameType = (String)parameters.get("type");
        Specification gameSpec = 
                            (Specification)mRegisteredGameSpecs.get(gameType);
        return(gameSpec.initiate(parameters));
    }

    public GameParticipantsInfo participantsFor(String gameId) throws Exception 
    {
        Game game = GamesManager.Instance().getGame(gameId);
        String[] players = new String[game.getPlayers().size()];
        int count = 0;
        Iterator at = game.getPlayers().iterator();
        while(at.hasNext())
        {
            Listener participant = (Listener)at.next();
            players[count] = participant.alias();
            if(!participant.isActive())
                players[count] += "(inactive)";
            count++;
        }

        String[] spectators = new String[game.getSpectators().size()];
        count = 0;
        at = game.getSpectators().iterator();
        while(at.hasNext())
        {
            Listener participant = (Listener)at.next();
            if(participant.isActive())
                spectators[count++] = participant.alias();
        }
        return(new GameParticipantsInfo(players, spectators));
    }

    public void loadGameSpecs()
    {
        mRegisteredGameSpecs.clear();
        URLClassLoader classLoader = (URLClassLoader)this.getClass().getClassLoader();
        
        String[] gameSpecs;
        try
        {
            gameSpecs = c_rsrcs.getStringArray("game.specifications");
        }
        catch(Exception e)
        {
            gameSpecs = c_rsrcs.getArrayFromString("game.specifications");
        }           
        for(int gCount = 0; gCount < gameSpecs.length; gCount++)
        {
            try
            {
                Class gameClass = classLoader.loadClass(gameSpecs[gCount]);
                Specification gameSpec = (Specification)gameClass.newInstance();
                register(gameSpec);
            }
            catch(ClassNotFoundException e)
            {
                e.printStackTrace();
                if(com.inexum.util.Debug.on)
                    com.inexum.util.Debug.out(e);
            }
            catch(InstantiationException e)
            {
                e.printStackTrace();
                if(com.inexum.util.Debug.on)
                    com.inexum.util.Debug.out(e);
            }
            catch(IllegalAccessException e)
            {
                e.printStackTrace();
                if(com.inexum.util.Debug.on)
                    com.inexum.util.Debug.out(e);
            }
        }
    }

    public void giogoUpdate(IOperation operation) throws Exception
    {
        synchronized(mGiogoClient)
        {
            mGiogoClient.execute(operation);
        }
    }
    
    public static void main(String[] args)
    {

        if(com.inexum.util.Debug.on)
          com.inexum.util.Debug.addMode(com.inexum.util.Debug.g_all);

        GamesServer server = GamesServer.Instance();
          
        try
        {
            System.setSecurityManager(new RMISecurityManager());
            Service.register(new NextActionService());
            Service.register(new com.giogo.game.card.CardGameLoginService());
            Service.register(new com.giogo.game.bidding.SelectedBidService());
            Service.register(new com.giogo.game.card.preference.PreferenceSelectedBidService());
            Service.register(new com.giogo.game.card.SelectedCardsService());
            Service.register(new com.giogo.game.card.TricksUpdateService());
        }
        catch(Exception e)
        {
            e.printStackTrace();
            if(com.inexum.util.Debug.on)
                com.inexum.util.Debug.out(e);
        }
    }
}
