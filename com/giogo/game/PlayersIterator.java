/*
 * PlayersIterator.java
 *
 * Created on December 12, 2003, 7:46 PM
 */

package com.giogo.game;

import java.util.Iterator;
import java.util.Random;
import java.util.List;

/**
 *
 * @author  inexum
 */
public class PlayersIterator implements Iterator
{
    private List    mPlayers;
    private int     mAtIndex;
    private int     mStartIndex;
    private boolean mFirstRound;

    /** Creates a new instance of PlayersIterator */
    public PlayersIterator(List players)
    {
        mPlayers = players;
        mStartIndex = new Random().nextInt( players.size() );
        mAtIndex = atIndex();
        mFirstRound = players.size() != 0;
    }

    /** Creates a new instance of PlayersIterator */
    public PlayersIterator(List players, int startIndex)
    {
        mPlayers = players;
        mStartIndex = startIndex;
        mAtIndex = atIndex();
        mFirstRound = players.size() != 0;
    }

    public boolean hasNext() 
    {
        return(mFirstRound || mAtIndex != atIndex());
    }
    
    public Object next() 
    {
        mAtIndex++;
        if( mAtIndex == mPlayers.size() )
            mAtIndex = 0;
        
        if( mFirstRound && mAtIndex == atIndex() )
            mFirstRound = false;
        
        Object player = mPlayers.get( mAtIndex );
        if( com.inexum.util.Debug.on )
            com.inexum.util.Debug.out( "@ " + ((Player)player).id() + 
                                            "."+((Player)player).alias() );
        return( player );
    }
    
    public void remove() 
    {
        if( com.inexum.util.Debug.on )
            com.inexum.util.Debug.out( "@ " + ((Player)mPlayers.get( mAtIndex )).id() + 
                                            "."+((Player)mPlayers.get( mAtIndex )).alias() );
        mPlayers.remove( mAtIndex );
    }

    public Player current()
    {
        Player player = (Player)mPlayers.get( mAtIndex );
        if( com.inexum.util.Debug.on )
            com.inexum.util.Debug.out( "@ " + player.id() + 
                                            "."+ player.alias() );
        return( player );
    }
    
    public Player nextPlayer()
    {
        if( com.inexum.util.Debug.on )
            com.inexum.util.Debug.out( "@ " + ((Player)mPlayers.get( mAtIndex )).id() + 
                                            "."+((Player)mPlayers.get( mAtIndex )).alias() );
        return( (Player)next() );
    }
    
    public void reset()
    {
        mAtIndex = atIndex();
        mFirstRound = mPlayers.size() != 0;
    }
    
    protected void reset(int startIndex)
    {
        mStartIndex = startIndex;
        mAtIndex = atIndex();
        mFirstRound = mPlayers.size() != 0;
    }
    
    protected int atIndex()
    {
        int start = mStartIndex - 1;
        if( start < 0 )
            start = mPlayers.size() - 1;
        return( start );
    }

    protected boolean isStart() 
    {
        return( mFirstRound && mAtIndex == atIndex() ); 
    }
}
