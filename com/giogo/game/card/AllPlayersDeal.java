/*
 * AllPlayersDeal.java
 *
 * Created on Jun 03, 2003, 11:01 PM
 */

package com.giogo.game.card;

import com.giogo.game.Action;

import java.util.Iterator;

/**
 *
 * @author  Giogo
 * @version 1.0
 */

public class AllPlayersDeal extends DealCards
{
    public AllPlayersDeal(int cardNo)
    {
        super(cardNo);
    }

    public AllPlayersDeal(int cardNo, boolean shuffle)
    {
        super(cardNo, shuffle);
    }

    protected Action createAction(Deck deck, CardGame game)
    {
        Iterator players = game.getPlayers().iterator();
        while(players.hasNext())
        {
            CardPlayer player = (CardPlayer)players.next();
            player.take(deck.split(deck.size() - cardNumber()));
        }
        return(new PlayersCards(game));
    }
}