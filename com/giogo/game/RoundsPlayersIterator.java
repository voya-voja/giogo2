/*
 * PlayersIterator.java
 *
 * Created on October 1, 2003, 7:34 PM
 */

package com.giogo.game;

import java.util.List;

/**
 *
 * @author  inexum
 */
public class RoundsPlayersIterator extends PlayersIterator 
{
    int     mRound = 1; 
    long    mRoundsNo = -1;

    /** Creates a new instance of RoundsPlayersIterator */
    public RoundsPlayersIterator(List players)
    {
        super(players);
    }

    /** Creates a new instance of RoundsPlayersIterator */
    public RoundsPlayersIterator(List players, int startIndex)
    {
        super(players, startIndex);
    }

    /** Creates a new instance of RoundsPlayersIterator */
    public RoundsPlayersIterator(List players, long roundsNo)
    {
        super(players);
        mRoundsNo = roundsNo;
    }

    /** Creates a new instance of RoundsPlayersIterator */
    public RoundsPlayersIterator(List players, int startIndex, long roundsNo)
    {
        super(players, startIndex);
        mRoundsNo = roundsNo;
    }

    public boolean hasNext() 
    {
        return( mRoundsNo < 0 || mRound < mRoundsNo+1 );
    }

    public Object next()
    {
        Object player = super.next();
        if( !super.hasNext() )
        {
            mRound++;
            reset();
        }
        return( player );
    }

    public int round() { return( mRound ); }

    protected boolean isStart() 
    {
        return( mRound == 1 && super.isStart() ); 
    }
}
