/*
 * Preference.java
 *
 * Created on January 22, 2004, 9:00 PM
 */

package com.giogo.game.card.preference;

import com.giogo.game.card.BiddingCardGame;
import com.giogo.game.card.Deck;
import com.giogo.game.Player;
import com.giogo.game.bidding.Bid;

import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author  inexum
 */
public class Preference extends BiddingCardGame 
{
    /** Creates a new instance of Preference */
    public Preference(Map parameters, PreferenceSpecification spec, Deck deck) 
    {
        super(parameters, spec, deck);
        mMaxNoRefe = Integer.parseInt((String)parameters.get("refe"));
        mStartMainPoints = Integer.parseInt((String)parameters.get("points"));
    }
    
    public Player firstPlayer() 
    {
        PreferencePlayer leadPlayer = (PreferencePlayer)biddingWinner();
        if(leadPlayer == null)
            return(super.firstPlayer());
        Bid bid = leadPlayer.bid();
        if(bid != null && 
            (bid.equals(PreferenceBid.cSans) || bid.equals(PreferenceBid.cSeven)))
        {
            Player player = (Player)getPlayers().get((leadPlayer.id() + 2) % 3);
            if(player.isPlaying())
                return(player);
            else
                return((Player)getPlayers().get((leadPlayer.id() + 1) % 3));
        }
        else
            return(super.firstPlayer());
    }
    
    public int remainingPoints()
    {
        Iterator at = getPlayers().iterator();
        int points = 0;
        while(at.hasNext())
        {
            PreferencePlayer player = (PreferencePlayer)at.next();
            points += player.mainPoints();
        }
        return(points);
    }
    
    public boolean isNoMoreRefes()
    {
        return(mNoRefe == mMaxNoRefe);
    }
    
    public void refe()
    {
        if(isNoMoreRefes()) return;

        Iterator at = getPlayers().iterator();
        while(at.hasNext())
        {
            PreferencePlayer player = (PreferencePlayer)at.next();
            if(player.mainPoints() >= 0) return;
        }
        
        mNoRefe++;
        at = getPlayers().iterator();
        while(at.hasNext())
        {
            PreferencePlayer player = (PreferencePlayer)at.next();
            player.newRefe();
        }
        receive(new RefeUpdate(this));
    }
    
    public int startMainPoints() { return(mStartMainPoints); }
    
    public void contraFactor(int contraFactor) { mContraFactor = contraFactor; }
    
    public int contraFactor() { return(mContraFactor); }
    
    public void oneMore()
    {
        super.oneMore();
        mContraFactor = 1;
    }

    public void contraBidingBegin() { mContraBidding = true; }
    public void contraBidingEnd() 
    { 
        mContraBidding = false;
    }
    
    public boolean isContraBidding() { return(mContraBidding); }

    boolean mContraBidding = false;
    int mMaxNoRefe = 2;
    int mNoRefe = 0;
    int mStartMainPoints = 80;
    int mContraFactor = 1;
}
