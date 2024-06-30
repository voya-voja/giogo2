/*
 * High32DeckSpec.java
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

public class High32DeckSpec extends DeckSpec
{
    private static char[] gmValues = { '7', '8', '9', '0', 'J', 'Q', 'K', 'A' };

    public High32DeckSpec( Suit[] suits )
    {
        super( suits );
    }

    public High32DeckSpec( Suit[] suits, Suit[] jokers )
    {
        super( suits, jokers );
    }

    public char[] getValidValues()
    {
        return( gmValues );
    }
}