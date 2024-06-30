/*
 * DeckSpec.java
 *
 * Created on Jun 03, 2003, 11:01 PM
 */

package com.giogo.game.card;

import java.awt.Color;

/**
 *
 * @author  Giogo
 * @version 1.0
 */

public abstract class DeckSpec
{
    private Suit[] mJokers;
    private Suit[] mSuits;

    protected DeckSpec( Suit[] suits )
    {
        mSuits = suits;
    }

    protected DeckSpec( Suit[] suits, Suit[] jokers )
    {
        mSuits = suits;
        mJokers = jokers;
    }

    public abstract char[] getValidValues();

    public Suit[] getValidSuits() { return( mSuits ); }

    public Suit[] getValidJokers() { return( mJokers ); }

    public boolean isValidCard( Card card )
    {
        return( isValidSuit( card.getSuit() ) 
				&& isValidValue( card.getValue() ) );
    }

    public boolean isValidSuit( Suit suit )
    {
        boolean valid = false;
        for( int count = 0; count < mSuits.length; count++ )
            if( mSuits[ count ].equals( suit ) )
            {
                valid = true;
                break;
            }
        return( valid );
    }

    public boolean isValidValue( char value )
    {
        boolean valid = false;
        char[] values = getValidValues();
        for( int count = 0; count < values.length; count++ )
            if( values[ count ] == value )
            {
                valid = true;
                break;
            }
        return( valid );
    }

    public Deck createDeck()
    {
        Deck deck = new Deck();

        char[] values = getValidValues();
        if( mSuits == null && values != null )
            for( int vCount =0; vCount < values.length; vCount++ )
                deck.add( new Card( values[ vCount ] ) );
        else if( mSuits != null && values == null )
            for( int sCount = 0; sCount < mSuits.length; sCount++ )
                deck.add( new Card( mSuits[ sCount ] ) );
        else if( mSuits != null && values != null )
            for( int sCount = 0; sCount < mSuits.length; sCount++ )
                for( int vCount =0; vCount < values.length; vCount++ )
                    deck.add( new Card( mSuits[ sCount ],
                                 values[ vCount ] ) );
        if( mJokers != null )
            for( int jCount = 0; jCount < mJokers.length; jCount++ )
                deck.add( new Card( mJokers[ jCount ] ) );
        return( deck );
    }
}