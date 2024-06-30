/*
 * CardGame.java
 *
 * Created on Jun 03, 2003, 11:01 PM
 */

package com.giogo.game.card;

import com.giogo.game.Game;

import java.util.List;
import java.util.Map;

/**
 *
 * @author  Giogo
 * @version 1.0
 */

public class CardGame extends Game
{
    private Deck	mDeck;
    private Deck	mWidows;
    private boolean     mPlayHand = false;

    public CardGame(Map parameters, CardGameSpecification spec, Deck deck)
    {
        super(parameters, spec);
        mDeck = deck;
    }

    public Deck deck() { return(mDeck); }

    public void widows(Deck deck) 
    {
        if(mWidows == null)
            mWidows = deck; 
        else
            mWidows.add(deck);
    }

    public Deck widows() { return(mWidows); }
    
    public void playHand(boolean status) {mPlayHand = status;}
    
    public boolean isPlayingHand() { return(mPlayHand); }

    public void oneMore()
    {
        super.oneMore();
        mPlayHand = false;
    }
}