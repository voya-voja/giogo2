/*
 * CardPlayer.java
 *
 * Created on Jun 03, 2003, 11:01 PM
 */

package com.giogo.game.card;

import com.giogo.game.Player;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author  Giogo
 * @version 1.0
 */

public class CardPlayer extends Player
{
    private Deck    mCurrentHand;
    private List    mTricks = new ArrayList();
    private Deck    mSelectedCards;
    private Deck    mThrownCards;

    public CardPlayer(CardGame game)
    {
        super(game);
    }
    
    public CardPlayer(String alias, CardGame game)
    {
        super(alias, game);
    }
    
    public void take(Deck deck)
    {
        if(mCurrentHand == null)
            mCurrentHand = deck;
        else
            mCurrentHand.add(deck);
    }

    public Deck giveCards()
    {
        Deck deck = mCurrentHand;
        mCurrentHand = null;
        emptyTricks(deck);
        return(deck);
    }
    
    public Deck currentHand() { return(mCurrentHand); }
    
    public void selectedCards(Card[] cards)
    {
        mSelectedCards = mCurrentHand.getCards(cards);
    }
    
    public Deck selectedCards()
    {
        return(mSelectedCards);
    }

    public Deck giveSelectedCards() 
    { 
        Deck cards = mSelectedCards;
        mSelectedCards = null;
        return(cards);
    }
    
    public void thrownCards(Deck cards) { mThrownCards = cards; }
    
    public Deck thrownCards() { return(mThrownCards); }
    
    public Deck giveThrownCards()
    {
        Deck cards = mThrownCards;
        mThrownCards = null;
        return(cards);
    }
    
    public void addTrick(Deck trick)
    {
        mTricks.add(trick);
    }
    
    public Deck giveTrick()
    {
        return((Deck)mTricks.remove(mTricks.size() - 1));
    }
    
    public int tricksNo() { return(mTricks.size()); }
    
    public Deck[] tricks() 
    { 
        Deck[] example = new Deck[mTricks.size()];
        return((Deck[])mTricks.toArray(example)); 
    }
    
    protected void emptyTricks(Deck deck)
    {
        while(!mTricks.isEmpty())
            deck.add((Deck)mTricks.remove(mTricks.size() - 1));
    }
}