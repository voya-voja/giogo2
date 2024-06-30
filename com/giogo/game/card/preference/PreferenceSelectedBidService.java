/*
 * PreferenceSelectedBid.java
 *
 * Created on August 29, 2003, 4:24 PM
 */

package com.giogo.game.card.preference;


import com.giogo.game.bidding.Bid;
import com.giogo.game.bidding.Bidder;
import com.giogo.game.bidding.Bidding;
import com.giogo.game.bidding.SelectedBidService;

/**
 *
 * @author  inexum
 */
public class PreferenceSelectedBidService extends SelectedBidService
{
    /** Creates a new instance of PreferenceSelectedBid */
    public PreferenceSelectedBidService() 
    {
        super("PreferenceSelectedBid");
    }
    
    protected void run(Bidding bidding, Bidder bidder, Bid bid) throws Exception 
    {
        if(com.inexum.util.Debug.on)
            com.inexum.util.Debug.out("bid '" + bid.name() + "'");
        PreferenceBid prefBid = (PreferenceBid)bid;
        if(prefBid.equals(PreferenceBid.cSame))
        {
            PreferenceBid.withoutSame();
            Bidder winner = bidding.biddingWinner();
            bidder.bid(winner.bid());
        }
        else
        {
            PreferenceBid.withSame();
            super.run(bidding, bidder, bid);
        }
    }
}
