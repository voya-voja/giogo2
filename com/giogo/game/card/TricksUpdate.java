/*
 * TricksUpdate.java
 *
 * Created on December 17, 2003, 9:22 PM
 */

package com.giogo.game.card;

import com.giogo.ClientAction;
import com.giogo.PresenterUpdate;
import com.giogo.ClientUpdate;

import com.giogo.game.Listener;
import com.giogo.game.UpdateAction;

/**
 *
 * @author  inexum
 */
public class TricksUpdate extends UpdateAction 
{
    private CardPlayer mPlayer;
    /** Creates a new instance of TricksUpdate */
    public TricksUpdate(CardGame game, CardPlayer player) 
    {
        super(game);
        mPlayer = player;
    }
    
    protected ClientAction clientAction(int playerId)
    {
        String[] presenters = game().specification().playerPresenterNames();
        int index = (presenters.length + mPlayer.id() - playerId) % presenters.length;
        String[] row = {"tricks", null};
        int tricks = mPlayer.tricksNo();
        if(tricks != 0)
            row[1] = Integer.toString(tricks);
            
        if(com.inexum.util.Debug.on)
            com.inexum.util.Debug.out("player: " + playerId + 
                                        "ticks of: " + mPlayer.id() + 
                                        "update presenter '" + presenters[index] + "'");
        ClientAction tricksUpdate = new PresenterUpdate(presenters[index], row);
        if(mPlayer.id() == playerId)
        {
            ClientAction[] tricksActions = tricksActions();
            if(tricksActions != null)
            {
                tricksActions[0] = tricksUpdate;
                tricksUpdate = new ClientUpdate(tricksActions);
            }
        }
        return(tricksUpdate);
    }
    
    private ClientAction[] tricksActions()
    {
        Deck[] tricks = mPlayer.tricks();
        if(tricks == null || tricks.length == 0) 
            return(null);

        CardGame game = (CardGame)game();
        CardGameSpecification gameSpec = 
                                (CardGameSpecification)game.specification();
        String[][] presenters = gameSpec.playerTricksPresenterNames();
        int trickSize = tricks[0].size();
        ClientAction[] updates = new ClientAction[tricks.length * trickSize + 1];
        for(int tCount = 0; tCount < tricks.length; tCount++)
        {
            for(int cCount = 0; cCount < trickSize; cCount++)
            {
                updates[tCount+cCount+1] = 
                                new PresenterUpdate(presenters[tCount][cCount], 
                                                tricks[tCount].cardAt(cCount));
            }
        }
        if(com.inexum.util.Debug.on)
            com.inexum.util.Debug.out("#updates:" + updates.length);
        return(updates);
    }
}
