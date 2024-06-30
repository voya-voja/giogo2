/*
 * PreferenceContractBidding.java
 *
 * Created on Jun 03, 2003, 11:01 PM
 */

package com.giogo.game.card.preference;

import com.giogo.game.Game;
import com.giogo.game.Condition ;
import com.giogo.game.card.CardGame;
import com.giogo.game.card.BiddingCardGame;
import com.giogo.game.bidding.BiddersIterator;
import com.giogo.game.bidding.Bidder;
import com.giogo.game.bidding.Bid;
import com.giogo.game.card.ShowWidows;

import java.util.List;

/**
 *
 * @author  Giogo
 * @version 1.0
 */

public class PreferenceContractBidding extends Condition
{
    private PreferenceBid   mBid;
    private ShowWidows      mShowWidows = new ShowWidows();
    private PreferenceSelectPlayCards mSelectPlayCards = new PreferenceSelectPlayCards();

    protected PreferenceContractBidding()
    {
    }
    
    public void execute(Game game)
    {
        if(com.inexum.util.Debug.on)
            com.inexum.util.Debug.out("game #" + game.id());
        mBid = null;
        contractBidding((BiddingCardGame)game);
        if(mBid == null)
            ((Preference)game).refe();
        else if(mBid.isContract())
            contractDeclaration((BiddingCardGame)game);
        else if(mBid.isGame())
            gameDeclaration((BiddingCardGame)game);
    }

    protected void contractBidding(BiddingCardGame game)
    {
        if(com.inexum.util.Debug.on)
            com.inexum.util.Debug.out("game #" + game.id());
        
        BiddersIterator at = new PreferenceBiddersIterator(game.getPlayers(), 
                                                        game.firstPlayer().id());
        game.bidders(at);
        while(at.hasNext())
        {
            Bid[] bids = PreferenceBid.availableBids(at.winner(), at.round());
            Bidder bidder = at.nextBidder();
            if(bidder.bid(bids))
            {
                mBid = (PreferenceBid)bidder.bid();
                if(mBid.isSans()) break;
            }
        }
    }

    protected void contractDeclaration(BiddingCardGame game)
    {
        if(com.inexum.util.Debug.on)
            com.inexum.util.Debug.out("game #" + game.id());
        
        mShowWidows.execute(game);
        Bidder winner = game.biddingWinner();
        if(winner == null) return;
        
        Bid[] bids = PreferenceBid.availableContracts(winner);
        winner.bid(bids);
        mSelectPlayCards.execute(game);
    }

    protected void gameDeclaration(BiddingCardGame game)
    {
        if(com.inexum.util.Debug.on)
            com.inexum.util.Debug.out("game #" + game.id());

        GameBiddersIterator at = new GameBiddersIterator(game.getPlayers(),
                                                    game.firstPlayer().id());
        game.bidders(at);
        while(at.hasNext())
        {
            Bid[] bids = PreferenceBid.availableGames(at.winner());
            Bidder bidder = at.nextBidder();
            if(bidder != null)
                bidder.bid(bids);
        }
    }

    protected boolean test(Game game) 
    {
        if(mBid != null && com.inexum.util.Debug.on)
            com.inexum.util.Debug.out("" + (mBid.isContract() || mBid.isGame()
                                        || mBid.isBettel() || mBid.isSans()));
        return(mBid != null && (mBid.isContract() || mBid.isGame() 
                                    || mBid.isBettel() || mBid.isSans()));
    }
    
}