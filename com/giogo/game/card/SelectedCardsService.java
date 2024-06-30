/*
 * SelectedCards.java
 *
 * Created on October 23, 2003, 5:17 PM
 */

package com.giogo.game.card;

import com.giogo.game.PlayerResponseService;
import com.giogo.ClientResponse;
import com.giogo.game.Player;
import com.giogo.game.Game;
import com.giogo.game.GamesManager;

import java.util.ArrayList;

/**
 *
 * @author  inexum
 */
public class SelectedCardsService extends PlayerResponseService 
{
    /** Creates a new instance of SelectedCards */
    public SelectedCardsService() throws java.rmi.RemoteException, 
                                                java.net.MalformedURLException
    {
       super("SelectedCards");
    }
    
    /** Creates a new instance of SelectedCardsService */
    public SelectedCardsService(String service) throws java.rmi.RemoteException, 
                                                java.net.MalformedURLException
    {
       super(service);
    }   
    
    /** Define specific functionality.
     *
     * @param response - a response sent by the game view client
     * @throws Exception - any application specific exception that is thrown by
     * the function
     */
    public void run(ClientResponse response) throws Exception 
    {
        CardGame game = (CardGame)GamesManager.Instance().getGame(response.gameId());
        CardPlayer player = (CardPlayer)game.getPlayer(response.alias());
        
        player.selectedCards((Card[])response.response());
        player.receivedResponse();
    }
}
