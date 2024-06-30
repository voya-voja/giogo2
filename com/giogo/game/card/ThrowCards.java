/*
 * ThrowCards.java
 *
 * Created on December 12, 2003, 3:51 PM
 */

package com.giogo.game.card;

import com.giogo.game.UpdateAction;
import com.giogo.ClientAction;
import com.giogo.ClientUpdate;
import com.giogo.PresenterUpdate;
import com.giogo.game.Listener;

/**
 *
 * @author  inexum
 */
public class ThrowCards extends UpdateAction 
{
    String[]    mPresenterNames;
    int         mThrowerId;
    Deck        mThrowen;
    
    /** The game action to be applied only to the player.
     *
     *  @param game - apply action on the provided game.
     *  @param player - a player affected by this game action.
     */
    public ThrowCards(CardGame game, CardPlayer player) 
    {
        super(game);
        CardGameSpecification spec = (CardGameSpecification)game.specification();
        mPresenterNames = spec.throwCardPresenterNames();
        mThrowerId = player.id();
        mThrowen = player.thrownCards();
    }

    protected ClientAction clientAction(int playerId)
    {
        int index = (mPresenterNames.length + mThrowerId - playerId) 
                                                        % mPresenterNames.length;
        
        if(com.inexum.util.Debug.on)
            com.inexum.util.Debug.out("player: " + playerId + 
                                        " thrower: " + mThrowerId + 
                                        " update presenter '" + mPresenterNames[index] + "'");
        try
        {
            return(new PresenterUpdate(mPresenterNames[index], mThrowen.cardAt(0)));
        }
        catch(IndexOutOfBoundsException e)
        {
            if(com.inexum.util.Debug.on)
                com.inexum.util.Debug.out(e);
            return(null);
        }   
    }
}
