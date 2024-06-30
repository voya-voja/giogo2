/*
 * GameInfoUpdate.java
 *
 * Created on April 30, 2004, 6:25 PM
 */

package com.giogo;

import com.inexum.IPC.IOperation;
import com.inexum.IPC.ServiceRegistry;

/**
 *
 * @author  inexum
 */
public class GameInfoUpdate implements IOperation 
{
    GameInfo    mGameInfo;
    
    /** Creates a new instance of GameInfoUpdate */
    public GameInfoUpdate(GameInfo info) 
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
        giogo.update(mGameInfo);
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
