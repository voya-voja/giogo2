/*
 * ActionDistribution.java
 *
 * Created on Jun 03, 2003, 11:01 PM
 */

package com.giogo.game;

/**
 *
 * @author  Giogo
 * @version 1.0
 */

public abstract class ActionDistribution implements Step
{
    public void execute(Game game)
    {
        if(com.inexum.util.Debug.on)
            com.inexum.util.Debug .out("ActionDistribution.execute()");

        Action action = createAction(game);
        game.receive(action);
    }

    abstract protected Action createAction(Game game);
}