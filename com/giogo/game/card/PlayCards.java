/*
 * PlayCards.java
 *
 * Created on December 9, 2003, 8:00 PM
 */

package com.giogo.game.card;

import com.giogo.game.Game;
import com.giogo.game.Step;
import com.giogo.game.Action;
import com.giogo.game.RoundWinnerFirstPlayersIterator;
import com.giogo.game.RoundsPlayersIterator;
import com.giogo.game.WipePresenterData;

/**
 *
 * @author  inexum
 */
public class PlayCards implements Step 
{
    private final int cHandEndTimeout = 2000;
    
    int mNumberOfHands = 0;
    
    /** Creates a new instance of PlayCards */
    public PlayCards(int numberOfHands) 
    {
        mNumberOfHands = numberOfHands;
    }
    
    protected int numberOfHands() { return(mNumberOfHands); }

    protected RoundsPlayersIterator playersPlayIterator(Game game)
    {
        return(new RoundWinnerFirstPlayersIterator(game, mNumberOfHands));
    }

    public void execute(Game game)
    {
        if( com.inexum.util.Debug.on )
            com.inexum.util.Debug.out( "Hands #: " + mNumberOfHands );

        RoundsPlayersIterator at = playersPlayIterator(game);
        int currentRound = -1;
        while(!isEnd((CardGame)game) && at.hasNext())
        {
            if(at.round() != currentRound)
            {
                currentRound = at.round();
                handStart(currentRound, (CardGame)game);
            }
            playerPlay((CardGame)game, (CardPlayer)at.next());
            if(at.round() != currentRound)
            {
                handEnd(currentRound, (CardGame)game);
                at.reset();
            }
        }
    }
    
    protected void handStart(int round, CardGame game) 
    {
        if( com.inexum.util.Debug.on )
            com.inexum.util.Debug.out( "hand [" + round + "]");
    }

    protected String[] activeCardPresenters(CardGame game, CardPlayer player)
    {
        CardGameSpecification gameSpec = 
                                    (CardGameSpecification)game.specification();
        return(gameSpec.playerCardPresenterNames());
    }
    
    protected void playerPlay(CardGame game, CardPlayer player)
    {
        if( com.inexum.util.Debug.on )
            com.inexum.util.Debug.out( "player [" + player.id() + "]");
        if(isActive(player))
        {
            String[] cardPresenters = activeCardPresenters(game, player);
            Action action = new ActivateCards( game, 
                                    cardPresenters, 
                                    player );
            game.receive(action);
            player.waitResponse();
            
            player.thrownCards(player.giveSelectedCards());
            action = new PlayersCards(game, player);
            game.receive(action);
            
            action = new ThrowCards(game, player);
            game.receive(action);            
        }
    }
    
    protected void handEnd(int round, CardGame game) 
    {
        if( com.inexum.util.Debug.on )
            com.inexum.util.Debug.out( "hand [" + round + "]");
        pause(cHandEndTimeout);
                    
        CardGameSpecification gameSpec = 
                                    (CardGameSpecification)game.specification();
        game.receive(new WipePresenterData(game, 
                                        gameSpec.throwCardPresenterNames()));
    }

    protected boolean isEnd(CardGame game) { return(false); }
    
    protected boolean isActive(CardPlayer player)
    {
        return(player.isPlaying());
    }
    
    private synchronized void pause(int timeout)
    {
        try{ wait(timeout); } catch(InterruptedException e) {}
    }
}
