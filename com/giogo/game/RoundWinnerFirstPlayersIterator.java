/*
 * RoundWinnerFirstPlayersIterator.java
 *
 * Created on December 12, 2003, 8:22 PM
 */

package com.giogo.game;

/**
 *
 * @author  inexum
 */
public class RoundWinnerFirstPlayersIterator extends RoundsPlayersIterator {
    
    private Game mGame;
    
    /** Creates a new instance of RoundWinnerFirstPlayersIterator */
    public RoundWinnerFirstPlayersIterator(Game game)
    {
        super(game.getPlayers(), game.firstPlayer().id());
        mGame = game;
    }

    /** Creates a new instance of RoundsPlayersIterator */
    public RoundWinnerFirstPlayersIterator(Game game, long roundsNo)
    {
        super(game.getPlayers(), game.firstPlayer().id(), roundsNo);
        mGame = game;
    }
    
    public void reset()
    {
        Player player = mGame.roundWinner();
        if(player!=null)
        {
            reset(player.id());
        }
        else
        {
            super.reset();
        }
    }
}
