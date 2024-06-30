/*
 * PreferenceDefenceBidding.java
 *
 * Created on Jun 03, 2003, 11:01 PM
 */

package com.giogo.game.card.preference;

import com.giogo.game.Game;
import com.giogo.game.bidding.Bidder;
import com.giogo.game.Condition;
import com.giogo.game.card.BiddingCardGame;
import com.giogo.game.card.BiddingCardPlayer;
import com.giogo.game.bidding.Bid;
import com.giogo.game.bidding.Bidder;
import com.giogo.game.PlayersIterator;
import com.giogo.game.bidding.BiddingUpdate;

import java.util.Iterator;

/**
 *
 * @author  Giogo
 * @version 1.0
 */

public class PreferenceDefenceBidding extends Condition
{
    // count of player that bided to play
    private int mPlayersCount = 0;
    private boolean mTwoBid = false;
    
    protected PreferenceDefenceBidding()
    {
    }
    
    public void execute(Game game)
    {
        if(com.inexum.util.Debug.on)
            com.inexum.util.Debug.out("game #" + game.id());

        mPlayersCount = 0;
        mTwoBid = false;
        basicBidding((Preference)game);
        if(mPlayersCount > 0)
            contraBiding((Preference)game);
        else if(mTwoBid)
            onBidTwo((Preference)game);
        
        Iterator at = game.getPlayers().iterator();
        while(at.hasNext())
        {
            PreferencePlayer player = (PreferencePlayer)at.next();
            game.receive(new BiddingUpdate(game, player.id(), player.bid()));
        }
    }
    
    protected void basicBidding(Preference game)
    {
        if(com.inexum.util.Debug.on)
            com.inexum.util.Debug.out("game #" + game.id());
        
        Bid[] bids = PreferenceBid.availableDefences(mPlayersCount);
        PreferencePlayer winner = (PreferencePlayer)game.biddingWinner();
        Bid winnerBid = winner.bid();
        mTwoBid = PreferenceBid.cTwo.equals(winnerBid);
        boolean bettel = !mTwoBid && (PreferenceBid.cSix.equals(winnerBid) ||
                            PreferenceBid.cBettel.equals(winnerBid)) ;
        Iterator at = new PlayersIterator(game.getPlayers(), winner.id());
        at.next();
        while(at.hasNext())
        {
            PreferencePlayer player = (PreferencePlayer)at.next();
            if(bettel)
            {
                player.bid(PreferenceBid.cPlay);
                mPlayersCount++;
            }
            else
            {
                player.defenceBid(bids);
                PreferenceBid bid = (PreferenceBid)player.bid();
                if(bid.equals(PreferenceBid.cPlay))
                    mPlayersCount++;
            }
        }
    }
    
    protected void contraBiding(Preference game)
    {
        if(com.inexum.util.Debug.on)
            com.inexum.util.Debug.out("Players # " + mPlayersCount + " winner");

        Bid[] bids = PreferenceBid.availableDefences(mPlayersCount);
        Iterator at = game.getPlayers().iterator();
        boolean contra = false;
        while(at.hasNext())
        {
            PreferencePlayer player = (PreferencePlayer)at.next();
            PreferenceBid bid = (PreferenceBid)player.bid();
            if(bid.equals(PreferenceBid.cPlay))
            {
                player.bid(bids);
                if(PreferenceBid.cPass.equals(player.bid()))
                    player.bid(PreferenceBid.cPlay);
                else if(PreferenceBid.cContra.equals(player.bid()))
                {
                    game.contraBidingBegin();
                    PreferencePlayer winner = (PreferencePlayer)game.biddingWinner();
                    game.contraFactor(recontraBidding(winner, player));
                    contra = true;
                    game.contraBidingEnd();
                }
            }
        }
        if(mTwoBid && !contra)
            onBidTwo(game);
    }
    
    protected void onBidTwo(Preference game)
    {
        mPlayersCount = 0;
        if(game.isNoMoreRefes()) return;
        
        game.biddingWinner().bid(PreferenceBid.cPass);
        game.bidders(null);
        game.refe();
    }
    
    private int recontraBidding(PreferencePlayer winner, PreferencePlayer player)
    {
        Bid[] bids = PreferenceBid.availableDefences(2);
        Bid playerBid = player.bid();
        Bid winnerBid = winner.bid();
        
        PreferencePlayer bidder = winner;
        boolean winnerBids = true;
        Bid bid;
        PreferenceBid.Contra contra = (PreferenceBid.Contra)PreferenceBid.cContra;
        do
        {
            contra.factor(contra.factor() * 2);
            bidder.bid(bids);
            bid = bidder.bid();
            if(winnerBids)
                bidder = player;
            else
                bidder = winner;
            winnerBids = !winnerBids;
        } while(PreferenceBid.cContra.equals(bid));

        player.bid(playerBid);
        winner.bid(winnerBid);
        
        int contraFactor = contra.factor();
        contra.reset();
        return(contraFactor);
    }
    
    protected boolean test(Game game) 
    {
        if(com.inexum.util.Debug.on)
            com.inexum.util.Debug.out("" + (mPlayersCount > 0));
        return(mPlayersCount > 0);
    }
    
}