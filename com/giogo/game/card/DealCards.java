/*
 * DealCards.java
 *
 * Created on Jun 03, 2003, 11:01 PM
 */

package com.giogo.game.card;

import com.giogo.game.Game;
import com.giogo.game.Action;
import com.giogo.game.ActionDistribution;

/**
 *
 * @author  Giogo
 * @version 1.0
 */

public abstract class DealCards extends ActionDistribution
{
    private int	mCardNo;
    private boolean mShuffle = false;

    public DealCards(int cardNo)
    {
        mCardNo = cardNo;
    }

    public DealCards(int cardNo, boolean shuffle)
    {
        mCardNo = cardNo;
        mShuffle = shuffle;
    }

    public Action createAction(Game game)
    {
        CardGame cardGame = (CardGame)game;
        Deck deck = cardGame.deck();
        
        if(mShuffle)
        {
            deck.faceDown();
            deck.shuffle();
        }
        pause();
        return(createAction(deck, cardGame));
    }

    public synchronized void pause()
    {
        try{ wait(3000); } catch(InterruptedException e) {}
    }

    abstract protected Action createAction(Deck deck, CardGame game);
    
    protected int cardNumber() { return(mCardNo); }
}