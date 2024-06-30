/*
 * WidowsDeal.java
 *
 * Created on Jun 03, 2003, 11:01 PM
 */

package com.giogo.game.card;

import com.giogo.game.Action;

/**
 *
 * @author  Giogo
 * @version 1.0
 */

public class WidowsDeal extends DealCards
{
    private int	mCardNo;
    private boolean mShuffle = false;

    public WidowsDeal(int cardNo)
    {
        super(cardNo);
    }

    public WidowsDeal(int cardNo, boolean shuffle)
    {
        super(cardNo, shuffle);
    }

    protected Action createAction(Deck deck, CardGame game)
    {
        game.widows(deck.split(deck.size() - cardNumber()));
        return(new WidowsCards(game));
    }
}