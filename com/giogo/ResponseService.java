/*
 * ResponseService.java
 *
 * Created on August 28, 2003, 6:01 PM
 */

package com.giogo;

import com.inexum.IPC.IProcedure;

/**
 *
 * @author  inexum
 */
public abstract class ResponseService extends Service 
{
    /** Creates a new instance of ResponseService */
    public ResponseService(String serviceName) 
    {
        super(serviceName);
    }
    
    /** Define specific functionality.
     *
     * @return an object that the function returns
     * @throws Exception - any application specific exception that is thrown by
     * the function
     */
    public Object run(IProcedure response) throws Exception 
    {
        run((ClientResponse)response);
        return(null);
    }
    
    /** Define specific functionality.
     *
     * @param response - a response sent by the game view client
     * @throws Exception - any application specific exception that is thrown by
     * the function
     */
    abstract public void run(ClientResponse response) throws Exception;
}
