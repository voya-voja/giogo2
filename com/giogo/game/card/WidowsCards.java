/*
 * WidowsCards.java
 *
 * Created on Jun 03, 2003, 11:01 PM
 */

package com.giogo.game.card;

import com.giogo.ClientAction;
import com.giogo.PresenterUpdate;
import com.giogo.ClientUpdate;

import com.giogo.game.Action;

/**
 *
 * @author  Giogo
 * @version 1.0
 */

public class WidowsCards extends Action
{
    public WidowsCards(CardGame game)
    {
        super(game);
    }

    public ClientAction gameViewAction()
    {
        CardGame game = (CardGame)game();
        Deck widows = game.widows();
        String[] presenters = ((CardGameSpecification)
                                game.specification()).widowCardPresenterNames();
        int updatesNo = widows.size();
        if(updatesNo > presenters.length)
            updatesNo = presenters.length;
        ClientAction[] updates = new ClientAction[updatesNo];
        for(int aCount = 0; aCount < updatesNo; aCount++)
            updates[aCount] = new PresenterUpdate(presenters[aCount], 
                                            widows.cardAt(aCount));
        if(com.inexum.util.Debug.on)
            com.inexum.util.Debug.out("#updates:" + updates.length);
        return(new ClientUpdate(updates));
    }
}