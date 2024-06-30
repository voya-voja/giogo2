/*
 * CardGameLoginService.java
 *
 * Created on June 15, 2003, 6:14 PM
 */

package com.giogo.game.card;

import com.giogo.game.Game;
import com.giogo.game.Player;
import com.giogo.game.LoginService;

/**
 *
 * @author  inexum
 */
public class CardGameLoginService extends LoginService 
{
    /** Creates a new instance of CardGameLoginService */
    public CardGameLoginService() throws java.rmi.RemoteException, 
                                                java.net.MalformedURLException
    {
    }
    
    /** Initialization method that will be called after the applet is loaded
     *  into the browser.
     */
    public Player createPlayer(String alias, Game game) 
    {
        return(new CardPlayer(alias, (CardGame)game));
    }
    
}
