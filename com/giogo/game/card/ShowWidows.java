/*
 * ShowWidows.java
 *
 * Created on Jun 03, 2003, 11:01 PM
 */

package com.giogo.game.card;

import com.giogo.game.Game;
import com.giogo.game.Action;
import com.giogo.game.ActionDistribution;

/**
 *
 * @author  Giogo
 * @version 1.0
 */

public class ShowWidows extends ActionDistribution
{
    public Action createAction(Game game)
    {
        CardGame cardGame = (CardGame)game;
        cardGame.widows().faceUp();
        return( new WidowsCards( cardGame ) );
    }
}