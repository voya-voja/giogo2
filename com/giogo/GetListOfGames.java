/*
 * GetListOfGames.java
 *
 * Created on April 8, 2004, 7:07 PM
 */

package com.giogo;

import com.inexum.IPC.ServiceRegistry;

/**
 *
 * @author  nvojinov
 */
public class GetListOfGames implements com.inexum.IPC.IFunction 
{
    String  mGameName;
    
    /** Creates a new instance of GetListOfGames */
    public GetListOfGames(String gameName) 
    {
        mGameName = gameName;
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
        return(giogo.underwayGames(mGameName));
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
