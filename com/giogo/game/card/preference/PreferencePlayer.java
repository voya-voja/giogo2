/*
 * PreferencePlayer.java
 *
 * Created on October 17, 2003, 3:20 PM
 */

package com.giogo.game.card.preference;

import com.giogo.game.card.CardGame;
import com.giogo.game.card.BiddingCardPlayer;
import com.giogo.game.bidding.Bid;
import com.giogo.game.bidding.Bidder;
import com.giogo.game.card.BiddingCardGame;

import java.util.Iterator;

/**
 *
 * @author  inexum
 */
public class PreferencePlayer extends BiddingCardPlayer 
{

    public static class PlayerLocation
    {
        // Ordinal of next suit to be created
        private static int gmNextOrdinal = 0;

        // Assign an ordinal to this suit
        private final int mOrdinal = gmNextOrdinal++;
                
        public int ordinal() { return(mOrdinal); }
        
        public PlayerLocation oposite()
        {
            if(this == cLeft)
                return(cRight);
            else
                return(cLeft);
        }
    }
    
    public final static PlayerLocation cLeft = new PlayerLocation();
    public final static PlayerLocation cRight = new PlayerLocation();
    
    /** Creates a new instance of PreferencePlayer */
    public PreferencePlayer(CardGame game)
    {
        super( game );
    }
    
    public PreferencePlayer(String alias, CardGame game)
    {
        super( alias, game );
    }
     
    public void bid(Bid bid) 
    {
        if(com.inexum.util.Debug.on)
            com.inexum.util.Debug.out("bid '" + bid.name() + "'");
        if(PreferenceBid.cInvite.equals(bid) || PreferenceBid.cContra.equals(bid))
            inviteOtherPlayer();
        
        try
        {
            PreferenceBid.Game gameBid = (PreferenceBid.Game)bid();
            Preference game = (Preference)game();
            if(gameBid == null || game.isContraBidding())
                super.bid(bid);
            else
            {
                gameBid.bid((PreferenceBid)bid);
                synchronized( this )
                {
                    notifyAll();
                    if( com.inexum.util.Debug.on )
                        com.inexum.util.Debug.out( "NOTIFY " + this  );
                }
            }
        }
        catch( ClassCastException e )
        {
            super.bid(bid);
        }
    }

    private PreferencePlayer otherDefencePlayer()
    {
        if(com.inexum.util.Debug.on)
            com.inexum.util.Debug.out("");
        BiddingCardGame game = (BiddingCardGame)game();
        Iterator at = game.getPlayers().iterator();
        PreferencePlayer winner = (PreferencePlayer)game.biddingWinner();

        
        PreferencePlayer player = winner.left();
        if(player == this)
            player = winner.right();
        return(player);
    }

    private void inviteOtherPlayer()
    {
        if(com.inexum.util.Debug.on)
            com.inexum.util.Debug.out("");
        otherDefencePlayer().bid(PreferenceBid.cInvited);
    }
    
    public boolean isPlaying() 
    { 
        return(!PreferenceBid.cPass.equals(bid())); 
    }
    
    public boolean isContra() 
    { 
        return(PreferenceBid.cContra.equals(bid())); 
    }
    
    public boolean isInvited() 
    { 
        return(PreferenceBid.cInvited.equals(bid())); 
    }
    
    public int mainPoints() { return(mMainPoints); }
    
    public void mainPoints(int points)
    {
        if(points > 0)
            mMainPoints = -points;
        else
            mMainPoints = points;
    }
    
    public int defencePoints(PlayerLocation location)
    {
        return(mDefencePoints[location.ordinal()]);
    }
    
    public void defencePoints(PlayerLocation location, int points)
    {
        mDefencePoints[location.ordinal()] = points;
    }
   
    private int points(int bidPoints, int availablePoints)
    {
        if(bidPoints > -availablePoints)
           return(-availablePoints);
        else
            return(bidPoints);
    }
    
    public void calculateMainPoints(int availablePoints, int factor)
    {
        PreferenceBid winningBid = (PreferenceBid)bid();
        int bidPoints = factor * winningBid.points();

        if(mNoRefe > 0)
        {
            mNoRefe--;
            Preference game = (Preference)game();
            game.receive(new RefeUpdate(game));
        }

        boolean defencePlay = left().isPlaying() || right().isPlaying();
        if(defencePlay && winningBid.equals(PreferenceBid.cTwo))
        {
            boolean contraOnTwo = left().isContra() || right().isContra();
            defencePlay = contraOnTwo;
        }
        
        boolean tookRequiredTricksNo = tricksNo() >= cMainRequiredTricksNo;
        boolean bettelPlayed = winningBid.isBettel() || winningBid.isSix();
        boolean passedBettel = bettelPlayed && tricksNo() == 0;
        if(!defencePlay || tookRequiredTricksNo || passedBettel)
        {
            mMainPoints += points(bidPoints, availablePoints);
        }
        else if(bidPoints > -availablePoints) // availablePoints is negative
        {
           mMainPoints -= 2*bidPoints;   // double fault at the end
        }
        else
        {
            mMainPoints -= bidPoints;
        }
    }
    
    public void calculateDefencePoints(PlayerLocation location, 
                                                int availablePoints, int factor)
    {
        if(!isPlaying()) return;

        Preference preference = (Preference)game();
        PreferencePlayer winner = (PreferencePlayer)preference.biddingWinner();
        PreferenceBid winningBid = (PreferenceBid)winner.bid();

        if(isInvited())
        {
            boolean bettelPlayed = winningBid.isBettel() || winningBid.isSix();
            if(bettelPlayed)
            {
                int points = collectedPoints(availablePoints, factor);
                mDefencePoints[location.ordinal()] += points;
            }
            return;
        }
        
        PreferencePlayer otherDefencePlayer = otherDefencePlayer();
        
        if(winningBid.equals(PreferenceBid.cTwo))
        {
            if(!(isContra() || otherDefencePlayer.isContra())) return;
        } 
        
        int requiredTricksNo = cDefenceRequiredTricksNo;
        if(otherDefencePlayer.isInvited())
        {
            while(otherDefencePlayer.tricksNo() > 0)
                addTrick(otherDefencePlayer.giveTrick());
            requiredTricksNo += cDefenceRequiredTricksNo;
            if(isContra())
            {
                requiredTricksNo++; // for contra 5
            }
        }

        int points = collectedPoints(availablePoints, factor);
        mDefencePoints[location.ordinal()] += points;
        adjustMainPoints(requiredTricksNo, availablePoints, factor);
    }

    private void adjustMainPoints(int requiredTricksNo,
                                                int availablePoints, int factor)
    {
        Preference preference = (Preference)game();
        PreferencePlayer winner = (PreferencePlayer)preference.biddingWinner();
        PreferenceBid winningBid = (PreferenceBid)winner.bid();

        boolean bettelPlayed = winningBid.isBettel() || winningBid.isSix();
        boolean failedNonBettel = !bettelPlayed 
                                    && tricksNo() < requiredTricksNo;
        boolean failedContraBettel = bettelPlayed 
                                    && PreferenceBid.cContra.equals(bid())
                                    && winner.tricksNo() == 0;
        if(failedNonBettel || failedContraBettel)
        {
            int bidPoints = factor * winningBid.points();
            if(bidPoints > -availablePoints)    // availablePoints is negative
            {
               mMainPoints -= 2*bidPoints;   // double fault at the end
            }
            else
            {
                mMainPoints -= bidPoints;
            }
        }        
    }
    
    public int collectedPoints(int availablePoints, int factor)
    {
        Preference preference = (Preference)game();
        PreferencePlayer winner = (PreferencePlayer)preference.biddingWinner();
        PreferenceBid winningBid = (PreferenceBid)winner.bid();

        int points;
        boolean bettelPlayed = winningBid.isBettel() || winningBid.isSix();
        if(bettelPlayed)
        {
            points = factor * winningBid.defencePoints(winner.tricksNo());
        }
        else if(winningBid.points() <= -availablePoints)
        {
            points = factor * winningBid.defencePoints(tricksNo());
        }
        else
        {
            points =  tricksNo() * -availablePoints;
        }
        return(points);
    }
    public PreferencePlayer left()
    {
        if(mLeft == null)
            assignPlayers();
        return(mLeft);      
    }
    
    public PreferencePlayer right()
    {
        if(mRight == null)
            assignPlayers();
        return(mRight);
    }
    
    private void assignPlayers()
    {
        for(Iterator at = game().getPlayers().iterator(); at.hasNext();)
        {
            PreferencePlayer player = (PreferencePlayer)at.next();
            if(player != this)
            {
                if((3 + id() - player.id()) % 3 == 2)
                {
                    mLeft = player;
                }
                else
                {
                    mRight = player;
                }
            }
        }
    }

    public void newRefe() { mNoRefe++; }
    
    public int refe() { return(mNoRefe); }
    
    public boolean isRefe() { return(mNoRefe > 0); }
    
    public void defenceBid(Bid[] offers)
    {
        bidder().reset();
        bid(offers);
    }

    private int mMainPoints = 0;
    private int mDefencePoints[] = {0, 0};
    private int mNoRefe = 0;
    
    private PreferencePlayer mLeft = null;
    private PreferencePlayer mRight = null;
    
    private final int cDefenceRequiredTricksNo = 2;
    private final int cMainRequiredTricksNo =  6;
}
