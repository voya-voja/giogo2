/*
 * Condition.java
 *
 * Created on Jun 03, 2003, 11:01 PM
 */

package com.giogo.game;

/**
 *
 * @author  Giogo
 * @version 1.0
 */

public abstract class Condition implements Step
{
    public boolean isSatisfied(Game game)
    {
        execute(game);
        return(test(game));
    }

    abstract public void execute(Game game);
    abstract protected boolean test(Game game);
}