/*
 * CollectCards.java
 *
 * Created on July 11, 2003, 4:24 PM
 */

package com.giogo.game.card;

import com.giogo.game.Game;
import com.giogo.game.Action;
import com.giogo.game.ActionDistribution;
import com.giogo.game.WipePresenterData;

import java.util.Iterator;

/**
 *
 * @author  inexum
 */
public class CollectCards extends ActionDistribution {
    
    /** Creates a new instance of CollectCards */
    public CollectCards() 
    {
    }
    
    protected Action createAction(Game game) 
    {
        CardGame cardGame = (CardGame)game;
        Deck deck = cardGame.deck();
        deck.add(cardGame.widows());

        Iterator players = game.getPlayers().iterator();
        CardPlayer player;
        while(players.hasNext())
        {
            player = (CardPlayer)players.next();
            deck.add(player.giveCards());
        }
        
        CardGameSpecification spec = (CardGameSpecification)cardGame.specification();
        String[] playerCardPresenters = spec.playerCardPresenterNames();
        String[] widowCardPresenters = spec.widowCardPresenterNames();
        String[][] playerTrickPresenters = spec.playerTricksPresenterNames();
        int trickSize = playerTrickPresenters[0].length;
        String[] presenters = new String[ playerCardPresenters.length 
                                    + widowCardPresenters.length
                                    + trickSize * playerTrickPresenters.length 
                                    + 1];
        
        for(int pCount = 0; pCount < playerCardPresenters.length; pCount++)
            presenters[ pCount ] = playerCardPresenters[ pCount ];
        
        int offset = playerCardPresenters.length;
        for(int pCount = 0; pCount < widowCardPresenters.length; pCount++)
            presenters[ offset + pCount ] = widowCardPresenters[ pCount ];
        
        offset += widowCardPresenters.length;

        for(int pCount = 0; pCount < playerTrickPresenters.length; pCount++)
        {
            for(int tCount = 0; tCount < trickSize; tCount++)
            {
                presenters[offset + trickSize * pCount + tCount] = 
                                        playerTrickPresenters[pCount][tCount];
            }
        }
        presenters[presenters.length - 1] = spec.loginPresenterName();
        
        return(new WipePresenterData(game, presenters));
    }    
    
}
