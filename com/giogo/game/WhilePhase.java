/*
 * WhilePhase.java
 *
 * Created on Jun 03, 2003, 11:01 PM
 */

package com.giogo.game;

import java.util.List;

/**
 *
 * @author  Giogo
 * @version 1.0
 */

public class WhilePhase extends Phase
{
    private Condition	mCondition;
    private boolean     mCheckConditionFirst;

    public WhilePhase(Condition condition, List steps)
    {
        super(steps);
        mCondition = condition;
        mCheckConditionFirst = true;
    }

    public WhilePhase(Condition condition, List steps, boolean checkConditionFirst)
    {
        super(steps);
        mCondition = condition;
        mCheckConditionFirst = checkConditionFirst;
    }

    public void execute(Game game)
    {
        if(mCheckConditionFirst)
            while(mCondition.isSatisfied(game))
                super.execute(game);
        else
            do
            {
                super.execute(game);
            }while(mCondition.isSatisfied(game));
    }
}