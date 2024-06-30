/*
 * Service.java
 *
 * Created on June 8, 2003, 5:36 PM
 */

package com.giogo;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.inexum.IPC.IProcedure;
import com.inexum.IPC.ServiceRegistry;

/**
 *
 * @author  inexum
 */
public abstract class Service
{
    private String mServiceName;
    
    /** Constructs GameViewServiceImpl object.
     * @param service - the service registration name
     * @throws RemoteException if cannot be exported or bound to RMI registry
     */
    Service(String serviceName)
    {
        mServiceName = serviceName;    
    }
    
    /** Register GameViewServiceImpl object with the RMI registry.
     * @param service - the service to be registered in the RMI registry
     * @throws MalformedURLException if name cannot be used to construct a valid URL
     * @throws RemoteException if cannot be exported or bound to RMI registry
     * @throws IllegalArgumentException if null passed as name
     */
    public static void register( Service service ) 
                                throws IllegalArgumentException
    {
        String name = service.serviceName();
        if (name == null) 
            throw new IllegalArgumentException("registration name can not be null");
        ServiceRegistry.getDefaultInstance().setServiceForName(service, service.mServiceName);
    }
    
    public String serviceName()
    { 
        return( mServiceName ); 
    }

    /** Define specific functionality.
     *
     * @param procedure - a procedure that server need to run
     * @return an object that the function returns
     * @throws Exception - any application specific exception that is thrown by
     * the function
     */
    public abstract Object run( IProcedure procedure ) throws Exception;
}
