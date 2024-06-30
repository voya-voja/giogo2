/*
 * NoClientAction.java
 *
 * Created on May 14, 2004, 8:08 PM
 */

package com.giogo;

/**
 *
 * @author  inexum
 */
public class NoClientAction implements ClientAction 
{
    /** Creates a new instance of NoClientAction */
    public NoClientAction() 
    {
    }
    
    public void execute(ClientSession session) 
    {
    }
    
    public String name() { return(getClass().getName()); }
}
