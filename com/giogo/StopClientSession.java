/*
 * StopClientSession.java
 *
 * Created on May 5, 2004, 7:36 PM
 */

package com.giogo;

/**
 *
 * @author  inexum
 */
public class StopClientSession implements ClientAction {
    
    /** Creates a new instance of StopClientSession */
    public StopClientSession() 
    {
    }
    
    public void execute(ClientSession session) 
    {
        session.halt();
    }
    
    public String name() { return(getClass().getName()); }
}
