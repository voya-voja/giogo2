/*
 * LoginService.java
 *
 * Created on June 13, 2003, 2:56 PM
 */

package com.giogo.game;

import com.giogo.game.PlayerRequestService;
import com.giogo.ClientRequest;
import com.giogo.ClientAction;
import com.giogo.StopClientSession;
import com.giogo.PresenterUpdate;
import com.giogo.ClientUpdate;

/**
 *
 * @author  inexum
 */
public class LoginService extends PlayerRequestService
{
    /** Creates a new instance of LoginService */
    public LoginService() throws java.rmi.RemoteException, java.net.MalformedURLException
    {
        super("Login");
    }
    
    /** Creates a new instance of LoginService */
    public LoginService(String service) throws java.rmi.RemoteException, java.net.MalformedURLException
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

        String alias = request.alias();
        Game game = GamesManager.Instance().getGame(request.gameId());
        ListenerSession session = (ListenerSession)Thread.currentThread();
        
        Specification spec = game.specification();
        Listener listener = game.getListener(alias);
        if(listener == null)
        {
            session.assign(game, alias);
            if(spec.allowNewPlayerToJoin(game.getPlayers().size()))
                return(newPlayer(game, alias));
            else
                return(newSpectator(game, alias));
        }
        else if(!listener.isActive())
        {
            session.assign(game, alias);
            listener.active();
            return(listener.replay());
        }
        session.halt();
        ClientAction[] actions = {
            new StopClientSession(),
            new PresenterUpdate(spec.loginPresenterName(), "Alias '" 
                                                    + alias +"' has been used!")
        };
        return(new ClientUpdate(actions));
    }
    
    protected Object newPlayer(Game game,String alias)
    {
        Player player = game.logonPlayer(alias);
        GamesManager.Instance().changeOf(game.id());
        return(player.getViewAction());
    }

    protected Object newSpectator(Game game,String alias)
    {
        Spectator spectator = new Spectator(alias, game);
        game.addSpectator(spectator);
        return(spectator.replay());
    }
}
