/*
 * RemoveGameInfo.java
 *
 * Created on May 1, 2004, 9:33 PM
 */

package com.giogo;

import com.inexum.IPC.IOperation;
import com.inexum.IPC.ServiceRegistry;

/**
 *
 * @author  inexum
 */
public class RemoveGameInfo implements IOperation 
{
    GameInfo    mGameInfo;
    
    /** Creates a new instance of RemoveGameInfo */
    public RemoveGameInfo(GameInfo info) 
    {
        mGameInfo = info;
    }
    
    /** Define specific functionality.
     *
     * @throws Exception - any application specific exception that is thrown by
     * the function
     */
    public void run() throws Exception 
    {
        ServiceRegistry registry = ServiceRegistry.getDefaultInstance();
        IGiogoServer giogo = (IGiogoServer)registry.getServiceForName(
                                                        "com.giogo.IGiogoServer");
        giogo.remove(mGameInfo);
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
