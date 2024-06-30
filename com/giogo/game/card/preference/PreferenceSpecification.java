/*
 * Preference.java
 *
 * Created on June 8, 2003, 8:50 PM
 */

package com.giogo.game.card.preference;

import com.giogo.game.*;
import com.giogo.game.card.*;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author  inexum
 */
public class PreferenceSpecification extends BiddingCardGameSpecification
{
    private static Suit[] gmSuits = { CharacterClassicSuit.cSpade,
                                        CharacterClassicSuit.cDimond,
                                        CharacterClassicSuit.cHeart,
                                        CharacterClassicSuit.cClub };

    private static String[] gmPlayerAppletNames = { "player", 
                                                "player.right", 
                                                "player.left" };

    private static String[] gmThrowCardAppletNames = { "card.throw", 
                                                "card.throw.right", 
                                                "card.throw.left" };

    private static String[] gmPlayerCardAppletNames = { "card.0",
                                                "card.1", 
                                                "card.2", 
                                                "card.3", 
                                                "card.4", 
                                                "card.5", 
                                                "card.6", 
                                                "card.7", 
                                                "card.8", 
                                                "card.9"
    };

    private static String[][] gmPlayerTrickAppletNames = { 
        {"trick.00", "trick.01", "trick.02"},
        {"trick.10", "trick.11", "trick.12"}, 
        {"trick.20", "trick.21", "trick.22"}, 
        {"trick.30", "trick.31", "trick.32"},
        {"trick.40", "trick.41", "trick.42"},
        {"trick.50", "trick.51", "trick.52"},
        {"trick.60", "trick.61", "trick.62"},
        {"trick.70", "trick.71", "trick.72"},
        {"trick.80", "trick.81", "trick.82"},
        {"trick.90", "trick.91", "trick.92"}
    };

    private static String[] gmWidowAppletNames = { "card.widow.0", 
                                                "card.widow.1" };
                                                
    private static String[] gmBiddingAppletNames = { "bidboard" };
                                                
    private static List gmSteps;
    /** Creates a new instance of Preference */
    public PreferenceSpecification() throws GameException
    {
        super("Preference", 3, new High32DeckSpec(gmSuits));
    }
    
    protected List gameSteps() 
    {
        if(gmSteps != null) return gmSteps;

        gmSteps = new ArrayList();
        gmSteps.add(new PlayersGameLogin(3));
        gmSteps.add(new PreferenceScoreInitialize());
        
        List playSteps = new ArrayList();
            playSteps.add(new CollectCards());
            playSteps.add(new RestartGame(1));
            playSteps.add(new AllPlayersDeal(5, true));
            playSteps.add(new WidowsDeal(2));
            playSteps.add(new AllPlayersDeal(5));
            playSteps.add(new AllPlayersSortCards());

            List contractBidingSteps = new ArrayList();
            playSteps.add(new IfElsePhases(new PreferenceContractBidding(), 
                                                    contractBidingSteps));

                List defenceBidingSteps = new ArrayList();
                contractBidingSteps.add(new IfElsePhases(
                                                new PreferenceDefenceBidding(), 
                                                defenceBidingSteps));
                    defenceBidingSteps.add(new PlayPreference());


        
        gmSteps.add(new WhilePhase(new PreferenceScoreUpdate(), 
                                                        playSteps, false));

        gmSteps.add(new PreferenceFinalScore());
        return(gmSteps);
    }
    
    public String[] playerPresenterNames() 
    {
        return(gmPlayerAppletNames);
    }
    
    public String playersPresenterName() 
    {
        return(gmPlayerAppletNames[0]);
    }
    
    public String[] playerCardPresenterNames()
    {
        return(gmPlayerCardAppletNames);
    }

    public String[][] playerTricksPresenterNames()
    {
        return(gmPlayerTrickAppletNames);
    }

    public String[] throwCardPresenterNames()
    {
        return(gmThrowCardAppletNames);
    }
    
    public String[] widowCardPresenterNames() 
    {
        return(gmWidowAppletNames);
    }
    
    public String[] biddingPresentersNames() 
    {
        return(gmBiddingAppletNames);
    }

    protected Game createGame(Map parameters)
    {
        return(new Preference(parameters, this, createDeck()));
    }

    public Player createPlayer(String alias, Game game) 
    {
        return(new PreferencePlayer(alias, (CardGame)game));
    }
}
