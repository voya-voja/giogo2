/*
 * Specification.java
 *
 * Created on Jun 03, 2003, 11:01 PM
 */

package com.giogo.game;

import com.giogo.GameInfo;
import com.inexum.IPC.Server;

import java.util.List;
import java.util.Map;

/**
 *
 * @author  Giogo
 * @version 1.0
 */

public abstract class Specification
{
    private String mName;
    private int	mMaximalPlayerNo;

    protected Specification(String name, int maxPlayerNo) throws GameException
    {
        mName = name;
        mMaximalPlayerNo = maxPlayerNo;
        register();
    }

    public String name() { return(mName); }

    public boolean allowNewPlayerToJoin(int playerNo)
    {
        return(playerNo < mMaximalPlayerNo);
    }

    public GameInfo initiate(Map parameters) throws java.io.IOException
    {
        Game game = createGame(parameters);
        Administrator admin = new Administrator(game, gameSteps());
        admin.start();
        return(game.info());
    }

    abstract public String[] playerPresenterNames();
    abstract public String playersPresenterName() ;
    
    public String loginPresenterName() 
    {
        return("login");
    }


    protected Game createGame(Map parameters)
    {
        return(new Game(parameters, this));
    }

    public Player createPlayer(String alias, Game game)
    {
        return(new Player(alias, game));
    }

    abstract protected List gameSteps();

    private void register() throws GameException
    {
        GamesServer server = GamesServer.Instance();
        if(server.isRegistered(mName))
        {
            throw new GameException("Unable to register the game '" 
                                                            + mName + "'");
        }
        try
        {
            server.register(this);
        }
        catch(Exception e)
        {
            throw new GameException("Unable to register the game '" 
                                                        + mName + "'", e);
        }
    }
    
    public String talkPresenterName()
    {
        return("talk");
    }
}