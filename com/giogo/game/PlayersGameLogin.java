/*
 * PlayersGameLogin.java
 *
 * Created on Jun 03, 2003, 11:01 PM
 */

package com.giogo.game;

import java.util.List;
import java.util.Iterator;

/**
 *
 * @author  Giogo
 * @version 1.0
 */

public class PlayersGameLogin implements Step
{
    private int	mRequiredPlayersNo;

    public PlayersGameLogin(int requiredPlayersNo)
    {
        mRequiredPlayersNo = requiredPlayersNo;
    }

    public void execute(Game game)
    {
        if(game.isStarted()) return;
        
        if(com.inexum.util.Debug.on)
            com.inexum.util.Debug.out(game.id());
        
        List gamePlayers = game.getPlayers();
        while(gamePlayers.size() < mRequiredPlayersNo)
        {
            try
            {
                synchronized(gamePlayers)
                {
                    if(com.inexum.util.Debug.on)
                        com.inexum.util.Debug.out("WAIT " + gamePlayers);
                    gamePlayers.wait();
                }
            }
            catch(InterruptedException e)
            {
                if(com.inexum.util.Debug.on)
                    com.inexum.util.Debug .out(e);
            }
            game.receive(new PlayersListUpdate(game));
        }
        game.start();
    }
}