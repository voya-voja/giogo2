/*
 * InitiateGame.java
 *
 * Created on April 8, 2004, 7:07 PM
 */

package com.giogo;

import com.inexum.IPC.ServiceRegistry;

import java.util.Map;

/**
 *
 * @author  nvojinov
 */
public class InitiateGame implements com.inexum.IPC.IFunction 
{
    Map  mParameters;
    
    /** Creates a new instance of InitiateGame */
    public InitiateGame(Map parameters) 
    {
        mParameters = parameters;
    }
    
    public Object run() throws Exception 
    {
        ServiceRegistry registry = ServiceRegistry.getDefaultInstance();
        IGamesServer giogo = 
                (IGamesServer)registry.getServiceForName("com.giogo.IGamesServer");
        return(giogo.initiateGame(mParameters));
    }
    /** Define specific exception class. 
     *
     * @return an exception class.
     */
    public Class exceptionClass()
    {
        return((new GiogoException()).getClass());
    }
    
    /** Throw the specified exception class.
     *
     * throws Exception a procedure's specific exception.
     */
    public void throwException() throws Exception
    {
        throw new GiogoException();
    }
}
