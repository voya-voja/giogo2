/*
 * Giogo.java
 *
 * Created on Jun 03, 2003, 11:01 PM
 */

package com.giogo;

import com.inexum.IPC.Server;
import com.inexum.IPC.ServiceRegistry;
import com.inexum.util.ConfigurationManager;

import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author  Giogo
 * @version 1.0
 */

public class GiogoServer implements IGiogo, IGiogoServer
{
    final static ConfigurationManager c_rsrcs = ConfigurationManager.Instance();

    private Map     mGamesServers = new HashMap();
    private Map     mSupportedGameSpecs = new HashMap();
    private Map     mUnderwayGames = new HashMap();
    private Map     mGamesAndServers = new HashMap();

    private Server mServer = new Server();
    
    static GiogoServer gmInstance;
    
    public static GiogoServer Instance()
    {
        if(gmInstance == null)
        {
            gmInstance = new GiogoServer();
        }
        return(gmInstance);
    }

    private GiogoServer()
    {
    }

    protected GamesServerProxy gameServer(String serverName)
    {
        if(com.inexum.util.Debug.on)
          com.inexum.util.Debug.out(serverName);
        if(mSupportedGameSpecs.containsKey(serverName))
        {
            return((GamesServerProxy)mGamesServers.get(serverName));
        }
        return(null);
    }

    protected GamesServerProxy gameServer(GamesServerInfo serverInfo)
    {
        if(com.inexum.util.Debug.on)
          com.inexum.util.Debug.out(serverInfo.name());
        String serverName = serverInfo.name();
        if(serverName == null)
        {
            serverInfo.name(newGameServerName());
        }
        GamesServerProxy gameServer = gameServer(serverName);
        if(gameServer == null)
        {
            gameServer = new GamesServerProxy(mGamesServers.size(), serverInfo);
            mGamesServers.put(serverName, gameServer);
        }
        return(gameServer);
    }

    public void update(GamesServerInfo serverInfo, String[] supportedGames)
    {
        if(com.inexum.util.Debug.on)
          com.inexum.util.Debug.out(serverInfo.name());
        String serverName = serverInfo.name();
        GamesServerProxy gameServer = gameServer(serverInfo);
        for(int gCount = 0; gCount < supportedGames.length; gCount++)
        {
            String gameName = supportedGames[gCount];
            if(mSupportedGameSpecs.containsKey(gameName))
            {
                List supportingServers = (List)mSupportedGameSpecs.get(gameName);
                supportingServers.add(gameServer);
            }
            else
            {
                ArrayList supportingServers = new ArrayList();
                supportingServers.add(gameServer);
                mSupportedGameSpecs.put(gameName, supportingServers);
            }
        }
    }

    public void add(GameInfo gameInfo, GamesServerProxy server)
    {
        if(com.inexum.util.Debug.on)
          com.inexum.util.Debug.out(gameInfo.id());
        mUnderwayGames.put(gameInfo.id(), gameInfo);
        mGamesAndServers.put(gameInfo.id(), server);
    }
    
    public void update(GameInfo gameInfo)
    {
        if(com.inexum.util.Debug.on)
          com.inexum.util.Debug.out(gameInfo.id());
        mUnderwayGames.put(gameInfo.id(), gameInfo);
    }
    
    public void remove(GameInfo gameInfo)
    {
        if(com.inexum.util.Debug.on)
          com.inexum.util.Debug.out(gameInfo.id());
        mUnderwayGames.remove(gameInfo.id());
        mGamesAndServers.remove(gameInfo.id());
    }
    
    public GamesServerInfo[] gamesServers()
    {
        if(com.inexum.util.Debug.on)
          com.inexum.util.Debug.out();
        return((GamesServerInfo[])mGamesServers.values().toArray());
    }

    public GameSpecification[] gameSpecifications()
    {
        if(com.inexum.util.Debug.on)
          com.inexum.util.Debug.out();
        return((GameSpecification[])mSupportedGameSpecs.values().toArray());
    }

    public GameInfo[] underwayGames(String gameName) throws Exception
    {
        if(com.inexum.util.Debug.on)
          com.inexum.util.Debug.out(gameName);
        GameInfo[] example = {};
        return((GameInfo[])mUnderwayGames.values().toArray(example));
    }

    public String startGame(Map parameters) throws Exception
    {
        String gameType = (String)parameters.get("type");
        if(com.inexum.util.Debug.on)
          com.inexum.util.Debug.out(gameType);
        List supportingServers = (List)mSupportedGameSpecs.get(gameType);
        Iterator at = supportingServers.iterator();
        GamesServerProxy executor = null;
        int minGameNo = 0x7fffffff;
        
        String gameId = null;
        while(at.hasNext())
        {
            GamesServerProxy server = (GamesServerProxy)at.next();
            int gameNo = server.numberOfGames();
            if(executor == null || minGameNo > gameNo)
            {
                executor = server;
                minGameNo = gameNo;
            }
        }
        if(executor != null)
        {
            GameInfo game = executor.initiateGame(parameters);
            add(game, executor);
            gameId = game.id();
        }
        return(gameId);
    }
    
    public String newGameServerName() 
    {
        synchronized(mGamesServers)
        {
            String name = Integer.toString(mGamesServers.size());
            if(com.inexum.util.Debug.on)
              com.inexum.util.Debug.out(name);
            mGamesServers.put(name, null);
            return(name);
        }
    }

    public void start() throws java.io.IOException
    {
        int port = 10101;
        try
        {
            port = Integer.parseInt(c_rsrcs.getString("giogo.server.port"));
        }
        catch(java.util.MissingResourceException e){}
        catch(Exception e)
        {
            e.printStackTrace();
            if(com.inexum.util.Debug.on)
                com.inexum.util.Debug.out(e);
        }
        if(com.inexum.util.Debug.on)
          com.inexum.util.Debug.out("" + port);
        mServer.open(port);
        mServer.start();
        
        ServiceRegistry registry = ServiceRegistry.getDefaultInstance();
        registry.setServiceForName(this, "com.giogo.IGiogo");
        registry.setServiceForName(this, "com.giogo.IGiogoServer");
    }
    
    public GamesServerInfo getGamesServerInfo(String gameId) throws Exception
    {
        if(com.inexum.util.Debug.on)
          com.inexum.util.Debug.out(gameId);
        GamesServerProxy executor = (GamesServerProxy)mGamesAndServers.get(gameId);
        return(executor.info());
    }
    
    public GameParticipantsInfo gameParticipants(String gameId) throws Exception 
    {
        if(com.inexum.util.Debug.on)
          com.inexum.util.Debug.out(gameId);
        GamesServerProxy executor = (GamesServerProxy)mGamesAndServers.get(gameId);
        return(executor.participantsFor(gameId));
    }
    
    public static void main(String[] args)
    {
        if(com.inexum.util.Debug.on)
          com.inexum.util.Debug.addMode(com.inexum.util.Debug.g_all);

        GiogoServer giogo = GiogoServer.Instance();
        try
        {
            giogo.start();
        }
        catch(java.io.IOException e)
        {
            e.printStackTrace();
            if(com.inexum.util.Debug.on)
                com.inexum.util.Debug.out(e);
        }
    }
    
}
