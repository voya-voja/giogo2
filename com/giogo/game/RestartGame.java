/*
 * RestartGame.java
 *
 * Created on October 3, 2003, 2:59 PM
 */

package com.giogo.game;

import com.giogo.game.Step;

/**
 *
 * @author  inexum
 */
public class RestartGame implements Step 
{
    int mStartFrom = 0;
    /** Creates a new instance of RestartGame */
    public RestartGame() 
    {
        mStartFrom = 0;
    }
    
    /** Creates a new instance of RestartGame */
    public RestartGame(int startFrom) 
    {
        mStartFrom = startFrom;
    }
    
    public void execute(Game game) 
    {
        if(com.inexum.util.Debug.on)
            com.inexum.util.Debug.out("Start from: " + mStartFrom);
        if(mStartFrom > 0)
            mStartFrom--;
        else
            game.oneMore();
    }
    
}
