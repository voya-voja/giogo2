/*
 * PreferenceBid.java
 *
 * Created on July 30, 2003, 4:01 PM
 */

package com.giogo.game.card.preference;

import com.giogo.game.bidding.Bid;
import com.giogo.game.bidding.Bidder;
import com.giogo.game.card.Suit;
import com.giogo.game.card.CharacterClassicSuit;

/**
 *
 * @author  inexum
 */
public class PreferenceBid extends Bid
{
    static int gNextOrdinal = -3;
    int mOrdinal = gNextOrdinal++;
    
    static int gWithoutSame = 0;
    
    /** Creates a new instance of PreferenceBid */
    private PreferenceBid(String name) 
    {
        super(name);
    }

    public boolean isContract() { return(cTwo.ordinal() <= mOrdinal && mOrdinal <= cSeven.ordinal()); }
    public boolean isGame() { return(mOrdinal == cGame.ordinal()); }
    public boolean isBettel() { return(mOrdinal == cBettel.ordinal()); }
    public boolean isSans() { return(mOrdinal == cSans.ordinal()); }
    public boolean isSix() { return(mOrdinal == cSix.ordinal()); }
    
    public boolean isTrump()
    {
        return(mOrdinal <= cFive.mOrdinal && mOrdinal >= cTwo.mOrdinal);
    }
    
    public boolean isTrump(Suit suit)
    {
        if(mOrdinal <= cFive.mOrdinal && mOrdinal >= cTwo.mOrdinal)
            return(suit.compareTo(gmSuits[mOrdinal-2]) == 0);
        return(false);
    }
    
    public static Bid[] availableBids(Bidder winner, int biddingRound) 
    {
        PreferenceBid winningBid = cPass;
        if(winner != null)
            winningBid = (PreferenceBid)winner.bid();
        if(biddingRound == 1)
            return(gmAvailableBids[0][winningBid.ordinal()]);
        else
            return(gmAvailableBids[1 + gWithoutSame][winningBid.ordinal()]);
    }
    
    public static Bid[] availableDefences(int defencePlayers) 
    {
        return(gmAvailableBids[4][defencePlayers]);
    }
    
    public static Bid[] availableContracts(Bidder winner) 
    {
        PreferenceBid winningBid = (PreferenceBid)winner.bid();
        return(gmAvailableBids[3][winningBid.ordinal()]);
    }
    
    public static Bid[] availableGames(Bidder winner) 
    {
        Bid[] mBids = {cTwo, cThree, cFour, cFive}; 
        return(mBids);
    }
    
    public static void withoutSame() { gWithoutSame = 1; }
    public static void withSame() { gWithoutSame = 0; }
    
    public boolean isStop() 
    {
        int ordinal = ordinal();
        return(ordinal < cTwo.ordinal() || cSans.ordinal() < ordinal); 
    }

    public boolean greaterEqual(Bid bid) 
    {
        return(bid == null || ordinal() >= ((PreferenceBid)bid).ordinal());
    }
    
    public boolean greater(Bid bid) 
    {
        return(bid == null || ordinal() > ((PreferenceBid)bid).ordinal());
    }
    
    public boolean equals(Bid bid) 
    {
        return(bid !=null && ordinal() == ((PreferenceBid)bid).ordinal());
    }

    public int points()
    {
         if(mOrdinal >= cTwo.mOrdinal && mOrdinal < cGame.mOrdinal)
            return(2*mOrdinal);
        else if(mOrdinal <= cSans.mOrdinal)
            return(2*mOrdinal - 4);
        return(0);
    }
    
    public int defencePoints(int tricksNo)
    {
        if(mOrdinal == cBettel.mOrdinal && tricksNo > 0)
            return(70);
        if(mOrdinal == cSix.mOrdinal && tricksNo > 0) 
            return(60);
        else
            return(tricksNo * points());
    }

    public static class Contra extends PreferenceBid
    {
        private int   mFactor = 1;
        private final int cContraMultiplier = 2;
        
        /** Creates a new instance of Game preference bid */
        private Contra() 
        {
            super("contra");
        }

        public void reset() 
        {
            factor(1);
        }
        
        public void factor(int factor) 
        {
            mFactor = factor;
        }
        
        public int factor() { return(mFactor); }
        
        public String name()
        {
            int value = cContraMultiplier * mFactor;
            String name = super.name() + "[" + Integer.toString(value) + "x]";
            return(name);
        }
    }
    
    public static class Game extends PreferenceBid
    {
        PreferenceBid   mBid;
        
        /** Creates a new instance of Game preference bid */
        private Game() 
        {
            super("game");
        }

        public void bid(PreferenceBid bid) 
        {
            mBid = bid; 
        }
        
        private PreferenceBid bid() { return(mBid); }
        
        public boolean greaterEqual(Bid bid) 
        {
            if(mBid != null && super.equals(bid))
                return(mBid.greaterEqual(((PreferenceBid.Game)bid).bid()));
            else
                return(super.greaterEqual(bid));
        }

        public boolean greater(Bid bid) 
        {
            if(mBid != null && super.equals(bid))
                return(mBid.greater(((PreferenceBid.Game)bid).bid()));
            else
                return(super.greater(bid));
        }

        public boolean equals(Bid bid) 
        {
            return(super.equals(bid) && 
                           ((mBid == null && bid == null) || 
                                (mBid != null &&
                                    mBid.equals(((PreferenceBid.Game)bid).bid()))));
        }

        public boolean isTrump()
        {
            return(true);
        }
        
        public boolean isTrump(Suit suit)
        {
            return(mBid.isTrump(suit));
        }
        
        public int points()
        {
            return(2 + mBid.points());
        }
        
        public String name()
        {
            String name = super.name();
            if(mBid != null)
                name += "[" + mBid.name() + "]";
            return(name);
        }
    }

    public static PreferenceBid cInvited = new PreferenceBid("invited");
    public static PreferenceBid cInvite = new PreferenceBid("invite");
    public static PreferenceBid cContra = new Contra();
    public static PreferenceBid cPass = new PreferenceBid("pass");
    public static PreferenceBid cPlay = new PreferenceBid("play");
    public static PreferenceBid cTwo = new PreferenceBid("2");
    public static PreferenceBid cThree = new PreferenceBid("3");
    public static PreferenceBid cFour = new PreferenceBid("4");
    public static PreferenceBid cFive = new PreferenceBid("5");
    public static PreferenceBid cSix = new PreferenceBid("6");
    public static PreferenceBid cSeven = new PreferenceBid("7");
    public static PreferenceBid cGame = new Game();
    public static PreferenceBid cBettel = new PreferenceBid("bettel");
    public static PreferenceBid cSans = new PreferenceBid("sans");
    public static PreferenceBid cSame = new PreferenceBid("same");
    
    protected int ordinal() { return(mOrdinal); }
    
    static PreferenceBid[][][] gmAvailableBids = { 
                                    { 
           /*pass*/                     {cPass,cTwo, cGame, cBettel, cSans}, 
           /*invite*/                   {},
           /*  2 */                     {cPass,cThree, cGame, cBettel, cSans},
           /*  3 */                     {cPass,cFour, cGame, cBettel, cSans},
           /*  4 */                     {},
           /*  5 */                     {},
           /*  6 */                     {},
           /*  7 */                     {},
           /*game*/                     {cPass,cGame, cBettel, cSans},
           /*bettel*/                   {cPass,cSans},
           /*sans*/                     {cPass}, 
           /*same*/                     {}
                                    },
                                    { 
           /*pass*/                     {}, 
           /*invite*/                   {},
           /*  2 */                     {},
           /*  3 */                     {cPass, cSame, cFour},
           /*  4 */                     {cPass, cSame, cFive},
           /*  5 */                     {cPass, cSame, cSix},
           /*  6 */                     {cPass, cSame, cSeven},
           /*  7 */                     {cPass},
           /*game*/                     {},
           /*bettel*/                   {},
           /*sans*/                     {},
           /*same*/                     {}
                                    },
                                    { 
           /*pass*/                     {}, 
           /*invite*/                   {},
           /*  2 */                     {},
           /*  3 */                     {cPass, cFour},
           /*  4 */                     {cPass, cFive},
           /*  5 */                     {cPass, cSix},
           /*  6 */                     {cPass, cSeven},
           /*  7 */                     {cPass},
           /*game*/                     {},
           /*bettel*/                   {},
           /*sans*/                     {},
           /*same*/                     {}
                                    },
                                    { 
           /*pass*/                     {}, 
           /*invite*/                   {},
           /*  2 */                     {cTwo, cThree, cFour, cFive, cSix, cSeven},
           /*  3 */                     {cThree, cFour, cFive, cSix, cSeven},
           /*  4 */                     {cFour, cFive, cSix, cSeven},
           /*  5 */                     {cFive, cSix, cSeven},
           /*  6 */                     {cSix, cSeven},
           /*  7 */                     {cSeven},
           /*game*/                     {},
           /*bettel*/                   {},
           /*sans*/                     {},
           /*same*/                     {}
                                    },
            /*defence*/             { 
           /*basic*/                    {cPass, cPlay},
           /*invite*/                   {cPass, cInvite, cContra},
           /*contra*/                   {cPass, cContra},
                                    }                                
    };
    
    static Suit[] gmSuits = { CharacterClassicSuit.cClub,
                                CharacterClassicSuit.cDimond,
                                CharacterClassicSuit.cHeart,
                                CharacterClassicSuit.cSpade };
}
