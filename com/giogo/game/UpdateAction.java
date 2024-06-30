/*
 * UpdateAction.java
 *
 * Created on May 3, 2004, 10:22 PM
 */

package com.giogo.game;

import com.giogo.ClientAction;

/**
 *
 * @author  inexum
 */
public abstract class UpdateAction extends Action 
{
    /** Creates a new instance of UpdateAction */
    public UpdateAction(Game game) 
    {
        super(game);
    }

    public ClientAction gameViewAction() 
    { 
        return(clientAction(0)); 
    }
    
    protected ClientAction listenerViewAction(Listener listener)
    {
        try
        {
            int playerId = ((Player)listener).id();
            return(clientAction(playerId));
        }
        catch(ClassCastException e)
        {
            return(super.listenerViewAction(listener));
        }
    }
    
    protected abstract ClientAction clientAction(int playerId);
}
