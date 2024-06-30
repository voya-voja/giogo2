/*
 * Suit.java
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

public class CharacterClassicSuit implements Suit
{
    private final char mSuit;
    private final Color mColor;

    // Ordinal of next suit to be created
    private static int gmNextOrdinal = 0;

    // Assign an ordinal to this suit
    private final int mOrdinal = gmNextOrdinal++;

    private CharacterClassicSuit(char suit, Color color) 
    { 
        mSuit = suit; 
        mColor = color; 
    }

    public String toString()  { return("" + this.mSuit); }

    public int compareTo(Object obj) 
    {
        if(obj.getClass().equals(getClass()))
	      return(compareTo((CharacterClassicSuit)obj));
        else
            return(-1);
    }

    public int compareTo(Suit suit) 
    {
        if(suit.getClass().equals(getClass()))
	      return(compareTo((CharacterClassicSuit)suit));
        else
            return(-1);
    }

    public int compareTo(CharacterClassicSuit suit) 
    {
        return(mOrdinal - suit.mOrdinal);
    }

    public Color color() { return(mColor); }
    
    public char value() { return(mSuit); }
    
    public boolean equals(Suit suit) 
    {
        return(compareTo(suit) == 0);
    }
    
    public static final Suit cDimond   = new CharacterClassicSuit((char)9830, Color.RED);
    public static final Suit cClub    = new CharacterClassicSuit((char)9824, Color.BLACK);
    public static final Suit cHeart  = new CharacterClassicSuit((char)9829, Color.RED);
    public static final Suit cSpade   = new CharacterClassicSuit((char)9827, Color.BLACK);
    public static final Suit cJokerBlack   = new CharacterClassicSuit((char)9787, Color.BLACK);
    public static final Suit cJokerRed   = new CharacterClassicSuit((char)9787, Color.RED);
}
