/*
 * Phase.java
 *
 * Created on Jun 03, 2003, 11:01 PM
 */

package com.giogo.game;

import java.util.List;
import java.util.Iterator;

/**
 *
 * @author  Giogo
 * @version 1.0
 */

public class Phase implements Step
{
    private List	mSteps;

    public Phase(List steps)
    {
        mSteps = steps;
    }

    public void execute(Game game)
    {
        if(com.inexum.util.Debug.on)
            com.inexum.util.Debug.out(game.toString());
        Iterator at = mSteps.iterator();
        while(at.hasNext())
        {
            Step step = (Step)at.next();
            step.execute(game);
        }
    }
}