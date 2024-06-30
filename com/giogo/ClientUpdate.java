/*
 * ClientUpdate.java
 *
 * Created on June 8, 2003, 5:20 PM
 */

package com.giogo;

/**
 *
 * @author  inexum
 */
public class ClientUpdate implements ClientAction 
{
    ClientAction[] mActions;

    public ClientUpdate(ClientAction[] actions)
    {
        mActions = actions;
    }

    public void execute(ClientSession session)
    {
        for(int aCount = 0; aCount < mActions.length; aCount++)
        {
            mActions[ aCount ].execute(session);
        }
    }

    public String name() { return(getClass().getName()); }
    
    public ClientAction[] extend(int additionalSize)
    {
        ClientAction[] actions = new ClientAction[mActions.length + additionalSize];
        for(int aCount = 0; aCount < mActions.length; aCount++)
            actions[aCount] = mActions[aCount];
        mActions = actions;
        return(mActions);
    }
}
