/*
 * PreferenceFinalScoreCalculation.java
 *
 * Created on March 18, 2004, 9:00 AM
 */

package com.giogo.game.card.preference;

import com.giogo.game.Player;
import com.giogo.game.Game;
import com.giogo.game.FinalScore;
import java.util.Iterator;

/**
 *
 * @author  inexum
 */
public class PreferenceFinalScore extends FinalScore 
{
    /** Creates a new instance of PreferenceFinalScoreCalculation */
    public PreferenceFinalScore()
    {
    }

    protected String score(Game game, Player player)
    {
        PreferencePlayer prefPlayer = (PreferencePlayer)player;
        int score = prefPlayer.mainPoints() * 10 
                        + prefPlayer.defencePoints(PreferencePlayer.cLeft)
                        + prefPlayer.defencePoints(PreferencePlayer.cRight);
        Iterator at = game.getPlayers().iterator();
        int playerId = prefPlayer.id();
        while(at.hasNext())
        {
            PreferencePlayer otherPlayer = (PreferencePlayer)at.next();
            if(otherPlayer == prefPlayer)
                continue;
            else if((3 + prefPlayer.id() - otherPlayer.id()) % 3 == 2)
                score -= otherPlayer.defencePoints(PreferencePlayer.cLeft);
            else
                score -= otherPlayer.defencePoints(PreferencePlayer.cRight);
        }
        return(Integer.toString(score));
    }

    protected void sort(String[] playersName, String[] playersScore)
    {
        boolean swapped = true;
        while(swapped)
        {
            swapped = false;
            for(int pCount = 1; pCount < playersScore.length; pCount++)
            {
                if(Integer.parseInt(playersScore[pCount - 1])
                        < Integer.parseInt(playersScore[pCount]))
                {
                    String tmp = playersScore[pCount - 1];
                    playersScore[pCount - 1] = playersScore[pCount];
                    playersScore[pCount] = tmp;
                    tmp = playersName[pCount - 1];
                    playersName[pCount - 1] = playersName[pCount];
                    playersName[pCount] = tmp;
                    swapped = true;
                }
            }
        }
    }
}
