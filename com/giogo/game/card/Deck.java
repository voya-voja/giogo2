/*
 * Deck.java
 *
 * Created on Jun 03, 2003, 11:01 PM
 */

package com.giogo.game.card;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.GregorianCalendar;
import java.util.Iterator;

/**
 *
 * @author  Giogo
 * @version 1.0
 */

public class Deck
{
    public static int   gmMaxSaffleNumber = 200;

    private List        mCards;

    public Deck()
    {
        mCards = new ArrayList();
    }

    public Deck( List cards )
    {
        mCards = cards;
    }

    public Deck( Card[] cards )
    {
        mCards = new ArrayList(cards.length);
        for( int index = 0; index <cards.length; index++ )
            mCards.add(index,cards[index]);
    }

    public Card getTopCard()
    {
        return( (Card)mCards.get( mCards.size() - 1 ) );
    }

    public Card getBottomCard()
    {
        return( (Card)mCards.get( 0 ) );
    }

    public Card removeTopCard()
    {
        if( mCards.size() == 0 ) return( null );
        return( (Card)mCards.remove( mCards.size() - 1 ) );
    }

    public Card removeBottomCard()
    {
        if( mCards.size() == 0 ) return( null );
        return( (Card)mCards.remove( 0 ) );
    }

    public int size() { return( mCards.size() ); }

    public void shuffle()
    {
        Random random = new Random( System.currentTimeMillis() );

        int deckSize = mCards.size();
        if( deckSize < 2 ) return;
        
        int shaffleNo = random.nextInt( gmMaxSaffleNumber );
        for( int sCount = 0; sCount < shaffleNo; sCount++ )
        {
                int swapIndex1 = random.nextInt( deckSize );
                int swapIndex2 = random.nextInt( deckSize );
                while( swapIndex1 == swapIndex2 ) 
                        swapIndex2 = random.nextInt( deckSize );
                
                Card heldCard = (Card)mCards.get( swapIndex1 );
                mCards.set( swapIndex1, (Card)mCards.get( swapIndex2 ) );
                mCards.set( swapIndex2, heldCard );
        }
    }

    public void sort()
    {
        int deckSize = mCards.size();
        boolean done = false;
        while( !done )
        {
            done = true;
            Card card = (Card)mCards.get( 0 );
            for( int cCount = 1; cCount < deckSize; cCount++ )
            {
                Card prevCard = card;
                card = (Card)mCards.get( cCount );
                if( prevCard.compareTo( card ) > 0 )
                {
                    mCards.set( cCount - 1, card );
                    mCards.set( cCount, prevCard );
                    card = prevCard;
                    if( done ) done = false;
                }
            }
        }
    }

    public Deck split()
    {
        Random random = new Random( new GregorianCalendar().getTimeInMillis() );
        return( split( random.nextInt( mCards.size() ) ) );
    }

    public Deck split( int at )
    {
        Deck deck = new Deck();
        int newDeckSize = mCards.size() - at;
        
        if( newDeckSize < 1 ) return deck;
        
        for( int cCount = 0; cCount < newDeckSize; cCount++ )
            deck.add( (Card)mCards.remove( mCards.size() - 1 ) );
        return( deck );
    }

    public void add( Deck deck )
    {
        if( deck == null ) return;
        
        mCards.addAll( deck.mCards );
        deck.mCards.clear();
    }

    public Card cardAt( int index )
    {
        return( (Card)mCards.get( index ) );
    }

    public void add( Card card )
    {
        mCards.add( card );
    }

    public void faceUp()
    {
        int deckSize = mCards.size();
        for( int cCount = 0; cCount < deckSize; cCount++ )
            ((Card)mCards.get( cCount )).faceUp();
    }

    public void faceDown()
    {
        int deckSize = mCards.size();
        for( int cCount = 0; cCount < deckSize; cCount++ )
            ((Card)mCards.get( cCount )).faceDown();
    }
    
    public void remove( Deck deck )
    {
        Iterator atCard = deck.mCards.iterator();
        while( atCard.hasNext() )
        {
            remove( (Card)atCard.next() );
        }
    }
    
    public void remove( Card card )
    {
        int deckSize = mCards.size();
        for( int at = 0; at < deckSize; at++ )
            if( card.compareTo( (Card)mCards.get(at) ) == 0)
            {
                mCards.remove(at);
                break;
            }
    }
    
    public Deck getCards(Card[] cards)
    {
        Deck deck = new Deck();
        for(int cCount = 0; cCount < cards.length; cCount++)
        {
            Card card = cards[cCount];
            int deckSize = mCards.size();
            for( int at = 0; at < deckSize; at++ )
            {
                Card deckCard = (Card)mCards.get(at);
                if( card.compareTo( deckCard ) == 0)
                {
                    deck.add(deckCard);
                    mCards.remove(at);
                    break;
                }
            }
        }            
        return(deck);
    }
}