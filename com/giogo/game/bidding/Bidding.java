/*
 * Bidding.java
 *
 * Created on June 24, 2003, 6:47 PM
 */

package com.giogo.game.bidding;

/**
 *
 * @author  inexum
 */
public interface Bidding 
{    
    public Bidder biddingWinner();
    
    public BiddersIterator bidders();
}
