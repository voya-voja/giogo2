/*
 * Bidder.java
 *
 * Created on August 1, 2003, 7:37 PM
 */

package com.giogo.game.bidding;

import com.giogo.game.Game;
import com.giogo.game.Player;
import com.giogo.game.bidding.ActivateBidding;

/**
 *
 * @author  inexum
 */
public class BiddingPlayer implements Bidder 
{
    private Player mPlayer;
    private Bid mBid;
    
    /** Creates a new instance of Bidder */
    public BiddingPlayer( Player player )
    {
        mPlayer = player;
    }
    
    public boolean bid( Bid[] offers )
    {
        if( com.inexum.util.Debug.on )
            com.inexum.util.Debug.out( "offers # " + offers.length );
        Game game = mPlayer.game();
        BiddingSpecification spec = (BiddingSpecification)game.specification();
        game.receive( new ActivateBidding( offers, game, 
                                            spec.biddingPresentersNames(), 
                                            mPlayer ) );
        mPlayer.waitResponse();        
        game.receive( new BiddingUpdate( game, mPlayer.id(), mBid ) );
        
        return( !mBid.isStop() );
    }
    
    public Bid bid() { return( mBid ); }
    
    public void bid( Bid bid ) 
    { 
        if( com.inexum.util.Debug.on )
            com.inexum.util.Debug.out( "Bid " + bid.name()  );
        mBid = bid;
        mPlayer.receivedResponse();
    }

    public boolean isBidding() 
    {
        return( mBid == null || !mBid.isStop() );
    }    
    
    public void reset()
    {
        mBid = null;
    }
    
}
