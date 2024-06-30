/*
 * PreferenceScoreCalculate.java
 *
 * Created on January 22, 2004, 10:38 PM
 */

package com.giogo.game.card.preference;

import com.giogo.game.Game;
import com.giogo.game.Step;

import java.util.Iterator;

/**
 *
 * @author  inexum
 */
public class PreferenceScoreInitialize implements Step 
{
    public void execute(Game game) 
    {
        if(com.inexum.util.Debug.on)
            com.inexum.util.Debug.out(game.toString());
        Iterator at = game.getPlayers().iterator();
        while(at.hasNext())
        {
            PreferencePlayer player = (PreferencePlayer)at.next();
            player.mainPoints(((Preference)game).startMainPoints());
        }
        game.receive(new RefreshScore(game));
    }
}
