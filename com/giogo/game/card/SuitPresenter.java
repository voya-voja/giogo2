/*
 * CharacterClassicSuitPresenter.java
 *
 * Created on June 5, 2003, 8:28 PM
 */

package com.giogo.game.card;

import java.awt.Graphics;

/**
 *
 * @author  inexum
 */
public interface SuitPresenter
{
    public void paint( Suit suit, Graphics g, int width, int height ) 
                                                throws ClassNotFoundException;
}
