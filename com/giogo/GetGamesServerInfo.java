/*
 * GetGamesServerInfo.java
 *
 * Created on April 26, 2004, 3:33 PM
 */

package com.giogo;

import com.inexum.IPC.ServiceRegistry;

/**
 *
 * @author  inexum
 */
public class GetGamesServerInfo implements com.inexum.IPC.IFunction 
{
    String  mGameId;
    
    /** Creates a new instance of GetGamesServerInfo */
    public GetGamesServerInfo(String gameId) 
    {
        mGameId = gameId;
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
        return(giogo.getGamesServerInfo(mGameId));
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
