/*
 * CardBiddingGameSpecification.java
 *
 * Created on August 8, 2003, 1:33 PM
 */

package com.giogo.game.card;

import com.giogo.game.bidding.BiddingSpecification;
import com.giogo.game.Game;
import com.giogo.game.GameException;
import com.giogo.game.Player;

import java.util.Map;

/**
 *
 * @author  inexum
 */
public abstract class BiddingCardGameSpecification extends CardGameSpecification 
                                            implements BiddingSpecification 
{
    
    /** Creates a new instance of CardBiddingGameSpecification */
    public BiddingCardGameSpecification(String name, int maxPlayerNo, 
                                        DeckSpec deckSpec) throws GameException
    {
        super(name, maxPlayerNo, deckSpec);
    }

    public BiddingCardGameSpecification(String name, int requiredPlayerNo, 
                                        DeckSpec deckSpec, 
                                        boolean newDeckEachDeal) 
                                                        throws GameException
    {
        super(name, requiredPlayerNo, deckSpec, newDeckEachDeal);
    }

    public Player createPlayer(String alias, Game game) 
    {
        return(new BiddingCardPlayer(alias, (CardGame)game));
    }

    protected Game createGame(Map parameters)
    {
        return(new BiddingCardGame(parameters, this, createDeck()));
    }
}
