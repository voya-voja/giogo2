/*
 * RefeUpdate.java
 *
 * Created on January 31, 2004, 8:52 PM
 */

package com.giogo.game.card.preference;

import com.giogo.ClientAction;
import com.giogo.PresenterUpdate;
import com.giogo.game.PlayersListUpdate;
import com.giogo.game.Player;

/**
 *
 * @author  inexum
 */
public class RefeUpdate extends PlayersListUpdate
{
    /** Creates a new instance of RefeUpdate */
    public RefeUpdate(Preference game) 
    {
        super(game);
    }
    
    protected ClientAction update(String presenter, Player player)
    {
        PreferencePlayer prefPlayer = (PreferencePlayer)player;
        String refeStr = (new Integer(prefPlayer.refe())).toString();
        String[] refe = {"refe", refeStr};
        return(new PresenterUpdate(presenter, refe));
    }
}
