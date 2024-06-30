/*
 * RefreshScore.java
 *
 * Created on January 23, 2004, 12:12 AM
 */

package com.giogo.game.card.preference;

import com.giogo.ClientAction;
import com.giogo.PresenterUpdate;
import com.giogo.ClientUpdate;

import com.giogo.game.Game;
import com.giogo.game.Player;

import java.util.List;

/**
 *
 * @author  inexum
 */
public class RefreshScore extends com.giogo.game.PlayersListUpdate 
{
    /** Creates a new instance of RefreshScore */
    public RefreshScore(Game game)
    {
        super( game );
    }

    protected ClientAction update(String presenter, Player player) 
    {
        PreferencePlayer prefPlayer = (PreferencePlayer)player;
        ClientAction[] updates = new ClientAction[3];
        String[] mainPoints = {player.alias(), 
                                (new Integer(prefPlayer.mainPoints())).toString()};
        updates[0] = new PresenterUpdate(presenter, mainPoints);
        
        List players = game().getPlayers();
        PreferencePlayer leftPlayer = 
                        (PreferencePlayer)players.get((player.id() + 2) % 3);
        String[] leftPoints = {"left " + leftPlayer.alias(), 
                                (new Integer(
                                    prefPlayer.defencePoints(PreferencePlayer.cLeft)
                                    )).toString()};
        updates[1] = new PresenterUpdate(presenter, leftPoints);

        PreferencePlayer rightPlayer = 
                        (PreferencePlayer)players.get((player.id() + 1) % 3);
        String[] rightPoints = {"right " + rightPlayer.alias(), 
                                (new Integer(
                                    prefPlayer.defencePoints(PreferencePlayer.cRight)
                                    )).toString()};
        updates[2] = new PresenterUpdate(presenter, rightPoints);

        return( new ClientUpdate( updates ) );
    }
}
