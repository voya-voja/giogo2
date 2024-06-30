/*
 * Player.java
 *
 * Created on Jun 03, 2003, 11:01 PM
 */

package com.giogo.game;

import com.giogo.ClientAction;

/**
 *
 * @author  Giogo
 * @version 1.0
 */

public class Player extends Listener
{
    private int mId;

    public Player(Game game)
    {
        super("Player", game);
    }
    
    public Player(String alias, Game game)
    {
        super(alias, game);
    }
    
    public int id() 
    {
        return(mId); 
    }
    
    public void setId(int id) 
    { 
        mId = id;
    }
    
    public void reset() {}
    
    public synchronized void waitResponse()
    {
        if(com.inexum.util.Debug.on)
            com.inexum.util.Debug.out("WAIT " + alias() + "["+ mId + "]");
        try{ wait(); } catch(InterruptedException e) {}
    }        

    public synchronized void receivedResponse()
    {
        if(com.inexum.util.Debug.on)
            com.inexum.util.Debug.out("NOTIFY " + this );
        notifyAll();
    }
    
    public boolean isPlaying() { return(true); }
    
    protected ClientAction getViewAction(Action action)
    {
        return(action.gameViewAction(this));
    }
}