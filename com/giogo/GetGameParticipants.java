/*
 * GetGameParticipants.java
 *
 * Created on May 10, 2004, 1:44 PM
 */

package com.giogo;

import com.inexum.IPC.ServiceRegistry;

/**
 *
 * @author  inexum
 */
public class GetGameParticipants implements com.inexum.IPC.IFunction 
{
    String mGameId;
    /** Creates a new instance of GetGameParticipants */
    public GetGameParticipants(String gameId) 
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
        return(giogo.gameParticipants(mGameId));
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
    
    protected String gameId() { return(mGameId); }
}
