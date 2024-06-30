/*
 * RequestService.java
 *
 * Created on June 8, 2003, 5:36 PM
 */

package com.giogo;

import com.inexum.IPC.IProcedure;

/**
 *
 * @author  inexum
 */
public abstract class RequestService extends Service
{
    /** Constructs RequestService object.
     * @param service - the service registration name
     * @throw RemoteException if cannot be exported or bound to RMI registry
     */
    public RequestService(String service)
    {
        super(service);    
    }

    /** Define specific functionality.  
     *
     * @param procedure - a procedure that server need to run
     * @return an object that the function returns
     * @throws Exception - any application specific exception that is thrown by
     * the function
     */
    public Object run(IProcedure procedure) throws Exception
    {
        ClientRequest request = (ClientRequest)procedure;
        return(run(request));
    }
    
    /** Define specific functionality.  
     *
     * @param request - a request for service sent by the game view client
     * @return an object that the function returns
     * @throws Exception - any application specific exception that is thrown by
     * the function
     */
    public abstract Object run(ClientRequest request) throws Exception;
}
