/*
 * IfElsePhases.java
 *
 * Created on July 11, 2003, 4:29 PM
 */

package com.giogo.game;

import java.util.List;

/**
 *
 * @author  inexum
 */
public class IfElsePhases extends Phase 
{
    private Condition mCondition;
    private Step    mElsePhase;
    
    /** Creates a new instance of IfElsePhases */
    public IfElsePhases(Condition condition, List steps) 
    {
        super(steps);
        mCondition = condition;
    }
    
    /** Creates a new instance of IfElsePhases */
    public IfElsePhases(Condition condition, List steps, List elseSteps) 
    {
        super(steps);
        mCondition = condition;
        mElsePhase = new Phase(elseSteps);
    }
    
    public void execute(Game game) 
    {
        if(mCondition.isSatisfied(game))
            super.execute(game);
        else if(mElsePhase != null)
            mElsePhase.execute(game);
    }
    
}
