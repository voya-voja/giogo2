/*
 * DummyAction.java
 *
 * Created on May 14, 2004, 8:17 PM
 */

package com.giogo.game;

import com.giogo.ClientAction;
import com.giogo.NoClientAction;

/**
 *
 * @author  inexum
 */
public class DummyAction extends Action 
{
    /** Creates a new instance of DummyAction */
    public DummyAction() 
    {
    }
    
    public ClientAction gameViewAction() 
    {
        return(new NoClientAction());
    }
}
