/*
 * CardGameSpecification.java
 *
 * Created on Jun 03, 2003, 11:01 PM
 */

package com.giogo.game.card;

import com.giogo.game.Specification;
import com.giogo.game.Game;
import com.giogo.game.GameException;
import com.giogo.game.Player;

import java.util.List;
import java.util.Map;

/**
 *
 * @author  Giogo
 * @version 1.0
 */

public abstract class CardGameSpecification extends Specification
{
    private List	mDeals;
    private DeckSpec	mDeckSpec;
    private boolean	mNewDeckEachDeal = false;

    public CardGameSpecification(String name, int maxPlayerNo, 
                                        DeckSpec deckSpec) throws GameException
    {
        super(name, maxPlayerNo);
        mDeckSpec = deckSpec;
    }

    public CardGameSpecification(String name, int requiredPlayerNo, 
                                    DeckSpec deckSpec, boolean newDeckEachDeal)
                                                            throws GameException
    {
        super(name, requiredPlayerNo);
        mNewDeckEachDeal = newDeckEachDeal;
    }
    
    protected Game createGame(Map parameters)
    {
        return(new CardGame(parameters, this, createDeck()));
    }

    public Player createPlayer(String alias, Game game)
    {
        return(new CardPlayer(alias, (CardGame)game));
    }

    public Deck createDeck() { return(mDeckSpec.createDeck()); }

    abstract public String[] playerCardPresenterNames();
    abstract public String[] widowCardPresenterNames();
    abstract public String[] throwCardPresenterNames();
    abstract public String[][] playerTricksPresenterNames();
}