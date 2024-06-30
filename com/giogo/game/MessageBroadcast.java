/*
 * MessageBroadcast.java
 *
 * Created on April 24, 2004, 1:24 AM
 */

package com.giogo.game;

import com.giogo.ClientAction;
import com.giogo.PresenterUpdate;

/**
 *
 * @author  inexum
 */
public class MessageBroadcast extends Action 
{
    String mMessage;
    
    /** Creates a new instance of MessageBroadcast */
    public MessageBroadcast(Game game, String message) 
    {
        super(game);
        mMessage = message;
    }
    
    public ClientAction gameViewAction() 
    {
        Specification spec = game().specification();
        return(new PresenterUpdate(spec.talkPresenterName(), mMessage));
    }
    
}
