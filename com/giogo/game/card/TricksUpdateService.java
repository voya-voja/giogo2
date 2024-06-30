/*
 * TricksUpdateService.java
 *
 * Created on December 31, 2005, 7:40 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.giogo.game.card;

import com.giogo.game.PlayerRequestService;
import com.giogo.ClientRequest;
import com.giogo.game.GamesManager;

/**
 *
 * @author nvojinovic
 */
public class TricksUpdateService extends PlayerRequestService
{
    /** Creates a new instance of TricksUpdateService */
    public TricksUpdateService() 
    {
        super("TricksUpdate");
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

        CardGame game = (CardGame)GamesManager.Instance().getGame(request.gameId());
        CardPlayer player = (CardPlayer)game.getListener(request.alias());
        TricksUpdate action = new TricksUpdate(game, player);
        game.receive(action);
        return(player.getViewAction());
    }
}
