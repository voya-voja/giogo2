/*
 * CardBiddingPlayer.java
 *
 * Created on August 8, 2003, 1:07 PM
 */

package com.giogo.game.card;

import com.giogo.game.bidding.Bid;
import com.giogo.game.bidding.Bidder;
import com.giogo.game.bidding.BiddingPlayer;

/**
 *
 * @author  inexum
 */
public class BiddingCardPlayer extends CardPlayer implements Bidder 
{
    private BiddingPlayer mBidder;
    
    /** Creates a new instance of CardBiddingPlayer */
    public BiddingCardPlayer(CardGame game)
    {
        super( game );
        mBidder = new BiddingPlayer( this );
    }
    
    public BiddingCardPlayer(String alias, CardGame game)
    {
        super( alias, game );
        mBidder = new BiddingPlayer( this );
    }
    
    public boolean bid( Bid[] offers ) 
    {
        return( mBidder.bid( offers ) );
    }
    
    public Bid bid() 
    {
        return( mBidder.bid() );
    }
    
    public boolean isBidding() 
    {
        return( mBidder.isBidding() );
    }
    
    public void bid(Bid bid) 
    {
        if(com.inexum.util.Debug.on)
            com.inexum.util.Debug.out("bid '" + bid.name() + "'");
        mBidder.bid( bid );
    }
    
    public void reset()
    {
        super.reset();
        mBidder.reset();
    }
    
    protected Bidder bidder() { return( mBidder ); }
}
