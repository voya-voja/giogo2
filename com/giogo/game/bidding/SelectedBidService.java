/*
 * SelectedBid.java
 *
 * Created on August 28, 2003, 5:59 PM
 */

package com.giogo.game.bidding;

import com.giogo.game.PlayerResponseService;
import com.giogo.ClientResponse;
import com.giogo.game.Player;
import com.giogo.game.Game;
import com.giogo.game.GamesManager;

/**
 *
 * @author  inexum
 */
public class SelectedBidService extends PlayerResponseService 
{
    /** Creates a new instance of SelectedBid */
    public SelectedBidService() 
    {
        super("SelectedBid");
    }
     
    /** Creates a new instance of SelectedBid */
    public SelectedBidService(String serviceName) 
    {
        super(serviceName);
    }
     
    /** Define specific functionality.
     *
     * @param response - a response sent by the game view client
     * @throws Exception - any application specific exception that is thrown by
     * the function
     */
    public void run(ClientResponse response) throws Exception 
    {
        Bid bid = (Bid)response.response();
        if(bid == null) 
            throw new BiddingException("'null' bid is not allowed!");
        
        if(com.inexum.util.Debug.on)
            com.inexum.util.Debug.out("selected bid '" + bid.name() + "'");
        
        Game game = GamesManager.Instance().getGame(response.gameId());
        run((Bidding)game, (Bidder)game.getPlayer(response.alias()), bid);
    }
    
    protected void run(Bidding bidding, Bidder bidder, Bid bid) throws Exception
    {
        if(com.inexum.util.Debug.on)
            com.inexum.util.Debug.out("bid '" + bid.name() + "'");
        bidder.bid(bid);
    }
}
