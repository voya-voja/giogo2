/*
 * StartGame.java
 *
 * Created on April 18, 2004, 12:21 AM
 */

package com.giogo;

import com.inexum.IPC.ServiceRegistry;

import java.util.Map;

/**
 *
 * @author  inexum
 */
public class StartGame implements com.inexum.IPC.IFunction 
{
    Map  mGameParameters;

    /** Creates a new instance of StartGame */
    public StartGame(Map gameParameters) 
    {
        mGameParameters = gameParameters;
    }
    
    /** Define specific functionality.
     *
     * @return an object that the function returns
     * @throws Exception - any application specific exception that is thrown by
     * the function
     */
    public Object run() throws Exception 
    {
        ServiceRegistry registry = ServiceRegistry.getDefaultInstance();
        IGiogo giogo = (IGiogo)registry.getServiceForName("com.giogo.IGiogo");
        return(giogo.startGame(mGameParameters));
    }
    
    /** Define specific exception class.
     *
     * @return an exception class.
     */
    public Class exceptionClass() 
    {
        return((new java.io.IOException()).getClass());
    }
    
    /** Throw the specified exception class.
     *
     * throws Exception a procedure's specific exception.
     */
    public void throwException() throws Exception 
    {
        throw new java.io.IOException();
    }
    
}
