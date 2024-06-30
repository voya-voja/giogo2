/*
 * PreferenceSelectPlayCards.java
 *
 * Created on Jun 03, 2003, 11:01 PM
 */

package com.giogo.game.card.preference;

import com.giogo.game.Game;
import com.giogo.game.Step;
import com.giogo.game.Action;
import com.giogo.game.card.CardGameSpecification;
import com.giogo.game.card.BiddingCardGame;
import com.giogo.game.card.CardPlayer;
import com.giogo.game.WipePresenterData;
import com.giogo.game.card.ActivateCards;
import com.giogo.game.card.PlayersCards;
import com.giogo.game.card.SelectedCardsService;
import com.giogo.game.card.CardGame;
import com.giogo.game.card.CardPlayer;
import com.giogo.game.card.Card;
import com.giogo.game.card.Deck;

/**
 *
 * @author  Giogo
 * @version 1.0
 */

public class PreferenceSelectPlayCards implements Step
{
    public void execute(Game game)
    {
        if(com.inexum.util.Debug.on)
            com.inexum.util.Debug.out(game.id());
        BiddingCardGame cardBiddingGame = (BiddingCardGame)game;
        CardPlayer winner = (CardPlayer)cardBiddingGame.biddingWinner();
        winner.take(cardBiddingGame.widows());
        cardBiddingGame.widows(null);

        PreferenceSpecification spec = (PreferenceSpecification)cardBiddingGame.specification();
        Action action = new WipePresenterData(cardBiddingGame, 
                                                    spec.widowCardPresenterNames(), 
                                                    winner, false);
        cardBiddingGame.receive(action);

        
        action = new ActivateCards(game, playerWidowCardPresenters(spec), winner, 2);
        cardBiddingGame.receive(action);
        winner.waitResponse();
        
        action = new WipePresenterData(cardBiddingGame, 
                                                    spec.widowCardPresenterNames(), 
                                                    winner, true);
        cardBiddingGame.receive(action);

        cardBiddingGame.widows(winner.giveSelectedCards());

        winner.currentHand().sort();
        action = new PlayersCards(cardBiddingGame);
        cardBiddingGame.receive(action);
    }
    
    private String[] playerWidowCardPresenters(PreferenceSpecification spec)
    {
        String[] playerCardPresenters = spec.playerCardPresenterNames();
        String[] widowCardPresenters = spec.widowCardPresenterNames();
        String[] cardPresenters = new String[ playerCardPresenters.length +
                                                   widowCardPresenters.length ];
        for(int pCount = 0; pCount < playerCardPresenters.length; pCount++)
            cardPresenters[ pCount ] = playerCardPresenters[ pCount ];
        
        for(int pCount = 0; pCount < widowCardPresenters.length; pCount++)
            cardPresenters[ playerCardPresenters.length + pCount ] = 
                                                    widowCardPresenters[ pCount ];
        return(cardPresenters);
    }   
}