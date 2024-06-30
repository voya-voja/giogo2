/*
 * FullDeckSpec.java
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

public class FullDeckSpec extends DeckSpec
{
        private static char[] gmValues = { '2', '3', '4', '5', '6', '7', '8',
                                                '9', '0', 'J', 'Q', 'K', 'A' };

        public FullDeckSpec( Suit[] suits )
        {
                super( suits );
        }

        public FullDeckSpec( Suit[] suits, Suit[] jokers )
        {
                super( suits, jokers );
        }

        public char[] getValidValues()
	  {
		return( gmValues );
	  }
}