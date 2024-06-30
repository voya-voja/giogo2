/*
 * Game.java
 *
 * Created on Jun 03, 2003, 11:01 PM
 */

package com.giogo.game;

import com.inexum.IPC.Server;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import com.giogo.GameInfo;

/**
 *
 * @author  Giogo
 * @version 1.0
 */

public class Game
{
    private Map mPlayers        = new HashMap();
    private List mPlayerList    = new ArrayList();
    private RoundsPlayersIterator mFirstPlayer;
    
    private Map mSpectators     = new HashMap();
    private List mSpectatorList = new ArrayList();
    
    private String mId;
    private String mName = "game";
    private List mActions = new ArrayList();
    private Specification mSpec;
    
    private Player  mRoundWinner;

    protected Game(Map parameters, Specification spec)
    {
        mSpec = spec;
        mId = (String)parameters.get("id");
        mName = (String)parameters.get("name");
        GamesManager.Instance().add(this);
    }
    
    public GameInfo info()
    {
        int playersNo = mPlayers.size();
        return(new GameInfo(mSpec.name(), mId, mName, 
                        mSpec.allowNewPlayerToJoin(playersNo),
                        playersNo));
    }
    
    public void receive(Action action)
    {
        try
        {
            synchronized(mActions)
            {
                action.id(mActions.size());
                mActions.add(action);
                mActions.notifyAll();
                if(com.inexum.util.Debug.on)
                    com.inexum.util.Debug.out("NOTIFY added action[" + action.id() 
                                    + "]:'" + action.getClass().getName() 
                                    + "'");
            }
        }
        catch(UnsupportedOperationException e) 
        { 
            e.printStackTrace();
            if(com.inexum.util.Debug.on)
                com.inexum.util.Debug.out(e);
        }
    }
    
    public Action nextAction(Action action) throws InterruptedException, ActionWaitTimedoutException
    {
        int actionId = 0;
        if(action != null)
            actionId = action.id() + 1;
        
        if(com.inexum.util.Debug.on)
            com.inexum.util.Debug.out("action:" 
                            + actionId 
                            + ", of:" + mActions.size());

        synchronized(mActions)
        {
            if(actionId < mActions.size())
                return((Action)mActions.get(actionId));
            return(waitNewAction());
        }
    }
    
    public Action waitNewAction() throws InterruptedException, ActionWaitTimedoutException
    {
        try
        {
            int atCurrent = mActions.size();

            if(com.inexum.util.Debug.on)
                com.inexum.util.Debug.out("WAIT action[" + atCurrent + "]");

            mActions.wait(20000);
            Action action = (Action)mActions.get(atCurrent);

            if(com.inexum.util.Debug.on)
                com.inexum.util.Debug.out("Got action[" + atCurrent + "]:" + action);
            return(action);
        }
        catch(UnsupportedOperationException e) 
        { 
            e.printStackTrace();
            if(com.inexum.util.Debug.on)
                com.inexum.util.Debug.out(e);
        }
        catch(IndexOutOfBoundsException e)
        {
            throw new ActionWaitTimedoutException();
        }
        return null;
    }

    public void addPlayer(Player player)
    {
        try
        {
            synchronized(mPlayers)
            {
                mPlayers.put(player.alias(), player);
                if(com.inexum.util.Debug.on)
                    com.inexum.util.Debug.out("Player added to map!");
                mPlayers.notifyAll();
                if(com.inexum.util.Debug.on)
                    com.inexum.util.Debug.out("NOTIFY " + mPlayers );
            }
            synchronized(mPlayerList)
            {
                player.setId(mPlayerList.size());
                mPlayerList.add(player);
                if(com.inexum.util.Debug.on)
                    com.inexum.util.Debug.out("Player [" + player.id() + "] added!");
                mPlayerList.notifyAll();
                if(com.inexum.util.Debug.on)
                    com.inexum.util.Debug.out("NOTIFY " + mPlayerList );
            }
        }
        catch(UnsupportedOperationException e) 
        { 
            e.printStackTrace();
        }
    }
    
    public void addSpectator(Spectator spectator)
    {
        try
        {
            synchronized(mPlayers)
            {
                mSpectators.put(spectator.alias(), spectator);
            }
            synchronized(mPlayerList)
            {
                mSpectatorList.add(spectator);
            }
            mSpectators.notifyAll();
            mSpectatorList.notifyAll();
            if(com.inexum.util.Debug.on)
                com.inexum.util.Debug.out("NOTIFY " + mSpectatorList);
        }
        catch(UnsupportedOperationException e) 
        { 
            e.printStackTrace();
            if(com.inexum.util.Debug.on)
                com.inexum.util.Debug.out(e);
        }
    }
    
    public List getPlayers()
    {
        return(mPlayerList);
    }
    
    public List getSpectators()
    {
        return(mSpectatorList);
    }
    
    public Player logonPlayer(String playerAlias) 
    {
        Player player;
        if(isLogged(playerAlias))
        {
            player = getPlayer(playerAlias);
        }
        else
        {
            player = mSpec.createPlayer(playerAlias, this);
            addPlayer(player);
        }
        return(player); 
    }
    
    public Player getPlayer(String playerAlias)
    {
        return((Player)mPlayers.get(playerAlias));
    }
    
    public boolean isLogged(String alias) 
    {
        return(mPlayers.containsKey(alias) || mSpectators.containsKey(alias)); 
    }

    public Spectator getSpectator(String spectatorAlias)
    {
        return((Spectator)mSpectators.get(spectatorAlias));
    }
    
    public Listener getListener(String alias)
    {
        Listener listener = getPlayer(alias);
        if(listener == null)
            listener = getSpectator(alias);
        return(listener);
    }

    public String id() { return(mId); }
    public Specification specification() { return(mSpec); }
    
    public int listenersSize()
    {
        return(mPlayers.size() + mSpectators.size());
    }
    
    public void start()
    {
        mFirstPlayer = new RoundsPlayersIterator(mPlayerList);
    }
    
    public boolean isStarted() { return(mFirstPlayer != null); }
    
    public void oneMore()
    {
        Iterator at = mPlayerList.listIterator();
        while(at.hasNext())
        {
            Player player = (Player)at.next();
            player.reset();
        }
        mFirstPlayer.next();
        if(com.inexum.util.Debug.on)
            com.inexum.util.Debug.out("1st player " + mFirstPlayer.current().id()
                                        + ". " + mFirstPlayer.current().alias());
    }
    
    public Player firstPlayer() { return(mFirstPlayer.current()); }
    
    public Player roundWinner() { return(mRoundWinner); }
    
    public void roundWinner(Player winner) { mRoundWinner = winner; }
    
    public Iterator gameActionIterator(int end)
    {
        return(new ActionIterator(mActions, end));
    }
    
    public Iterator gameActionIterator(int start, int end)
    {
        return(new ActionIterator(mActions, start, end));
    }
    
    private static class ActionIterator implements Iterator
    {
        private int mEnd;
        private int mIndex = 0;
        private List mActions;
        
        ActionIterator(List actions)
        {
            mActions = actions;
            synchronized(mActions)
            {
                mEnd = mActions.size();
            }
            mEnd = mActions.size();
        }
        
        ActionIterator(List actions, int end)
        {
            mActions = actions;
            synchronized(mActions)
            {
                mEnd = mActions.size();
            }
            if(end + 1 < mEnd)
                mEnd = end + 1;
        }
        
        ActionIterator(List actions, int start, int end)
        {
            mIndex = start;
            mActions = actions;
            synchronized(mActions)
            {
                mEnd = mActions.size();
            }
            if(end + 1 < mEnd)
                mEnd = end + 1;
        }
        
        public boolean hasNext() 
        {
            return(mIndex < mEnd);
        }
        
        public Object next() 
        {
            Object o = null;
            synchronized(mActions)
            {
                o = mActions.get(mIndex);
                mIndex++;
            }
            return(o);
        }
        
        public void remove() 
        {
            synchronized(mActions)
            {
                mActions.remove(mIndex);
                mEnd--;
            }
        }
        
    }
}