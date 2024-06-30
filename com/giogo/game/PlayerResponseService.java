/*
 * PlayerResponseService.java
 *
 * Created on August 28, 2003, 6:01 PM
 */

package com.giogo.game;

import com.inexum.IPC.IProcedure;
import com.giogo.ResponseService;
import com.giogo.ClientResponse;

/**
 *
 * @author  inexum
 */
public abstract class PlayerResponseService extends ResponseService 
{
    /** Creates a new instance of PlayerResponseService */
    public PlayerResponseService(String serviceName) 
    {
        super(serviceName);
    }
    
    /** Define specific functionality.
     *
     * @return an object that the function returns
     * @throws Exception - any application specific exception that is thrown by
     * the function
     */
    public Object run(IProcedure procedure) throws Exception 
    {
        ClientResponse response = (ClientResponse)procedure;
        String message = response.message();
        if(message != null)
        {
            Game game = GamesManager.Instance().getGame(response.gameId());
            game.receive(new MessageBroadcast(game, message));
        }
        super.run(procedure);
        return(null);
    }
}
