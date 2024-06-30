/*
 * AllPlayersSortCards.java
 *
 * Created on Jun 03, 2003, 11:01 PM
 */

package com.giogo.game.card;

import com.giogo.game.Game;
import com.giogo.game.Action;
import com.giogo.game.ActionDistribution;

import java.util.Iterator;

/**
 *
 * @author  Giogo
 * @version 1.0
 */

public class AllPlayersSortCards extends ActionDistribution
{
    protected Action createAction( Game game )
    {
        CardGame cardGame = (CardGame)game;
        
        Iterator players = game.getPlayers().iterator();
        while( players.hasNext() )
        {
            CardPlayer player = (CardPlayer)players.next();
            Deck hand = player.currentHand();
            hand.sort();
            hand.faceUp();
        }
        return( new PlayersCards( cardGame ) );
    }    
}