/*
 * GiogoGameLogon.java
 *
 * Created on April 17, 2004, 10:35 PM
 */

package com.giogo;

import com.inexum.IPC.IFunction;
import com.inexum.IPC.ServiceRegistry;
/**
 *
 * @author  inexum
 */
public class GiogoGameLogon implements IFunction
{
    GamesServerInfo    mInfo;
    String[]    mSupportedGames;
    
    /** Creates a new instance of GiogoGameLogon */
    public GiogoGameLogon(String host, int port, String[] supportedGames)
    {
        mInfo = new GamesServerInfo(null, host, port);
        mSupportedGames = supportedGames;
    }
    
    /** Define specific exception class.
     *
     * @return an exception class.
     */
    public Class exceptionClass() 
    {
        return((new java.io.IOException()).getClass());
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
        IGiogoServer server = (IGiogoServer)registry.getServiceForName("com.giogo.IGiogoServer");

        mInfo.name(server.newGameServerName());
        server.update(mInfo, mSupportedGames);
        
        return(mInfo.name());
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
