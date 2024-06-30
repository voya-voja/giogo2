/*
 * GameViewAction.java
 *
 * Created on June 8, 2003, 5:20 PM
 */

package com.giogo;

import java.io.Serializable;

/**
 *
 * @author  inexum
 */
public interface ClientAction  extends Serializable
{
    public String name();
    
    public void execute(ClientSession session);
}
