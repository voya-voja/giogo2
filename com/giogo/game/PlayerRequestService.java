/*
 * PlayerRequestService.java
 *
 * Created on June 8, 2003, 5:36 PM
 */

package com.giogo.game;

import com.inexum.IPC.IProcedure;
import com.giogo.RequestService;
import com.giogo.ClientRequest;

/**
 *
 * @author  inexum
 */
public abstract class PlayerRequestService extends RequestService
{
    /** Constructs RequestService object.
     * @param service - the service registration name
     * @throw RemoteException if cannot be exported or bound to RMI registry
     */
    public PlayerRequestService(String service)
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
        String message = request.message();
        if(message != null)
        {
            Game game = GamesManager.Instance().getGame(request.gameId());
            game.receive(new MessageBroadcast(game, message));
        }
        return(super.run(procedure));
    }
}
