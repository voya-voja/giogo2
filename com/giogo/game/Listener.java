/*
 * Listener.java
 *
 * Created on June 8, 2003, 5:54 PM
 */

package com.giogo.game;

import java.util.ArrayList;
import java.util.Iterator;

import com.giogo.ClientAction;
import com.giogo.ClientUpdate;

/**
 *
 * @author  inexum
 */
public class Listener 
{
    private String  mAlias;
    private Game    mGame;
    private Action  mAction;
    private boolean mActive = true;
    
    static Class    gmActivatePresentersClass;
    static private DummyAction gmDummyAction = new DummyAction();

    protected Listener(String alias, Game game)
    {
        mAlias = alias;
        mGame = game;
    }
    
    public String alias()
    {
        return(mAlias);
    }

    public Game game() { return(mGame); }
    
    public ClientAction getViewAction()
    {
        if(com.inexum.util.Debug.on)
            com.inexum.util.Debug.out(mAlias);
        ClientAction viewAction = null;
        do {
            Action action = nextAction();
            if(action != null)
                viewAction = action.gameViewAction(this);
        } while(viewAction == null);
        return(viewAction);
    }

    public ClientAction replay()
    {
        if(com.inexum.util.Debug.on)
            com.inexum.util.Debug.out("for " + mAlias);
        ArrayList updates = new ArrayList();
        Iterator at = mGame.gameActionIterator(mAction.id());
        while(at.hasNext())
        {
            Action action = (Action)at.next();
            if(action == null)
                continue;

            if(com.inexum.util.Debug.on)
                com.inexum.util.Debug.out("action[" + action.id() + "]");
            if(!action.isFor(this))
                continue;

            if(com.inexum.util.Debug.on)
                com.inexum.util.Debug.out("for user");

            if(gmActivatePresentersClass == null)
                loadActivatePresenters();
            if(gmActivatePresentersClass.isAssignableFrom(action.getClass()))
                continue;

            if(com.inexum.util.Debug.on)
                com.inexum.util.Debug.out("not active");
            mAction = action;
            ClientAction viewAction = getViewAction(action);
            if(viewAction != null)
                updates.add(viewAction);
        }
        if(com.inexum.util.Debug.on)
            com.inexum.util.Debug.out("updates #: " + updates.size());
        return(new ClientUpdate(
                    (ClientAction[])updates.toArray(new ClientAction[1])));
    }
    
    public boolean isActive() { return(mActive); }
    public void active() { mActive = true; }
    public void inactive() { mActive = false; }
    
    protected ClientAction getViewAction(Action action)
    {
        return(action.gameViewAction());
    }
    
    final protected Action nextAction()
    {
        if(com.inexum.util.Debug.on)
            com.inexum.util.Debug.out(mAlias);
        try
        {
            Action action = mGame.nextAction(mAction);
            if(action != null)
                mAction = action;
            return(action);
        }
        catch(InterruptedException e){}
        catch(ActionWaitTimedoutException e) {}
        return(gmDummyAction);
    }        
    
    private void loadActivatePresenters()
    {
        try
        {
            if(gmActivatePresentersClass == null)
                gmActivatePresentersClass =
                    ClassLoader.getSystemClassLoader().loadClass("com.giogo.game.ActivatePlayer");
        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
