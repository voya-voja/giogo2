/*
 * PreferenceCardApplet.java
 *
 * Created on June 8, 2003, 3:00 PM
 */

package com.giogo.game.card.preference;

import com.giogo.game.card.CardApplet;
import com.giogo.game.card.CharacterClassicSuitPresenter;

import java.awt.Color;

/**
 *
 * @author  inexum
 */
public class PreferenceCardApplet extends CardApplet 
{
    int i =0;
    /** Creates a new instance of PreferenceCardApplet */
    public PreferenceCardApplet() 
    {
        super(new CharacterClassicSuitPresenter());
    }
}