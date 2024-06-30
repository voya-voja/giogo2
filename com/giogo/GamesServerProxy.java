/*
 * GameServerProxy.java
 *
 * Created on April 2, 2004, 4:43 PM
 */

package com.giogo;

import com.inexum.IPC.Client;

import java.util.Date;
import java.util.Map;

/**
 *
 * @author  nvojinov
 */
public class GamesServerProxy implements IGamesServer
{
    private int mId = 0;
    private GamesServerInfo mServerInfo;
    private int mNumberOfGames = 0;
    private Client  mClient = new Client();
    
    /** Creates a new instance of GameServerProxy */
    public GamesServerProxy(int id, GamesServerInfo serverInfo) 
    {
        if(com.inexum.util.Debug.on)
          com.inexum.util.Debug.out(serverInfo.name() + ": " + id);
        mId = id;
        mServerInfo = serverInfo;
        try
        {
//            mClient.connect(serverInfo.host(), serverInfo.port());
            mClient.connect("localhost", serverInfo.port());
        }
        catch(java.io.IOException e)
        {
            e.printStackTrace();
            if(com.inexum.util.Debug.on)
                com.inexum.util.Debug.out(e);
        }
    }
    
    public GamesServerInfo info() { return(mServerInfo); }
    
    public int numberOfGames()
    {
        return(mNumberOfGames);
    }
        
    public GameInfo initiateGame(Map parameters) throws Exception
    {
        String gameType = (String)parameters.get("type");
        String id = Integer.toHexString(new Date().hashCode()) + "_" + 
                          Integer.toHexString(gameType.hashCode()) + "_" + 
                          Integer.toHexString(mId) + "_" + 
                          Integer.toHexString(mNumberOfGames);
        parameters.put("id", id);
        if(com.inexum.util.Debug.on)
          com.inexum.util.Debug.out(id);
        GameInfo gameInfo = null;
        try
        {
            gameInfo = (GameInfo)mClient.execute(new InitiateGame(parameters));
            mNumberOfGames++;
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
        return(gameInfo);
    }
    
    public GameParticipantsInfo participantsFor(String gameId) throws Exception
    {
        if(com.inexum.util.Debug.on)
          com.inexum.util.Debug.out(gameId);
        GameInfo gameInfo = null;
        try
        {
            GameParticipantsInfo participants = (GameParticipantsInfo)
                    mClient.execute(new GetGameParticipantsFromGamesServer(gameId));
            return(participants);
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
        return(null);
    }
    
}
