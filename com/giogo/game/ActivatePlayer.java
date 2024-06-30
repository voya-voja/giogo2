/*
 * ActivatePlayer.java
 *
 * Created on August 1, 2003, 8:11 PM
 */

package com.giogo.game;

import com.giogo.ClientAction;
import com.giogo.PresenterUpdate;
import com.giogo.ActivateClientSession;
import com.giogo.ClientUpdate;

/**
 *
 * @author  inexum
 */
public class ActivatePlayer extends Action 
{
    String[]    mPresenterNames;
    Player      mPlayer;
    
    /** Creates a new instance of ActivatePlayer */
    public ActivatePlayer(Game game, String[] presenterNames, Player player) 
    {
        super(game, player);
        mPresenterNames = presenterNames;
        mPlayer = player;
    }
    
    public ClientAction gameViewAction(Listener listener)
    {
        if(listener == mPlayer)
            return(gameViewAction());
        else
            return(new PresenterUpdate(game().specification().loginPresenterName(),
                                            mPlayer.alias() + " is active!"));
    }

    public ClientAction gameViewAction() 
    {
        if(mPresenterNames == null || mPresenterNames.length == 0)
            return(activation());
        
        ClientAction[] updates = new ClientAction[mPresenterNames.length+1];
        updates[0] = activation();
        for(int pCount = 0; pCount < mPresenterNames.length; pCount++)
        {
            updates[pCount + 1] = new PresenterUpdate(mPresenterNames[pCount],
                                            presenterInfo(mPresenterNames[pCount]),
                                            PresenterUpdate.cEnable);
        }
        if(com.inexum.util.Debug.on)
            com.inexum.util.Debug.out("#update:" + updates.length);
        
        return(new ClientUpdate(updates));
    }
    
    protected ActivateClientSession activation() { return(new ActivateClientSession()); }
    protected Object presenterInfo(String presenterName) { return(PresenterUpdate.cNoData); }
}

