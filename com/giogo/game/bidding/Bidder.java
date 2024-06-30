/*
 * BiddingPlayer.java
 *
 * Created on July 30, 2003, 4:10 PM
 */

package com.giogo.game.bidding;

/**
 *
 * @author  inexum
 */
public interface Bidder {
    
    public boolean isBidding();
    
    public boolean bid( Bid[] offers );
    
    public Bid bid();
    
    public void bid( Bid bid );
    
    public void reset();
}
