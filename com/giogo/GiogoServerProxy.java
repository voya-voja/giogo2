/*
 * GiogoServerProxy.java
 *
 * Created on April 8, 2004, 6:59 PM
 */

package com.giogo;

import com.inexum.IPC.IFunction;
import com.inexum.IPC.Client;

import java.util.Map;

/**
 *
 * @author  nvojinov
 */
public class GiogoServerProxy implements IGiogo
{
    static private GiogoServerProxy gmInstance;
    
    static public GiogoServerProxy Instance()
    {
        if(gmInstance == null)
            gmInstance = new GiogoServerProxy();
        return(gmInstance);
    }
    
    Client mClient = new Client("localhost",10101);
    
    /** Creates a new instance of GiogoServerProxy */
    private GiogoServerProxy() 
    {
        try
        {
            mClient.connect();
        }
        catch(java.io.IOException e)
        {
            e.printStackTrace();
        }
    }
    

    public GameInfo[] underwayGames(String gameName) throws Exception
    {
        try
        {
            GameInfo[] gameInfos = (GameInfo[])mClient.execute(new GetListOfGames(gameName));
            return(gameInfos);
        }
        catch(ClassCastException e)
        {
            e.printStackTrace();
        }
        return(null);
    }
    
    public GamesServerInfo getGamesServerInfo(String gameId) throws Exception
    {
        try
        {
            GamesServerInfo serverInfo = (GamesServerInfo)mClient.execute(new GetGamesServerInfo(gameId));
            return(serverInfo);
        }
        catch(ClassCastException e)
        {
            e.printStackTrace();
        }
        return(null);
    }
    
    public String startGame(Map parameters) throws Exception 
    {
        try
        {
            String gameId = 
                (String)mClient.execute(new StartGame(parameters));
            return(gameId);
        }
        catch(ClassCastException e)
        {
            e.printStackTrace();
        }
        return(null);
    }
    
    public GameParticipantsInfo gameParticipants(String gameId) throws Exception 
    {
        try
        {
            GameParticipantsInfo participants = 
                (GameParticipantsInfo)mClient.execute(new GetGameParticipants(gameId));
            return(participants);
        }
        catch(ClassCastException e)
        {
            e.printStackTrace();
        }
        return(null);
    }
    
}
