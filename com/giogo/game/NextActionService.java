/*
 * NextActionService.java
 *
 * Created on June 8, 2003, 5:22 PM
 */

package com.giogo.game;

import com.giogo.game.PlayerRequestService;
import com.giogo.ClientRequest;

/**
 *
 * @author  inexum
 */
public class NextActionService extends PlayerRequestService
{
    
    /** Creates a new instance of NextActionRequest */
    public NextActionService() throws java.rmi.RemoteException, 
                                                java.net.MalformedURLException
    {
       super("NextAction");
    }
    
    /** Creates a new instance of NextActionRequest */
    public NextActionService(String service) throws java.rmi.RemoteException, 
                                                java.net.MalformedURLException
    {
       super(service);
    }
    
    /** Define specific functionality.
     *
     * @return an object that the function returns
     * @throws Exception - any application specific exception that is thrown by
     * the function
     */
    public Object run(ClientRequest request) throws Exception 
    {
        if(com.inexum.util.Debug.on)
            com.inexum.util.Debug.out(request.alias());

        if(Thread.currentThread().getName().startsWith("Thread"))
            System.out.println("Thread");
        
        Game game = GamesManager.Instance().getGame(request.gameId());
        Listener listener = game.getListener(request.alias());
        return(listener.getViewAction());
    }
    
}
