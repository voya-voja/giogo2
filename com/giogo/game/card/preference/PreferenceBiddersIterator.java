/*
 * PreferenceBiddersIterator.java
 *
 * Created on October 16, 2003, 8:33 AM
 */

package com.giogo.game.card.preference;

import com.giogo.game.bidding.Bidder;

import java.util.List;

/**
 *
 * @author  inexum
 */
public class PreferenceBiddersIterator extends com.giogo.game.bidding.BiddersIterator {
    
    /** Creates a new instance of PreferenceBiddersIterator */
    public PreferenceBiddersIterator( List bidders ) 
    {
        super( bidders );
    }
    
    /** Creates a new instance of BiddersIterator */
    public PreferenceBiddersIterator( List bidders, int atIndex ) 
    {
        super( bidders, atIndex );
    }
    
    public boolean hasNext() 
    {
        boolean noWinner = super.hasNext();
        Bidder winner = winner();
        if( winner == null )
            return( noWinner );
        PreferenceBid winnerBid = (PreferenceBid)winner.bid();
        return( noWinner && !((winnerBid.isGame() 
                                || winnerBid.isBettel() 
                                || winnerBid.isSans()) && round() >1) );
    }    
}
