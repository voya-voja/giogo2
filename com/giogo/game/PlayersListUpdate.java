/*
 * PlayersList.java
 *
 * Created on Jun 03, 2003, 11:01 PM
 */

package com.giogo.game;

import java.util.List;
import java.util.Iterator;

import com.giogo.ClientAction;
import com.giogo.ClientUpdate;
import com.giogo.PresenterUpdate;

/**
 *
 * @author  Giogo
 * @version 1.0
 */

public class PlayersListUpdate extends UpdateAction
{
    public PlayersListUpdate(Game game)
    {
        super(game);
    }

    protected ClientAction clientAction(int playerId)
    {
        List players = game().getPlayers();
        int playersNo = players.size();
        String[] presenters = game().specification().playerPresenterNames();
        ClientAction[] updates = new ClientAction[playersNo];
        for(int aCount = 0; aCount < presenters.length; aCount++)
        {
            int pCount = (playerId + aCount) % presenters.length;
            if(pCount >= playersNo) continue;

            Player player = (Player)players.get(pCount);
            updates[pCount] = update(presenters[aCount], player);
        }
        if(com.inexum.util.Debug.on)
            com.inexum.util.Debug.out("#updates:" + updates.length);
        return(new ClientUpdate(updates));
    }
    
    protected ClientAction update(String presenter, Player player)
    {
        String[] alias = {player.alias(), null};
        return(new PresenterUpdate(presenter, alias));
    }
}