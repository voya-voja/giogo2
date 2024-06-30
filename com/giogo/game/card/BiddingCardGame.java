/*
 * BiddingCardGame.java
 *
 * Created on June 24, 2003, 6:45 PM
 */

package com.giogo.game.card;

import com.giogo.game.bidding.Bidding;
import com.giogo.game.bidding.Bidder;
import com.giogo.game.bidding.BiddersIterator;
import com.giogo.game.bidding.BiddingUpdate;

import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author  inexum
 */
public class BiddingCardGame extends CardGame implements Bidding 
{
    private BiddersIterator mBidders;
    
    /** Creates a new instance of CardBiddingGame */
    public BiddingCardGame(Map parameters, BiddingCardGameSpecification spec, Deck deck) 
    {
        super(parameters, spec, deck);
    }
    
//    public void biddingWinner(Bidder winner) { mWinner = winner; }
    
    public Bidder biddingWinner() 
    {
        if(mBidders == null)
            return(null);
        return(mBidders.winner());
    }
    
    public BiddersIterator bidders()
    {
        if(mBidders == null)
            mBidders = new BiddersIterator(getPlayers(), firstPlayer().id());
        return(mBidders);
    }
    
    public void bidders(BiddersIterator bidders)
    {
            mBidders = bidders;
    }
    
    public void oneMore()
    {
        super.oneMore();
        mBidders = null;
        
        Iterator at = getPlayers().iterator();
        while(at.hasNext())
        {
            BiddingCardPlayer player = (BiddingCardPlayer)at.next();
            receive(new BiddingUpdate(this, player.id()));
            receive(new TricksUpdate(this, player));
        }
    }
}
