/*
 * ActivateBidding.java
 *
 * Created on August 26, 2003, 6:45 PM
 */

package com.giogo.game.bidding;

import com.giogo.game.Game;
import com.giogo.game.Player;
import com.giogo.ActivateClientSession;
import com.giogo.game.ActivatePlayer;

/**
 *
 * @author  inexum
 */
public class ActivateBidding extends ActivatePlayer 
{
    Bid[] mOffers;
    
    /** Creates a new instance of ActivateBidding */
    public ActivateBidding(Bid[] offers, Game game, 
                        String[] presenterNames, Player player) 
    {
        super(game, presenterNames, player);
        mOffers = offers;
    }
    
    protected ActivateClientSession activation() { return(new SelectBid()); }
    
    protected Object presenterInfo(String presenterName) 
    { 
        if(com.inexum.util.Debug.on)
            com.inexum.util.Debug.out("Offers #" + mOffers.length);
        return(mOffers); 
    }
}
