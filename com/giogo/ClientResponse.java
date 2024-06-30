/*
 * ClientResponse.java
 *
 * Created on August 27, 2003, 2:33 PM
 */

package com.giogo;

import com.inexum.IPC.IOperation;
import com.inexum.IPC.ServiceRegistry;

/**
 *
 * @author  inexum
 */
public class ClientResponse implements IOperation
{
    private String mAlias;
    private String mGameId;
    private String mService;
    private Object mResponse;
    private String mMessage;
    
    /** Creates a new instance of ClientResponse */
    public ClientResponse(String service, Object response) 
    {
        mService = service;
        mResponse = response;
    }
    
    /** Creates a new instance of ClientResponse */
    public ClientResponse(String service, String gameId, String alias, Object response) 
    {
        mGameId = gameId;
        mAlias = alias;
        mService = service;
        mResponse = response;
    }
    
    /** Define specific exception class.
     *
     * @return an exception class.
     */
    public Class exceptionClass() 
    {
        return((new GiogoException("")).getClass());
    }    
    /** Throw the specified exception class.
     *
     * throws Exception a procedure's specific exception.
     */
    public void throwException() throws Exception
    { 
        throw new GiogoException("ClientRequest exception:"); 
    }
    
    /** Define specific functionality.
     *
     * @return an object that the function returns
     * @throws Exception - any application specific exception that is thrown by
     * the function
     */
    public void run() throws Exception
    {
        Service service = 
            (Service)ServiceRegistry.getDefaultInstance().getServiceForName(mService);
        service.run(this);
    }
    
    public String gameId() { return(mGameId); }
    public void gameId(String id) { mGameId = id; }
    
    public String alias() { return(mAlias); }
    public void alias(String alias) { mAlias = alias; }
    
    public Object response() { return(mResponse); }
    
    public void service(String service) { mService = service; }
    public String service() { return(mService); }
    
    public void message(String message) { mMessage = message; }
    public String message() { return(mMessage); }
}
