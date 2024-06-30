/*
 * GameBiddersIterator.java
 *
 * Created on October 13, 2003, 6:38 PM
 */

package com.giogo.game.card.preference;

import com.giogo.game.bidding.Bidder;
import com.giogo.game.bidding.BiddersIterator;

import java.util.List;

/**
 *
 * @author  inexum
 */
public class GameBiddersIterator extends BiddersIterator 
{
    Bidder      mWinner;
    
    /** Creates a new instance of GameBiddersIterator */
    public GameBiddersIterator( List bidders ) 
    {
        super( bidders );
    }
    
    /** Creates a new instance of BiddersIterator */
    public GameBiddersIterator( List bidders, int atIndex ) 
    {
        super( bidders, atIndex );
    }
    
    public boolean hasNext()
    {
        return(round() == 1);
    }
    
    public Object next()
    {
        return( nextBidder() );
    }
    
    public Bidder nextBidder()
    {
        Bidder bidder;
        PreferenceBid bid;
        do
        {
            bidder = (Bidder)super.next();
            bid = (PreferenceBid)bidder.bid();
        } while( hasNext() && !bid.isGame() );
        if( !hasNext() && !bid.isGame() )
            bidder = null;
        return( bidder );
    }
  
    public Bidder winner() 
    { 
        Bidder bidder = (Bidder)current();
        if( mWinner == null || bidder.bid().greater( mWinner.bid() ) )
            mWinner = bidder;
        
        if( mWinner != null && com.inexum.util.Debug.on )
            com.inexum.util.Debug.out( "" + ((com.giogo.game.Player)mWinner).id() + 
                                        "."+ ((com.giogo.game.Player)mWinner).alias() );
        return( mWinner );
    }
}
