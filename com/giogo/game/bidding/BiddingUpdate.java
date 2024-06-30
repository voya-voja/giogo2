/*
 * BiddingUpdate.java
 *
 * Created on August 1, 2003, 6:35 PM
 */

package com.giogo.game.bidding;

import com.giogo.ClientAction;
import com.giogo.PresenterUpdate;
import com.giogo.ClientUpdate;

import com.giogo.game.Game;
import com.giogo.game.Player;
import com.giogo.game.UpdateAction;
import com.giogo.game.Listener;

import java.util.List;

/**
 *
 * @author  inexum
 */
public class BiddingUpdate extends UpdateAction 
{
    private int mBidderId;
    private Bid mBid;
    
    /** Creates a new instance of BiddingUpdate */
    public BiddingUpdate(Game game, int bidderId) 
    {
        super(game);
        mBidderId = bidderId;
    }
    
    /** Creates a new instance of BiddingUpdate */
    public BiddingUpdate(Game game, int bidderId, Bid bid) 
    {
        super(game);
        mBidderId = bidderId;
        mBid = bid;
    }
    
    protected ClientAction clientAction(int playerId)
    {
        List players = game().getPlayers();
        int playersNo = players.size();
        String[] presenters = game().specification().playerPresenterNames();
        int index = (presenters.length + mBidderId - playerId) % presenters.length;
        String[] row = {"bid", null};
        if(mBid != null)
            row[1] = mBid.name();
        ClientAction update = new PresenterUpdate(presenters[index], row);
        if(com.inexum.util.Debug.on)
            com.inexum.util.Debug.out("player: " + playerId + 
                                        " bidder: " + mBidderId + 
                                        " update presenter '" + 
                                        presenters[index] + "'");
        return(update);
    }
}
