/*
 * ActivateCards.java
 *
 * Created on October 21, 2003, 8:12 AM
 */

package com.giogo.game.card;

import com.giogo.ActivateClientSession;
import com.giogo.game.ActivatePlayer;
import com.giogo.game.Player;
import com.giogo.game.Game;

/**
 *
 * @author  inexum
 */
public class ActivateCards extends ActivatePlayer 
{
    SelectCards     mSelectCards;
    
    /** Creates a new instance of ActivateCards */
    public ActivateCards(Game game, String[] presenterNames, Player player) 
    {
        super(game, presenterNames, player);
        mSelectCards = new SelectCards();
    }
    
    /** Creates a new instance of ActivateCards */
    public ActivateCards(Game game, String[] presenterNames, Player player, 
                            int cardsNo) 
    {
        super(game, presenterNames, player);
        mSelectCards = new SelectCards(cardsNo);
    }
    
    protected ActivateClientSession activation() { return(mSelectCards); }
}
