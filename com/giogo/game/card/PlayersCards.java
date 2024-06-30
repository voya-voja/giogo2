/*
 * PlayersCards.java
 *
 * Created on Jun 03, 2003, 11:01 PM
 */

package com.giogo.game.card;

import com.giogo.ClientAction;
import com.giogo.PresenterUpdate;
import com.giogo.ClientUpdate;

import com.giogo.game.Action;
import com.giogo.game.WipeAllPresenters;
import com.giogo.game.Listener;

/**
 *
 * @author  Giogo
 * @version 1.0
 */

public class PlayersCards extends Action
{
    /** The game action to be applied to all players.
     *
     *  @param game - apply action on the provided game.
     */
    public PlayersCards(CardGame game)
    {
        super(game);
    }

    /** The game action to be applied only to the player.
     *
     *  @param game - apply action on the provided game.
     *  @param player - a player affected by this game action.
     */
    public PlayersCards(CardGame game, CardPlayer player) 
    {
        super(game, player);
    }

    public ClientAction gameViewAction()
    {
        return(null);
    }

    protected ClientAction listenerViewAction(Listener listener)
    {
        CardPlayer player = (CardPlayer)listener;
        Deck currentHand = player.currentHand();
        
        CardGame game = (CardGame)game();
        String[] presenters = ((CardGameSpecification)
                                game.specification()).playerCardPresenterNames();
        ClientAction[] updates = new ClientAction[presenters.length];
        int handSize = currentHand.size();
        for(int pCount = 0; pCount < presenters.length; pCount++)
        {
            if(handSize > pCount)
                updates[pCount] = new PresenterUpdate(presenters[pCount], 
                                            currentHand.cardAt(pCount));
            else
                updates[pCount] = new PresenterUpdate(presenters[pCount], null);
        }
        if(com.inexum.util.Debug.on)
            com.inexum.util.Debug.out("#updates:" + updates.length);
        return(new ClientUpdate(updates));
    }
}