/*
 * CharacterClassicSuitPresenter.java
 *
 * Created on June 5, 2003, 8:28 PM
 */

package com.giogo.game.card;

import java.awt.Graphics;
import java.awt.Font;

/**
 *
 * @author  inexum
 */
public class CharacterClassicSuitPresenter implements SuitPresenter
{
    public void paint( Suit suit, Graphics g, int width, int height ) throws ClassNotFoundException
    {
        if( suit.getClass().equals(
                        Class.forName( "com.giogo.game.card.CharacterClassicSuit" ) ) )
        {
            g.setColor( suit.color() );
            g.setFont( new Font( "Arial", Font.BOLD, (height + 12)/2 ) );
            CharacterClassicSuit charSuit = (CharacterClassicSuit)suit;
            g.drawString( "" + charSuit.value(), width/11, (height - 12)/2 );
        }
    }
}
