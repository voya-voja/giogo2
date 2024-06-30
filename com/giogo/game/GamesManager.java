/*
 * GamesManager.java
 *
 * Created on June 8, 2003, 7:33 PM
 */

package com.giogo.game;

import com.giogo.GameInfoUpdate;
import com.giogo.RemoveGameInfo;

import java.util.Map;
import java.util.HashMap;

/**
 *
 * @author  inexum
 */
public class GamesManager 
{
    private Map mGames = new HashMap();
    
    private static GamesManager gmGamesManager;
    
    public static GamesManager Instance() 
    {
        if(gmGamesManager == null)
            gmGamesManager = new GamesManager();
        return(gmGamesManager);
    }
    
    public void add(Game game)
    {
        mGames.put(game.id(), game);
    }
    
    public Game getGame(String gameId)
    {
        return((Game)mGames.get(gameId));
    }

    public int currentGameNo() { return(mGames.size()); }
    
    public void changeOf(String gameId)
    {
        try
        {
            Game game = getGame(gameId);
            GamesServer.Instance().giogoUpdate(new GameInfoUpdate(game.info()));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void ended(Game game)
    {
        try
        {
            GamesServer.Instance().giogoUpdate(new RemoveGameInfo(game.info()));
	    wait(30000);
            mGames.remove(game.id());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    /** Creates a new instance of GamesManager */
    private GamesManager() 
    {
    }
}
