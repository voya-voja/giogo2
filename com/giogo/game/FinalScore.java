/*
 * PreferenceFinalScoreCalculation.java
 *
 * Created on March 18, 2004, 9:00 AM
 */

package com.giogo.game;

import java.util.Iterator;
import java.util.List;
/**
 *
 * @author  inexum
 */
public abstract class FinalScore implements Step 
{
    /** Creates a new instance of PreferenceFinalScoreCalculation */
    public FinalScore()
    {
    }
    
    public void execute(Game game) 
    {
        game.oneMore();
        game.receive(score(game));
    }

    protected Action score(Game game) 
    {
        List players = game.getPlayers();
        int playersNo = players.size();
        String[] playersName = new String[playersNo];
        String[] playersScore = new String[playersNo];
        Iterator at = players.iterator();
        for(int pCount =0; at.hasNext(); pCount++)
        {
            Player player = (Player)at.next();
            playersName[pCount] = player.alias();
            playersScore[pCount] = score(game, player);
        }
        sort(playersName, playersScore);
        return(new PresentFinalScore(game, playersName, playersScore));
    }
    
    protected void sort(String[] playersName, String[] playersScore)
    {
    }
    
    abstract protected String score(Game game, Player player);
}
