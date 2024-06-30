/*
 * Administrator.java
 *
 * Created on Jun 03, 2003, 11:01 PM
 */

package com.giogo.game;

import java.util.Iterator;
import java.util.Collection;

import java.io.IOException;
/**
 *
 * @author  Giogo
 * @version 1.0
 */

public class Administrator extends Thread
{
    private boolean mHalt = false;
    private Collection mSteps;
    private Game mGame;
    
    protected Administrator(Game game, Collection gameSteps) 
    {
        mSteps = gameSteps;
        mGame = game;
    }
    
    public void run()
    {
        Thread.currentThread().setName("Admin-" + mGame.id());
        try
        {
            Iterator at = mSteps.iterator();
            while(!mHalt && at.hasNext())
            {
                Step step = (Step)at.next();
                step.execute(mGame);
                yield();
            }
            if(mHalt)
                onHalt();
            GamesManager.Instance().ended(mGame);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            if(com.inexum.util.Debug.on)
                com.inexum.util.Debug.out(e);
        }
    }
    
    /** Stop the server. */    
    public void halt () { mHalt = true; }

    /** On halt event closes a server socket.
     * 
     * @throws IOException if is unable to close a connection.
     */    
    protected void onHalt() throws IOException
    {
        
    }

}