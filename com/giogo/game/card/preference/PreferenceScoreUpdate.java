/*
 * PreferenceScoreUpdate.java
 *
 * Created on Jun 03, 2003, 11:01 PM
 */

package com.giogo.game.card.preference;

import com.giogo.game.Condition;
import com.giogo.game.Game;

import java.util.Iterator;


/**
 *
 * @author  Giogo
 * @version 1.0
 */

public class PreferenceScoreUpdate extends Condition
{
    public void execute(Game game)
    {
        if(com.inexum.util.Debug.on)
            com.inexum.util.Debug.out(game.toString());
        Preference preference = (Preference)game;
        PreferencePlayer winner = (PreferencePlayer)preference.biddingWinner();
        
        if(winner == null) return;
        int remainingPoints = preference.remainingPoints();
        int factor = preference.contraFactor();
        if(winner.isRefe())
            factor *= 2;

        winner.calculateMainPoints(remainingPoints, factor);
        PreferencePlayer left = winner.left();
        left.calculateDefencePoints(PreferencePlayer.cLeft, 
                                                    remainingPoints, factor);
        PreferencePlayer right = winner.right();
        right.calculateDefencePoints(PreferencePlayer.cRight, 
                                                    remainingPoints, factor);
        game.receive(new RefreshScore(game));
    }
    
    protected boolean test(Game game)
    {
        if(com.inexum.util.Debug.on)
            com.inexum.util.Debug.out("" + ((Preference)game).remainingPoints());
        return(((Preference)game).remainingPoints() < 0);
    }
}