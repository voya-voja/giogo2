/*
 * BiddersIterator.java
 *
 * Created on September 26, 2003, 9:27 PM
 */

package com.giogo.game.bidding;

import com.giogo.game.RoundsPlayersIterator;

import java.util.HashSet;
import java.util.Set;
import java.util.List;

/**
 *
 * @author  inexum
 */
public class BiddersIterator extends RoundsPlayersIterator
{
    int mInitialBiddersSize = 0;
    Set mNonBidders = new HashSet();
    Bidder mWinner = null;
    
    /** Creates a new instance of BiddersIterator */
    public BiddersIterator(List bidders) 
    {
        super(bidders);
        mInitialBiddersSize = bidders.size();
    }
    
    /** Creates a new instance of BiddersIterator */
    public BiddersIterator(List bidders, int atIndex) 
    {
        super(bidders, atIndex);
        mInitialBiddersSize = bidders.size();
    }
    
    public boolean hasNext() 
    {
        Bidder bidder = (Bidder)super.current();
        if(!bidder.isBidding())
            mNonBidders.add(bidder);
        if(com.inexum.util.Debug.on)
            com.inexum.util.Debug.out("for " + mNonBidders.size() + ": " +
                            (mNonBidders.size() + 1 < mInitialBiddersSize 
                && (round() == 1 && mNonBidders.size() < mInitialBiddersSize)));

        // still bidding more than one
        boolean noWinner = mNonBidders.size() + 1 < mInitialBiddersSize;
        // still 1st round and all did not pass
        noWinner |= round() == 1 && mNonBidders.size() < mInitialBiddersSize;

        return(noWinner);
    }
    
    public Object next() 
    {
        if(!hasNext()) return(null);
        
        Object bidder = (Bidder)super.next();
        while(mNonBidders.contains(bidder))
        {
            bidder = (Bidder)super.next();
        }
        if(com.inexum.util.Debug.on)
            com.inexum.util.Debug.out("" + ((com.giogo.game.Player)bidder).id() + 
                                            "."+ ((com.giogo.game.Player)bidder).alias());
        return(bidder);
    }
    
    public Bidder nextBidder() 
    {
        return((Bidder)next());
    }
    
    public Bidder winner() 
    {
        if(isStart()) return null;
        
        
        Bidder bidder = (Bidder)current();
        if(bidder == mWinner) return(mWinner);
        
        if(bidder.isBidding() && 
            (mWinner == null || bidder.bid().greaterEqual(mWinner.bid())))
        {
            mWinner = bidder;
            if(com.inexum.util.Debug.on)
                com.inexum.util.Debug.out("" + ((com.giogo.game.Player)mWinner).id() + 
                                            "."+ ((com.giogo.game.Player)mWinner).alias());
        }
        return(mWinner);
    }
}
