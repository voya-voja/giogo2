/*
 * Card.java
 *
 * Created on Jun 03, 2003, 11:01 PM
 */

package com.giogo.game.card;

import java.awt.Color;
import java.io.Serializable;

/**
 *
 * @author  Giogo
 * @version 1.0
 */

public class Card implements Serializable
{
    // Ordinal of next suit to be created
    private static int gmNextOrdinal = 0;

    // Assign an ordinal to this suit
    private final int mOrdinal;

    private Suit	mSuit;
    private char	mValue;
    private boolean	mFaceUp;

    public Card( char value )
    {
        mValue = value;
        mFaceUp = false;
        mOrdinal = gmNextOrdinal++;
    }

    public Card( Suit suit  )
    {
        mSuit = suit;
        mFaceUp = false;
        mOrdinal = gmNextOrdinal++;
    }

    public Card( Suit suit, char value  )
    {
        mSuit = suit;
        mValue = value;
        mFaceUp = false;
        mOrdinal = gmNextOrdinal++;
    }

    public Card( char value, int ordinal )
    {
        mValue = value;
        mFaceUp = false;
        mOrdinal = ordinal;
        gmNextOrdinal = ordinal + 1;
    }

    public Card( Suit suit, int ordinal   )
    {
        mSuit = suit;
        mFaceUp = false;
        mOrdinal = ordinal;
        gmNextOrdinal = ordinal + 1;
    }

    public Card( Suit suit, char value, int ordinal   )
    {
        mSuit = suit;
        mValue = value;
        mFaceUp = false;
        mOrdinal = ordinal;
        gmNextOrdinal = ordinal + 1;
    }

    public char getValue() { return( mValue ); }
    public Suit getSuit() { return( mSuit ); }

    public boolean isFaceUp() { return( mFaceUp ); }
    public void faceUp() 
    { 
        mFaceUp = true; 
    }
      
    public void faceDown() 
    { 
        mFaceUp = false; 
    }
      
    public Color color() { return( mSuit.color() ); }

    public int compareTo( Object obj ) 
    {
        if( obj.getClass().equals( getClass() ) )
	      return( compareTo( (Card)obj ) );
        else
            return( -1 );
    }

    public int compareTo( Card card )
    {
        int test = mSuit.compareTo( card.getSuit() );
        if( test == 0 )
            test = (int)(mOrdinal - card.mOrdinal );
        return( test );
    }
}