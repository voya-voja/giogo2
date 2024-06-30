/*
 * ClientRequest.java
 *
 * Created on June 8, 2003, 5:36 PM
 */

package com.giogo;

import com.inexum.IPC.IFunction;
import com.inexum.IPC.ServiceRegistry;

/**
 *
 * @author  inexum
 */
public class ClientRequest implements IFunction
{
    private String mAlias;
    private String mGameId;
    private String mService;
    private String mMessage;
    
    /** Creates a new instance of ClientRequest */
    public ClientRequest(String service) 
    {
        mService = service;
    }
    
    /** Creates a new instance of ClientRequest */
    public ClientRequest(String service, String gameId, String alias) 
    {
        mGameId = gameId;
        mAlias = alias;
        mService = service;
    }
    
    /** Define specific exception class.
     *
     * @return an exception class.
     */
    public Class exceptionClass() 
    {
        return( (new GiogoException( "" )).getClass() );
    }    
    /** Throw the specified exception class.
     *
     * throws Exception a procedure's specific exception.
     */
    public void throwException() throws Exception
    { 
        throw new GiogoException( "ClientRequest exception:" ); 
    }
    
    /** Define specific functionality.
     *
     * @return an object that the function returns
     * @throws Exception - any application specific exception that is thrown by
     * the function
     */
    public Object run() throws Exception
    {
        Service service = 
            (Service)ServiceRegistry.getDefaultInstance().getServiceForName(mService);
        return( service.run( this ) );
    }
    
    public String gameId() { return( mGameId ); }
    public void gameId( String id ) { mGameId = id; }
    
    public String alias() { return( mAlias ); }
    public void alias( String alias ) { mAlias = alias; }
    
    public String service() { return(mService); }

    public void message(String message) { mMessage = message; }
    public String message() { return(mMessage); }
}
