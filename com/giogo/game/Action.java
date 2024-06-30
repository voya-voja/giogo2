/*
 * Action.java
 *
 * Created on June 8, 2003, 8:23 PM
 */

package com.giogo.game;

import com.giogo.ClientAction;

/**
 *
 * @author  inexum
 */
public abstract class Action 
{
    private int mId;
    private Listener mListener;
    private boolean mForListenerOnly = false;
    private Game	mGame;


    /** The game action to be applied to all listeners.
     *
     */
    protected Action()
    {
    }

    /** The game action to be applied to all listeners.
     *
     *  @param game - apply action on the provided game.
     */
    public Action(Game game)
    {
        mGame = game;
    }

    /** The game action to be applied only to the listener.
     *
     *  @param game - apply action on the provided game.
     *  @param listener - a game listener affected by this game action.
     */
    public Action(Game game, Listener listener) 
    {
        mGame = game;
        mListener = listener;
        mForListenerOnly = true;
    }

    /** The game action to be applied only to the listener or to all other listeners
     *  except the provided listener.
     *
     *  @param game - apply action on the provided game.
     *  @param listener - a game listener affected by this game action.
     *  @param forListenerOnly - if true, only the specified listener will be affected 
     *                         by the action. If false, only the specified listener
     *                         will be not be affected by the action.
     */
    public Action(Game game, Listener listener, boolean forListenerOnly) 
    {
        mGame = game;
        mListener = listener;
        mForListenerOnly = forListenerOnly;
    }

    public int id() { return(mId); }
    public void id(int id) { mId = id; }
    
    public boolean isFor(Listener listener)
    {
        return(mListener == null 
                || (mForListenerOnly && mListener == listener)
                || (!mForListenerOnly && mListener != listener));
    }
    
    abstract public ClientAction gameViewAction();
    
    public ClientAction gameViewAction(Listener listener)
    {
        if(com.inexum.util.Debug.on)
            com.inexum.util.Debug .out("action[" + mId +" for " 
                                                            + listener.alias());
        
        ClientAction viewAction = null;
        if(mListener == null || ((mListener != listener) ^ mForListenerOnly))
            viewAction = listenerViewAction(listener);
        return(viewAction);
    }
    
    protected ClientAction listenerViewAction(Listener listener)
    {
        return(gameViewAction());
    }
    
    protected Game game() { return(mGame); }
}
