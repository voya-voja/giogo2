/*
 * WipePresenterData.java
 *
 * Created on June 24, 2003, 7:09 PM
 */

package com.giogo.game;

import com.giogo.ClientAction;
import com.giogo.PresenterUpdate;
import com.giogo.ClientUpdate;

/**
 *
 * @author  inexum
 */
public class WipePresenterData extends Action 
{
    String[] mPresenterNames;
    
    /** Creates a new instance of WipePresenterData */
    public WipePresenterData(Game game) 
    {
        super(game);
    }
    
    /** Creates a new instance of WipePresenterData */
    public WipePresenterData(Game game, String[] presenterNames) 
    {
        super(game);
        mPresenterNames = presenterNames;
    }
    
    /** Creates a new instance of WipePresenterData */
    public WipePresenterData(Game game, String[] presenterNames, Player player, boolean forPlayerOnly) 
    {
        super(game, player, forPlayerOnly);
        mPresenterNames = presenterNames;
    }
    
    public ClientAction gameViewAction() 
    {
        if(mPresenterNames.length == 0) return(new WipeAllPresenters());
        
        ClientAction[] updates = new ClientAction[mPresenterNames.length];
        for(int pCount = 0; pCount < mPresenterNames.length; pCount++)
        {
            updates[pCount] = new PresenterUpdate(mPresenterNames[pCount], null);
        }
        if(com.inexum.util.Debug.on)
            com.inexum.util.Debug.out("#updates:" + updates.length);
        
        return(new ClientUpdate(updates));
    }
    
}
