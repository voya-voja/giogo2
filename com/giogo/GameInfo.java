/*
 * GameInfo.java
 *
 * Created on April 2, 2004, 4:47 PM
 */

package com.giogo;

import java.io.Serializable;

/**
 *
 * @author  nvojinov
 */
public class GameInfo implements Serializable
{
    private String  mType;
    private String  mName;
    private String  mId;
    private String  mStatus;
    private int     mPlayersNo;
    /** Creates a new instance of GameInfo */
    public GameInfo(String type, String id, String name, String status, int playersNo) 
    {
        mType = type;
        mId = id;
        mName = name;
        mStatus = status;
        mPlayersNo = playersNo;
    }

    public GameInfo(String type, String id, String name, boolean running, int playersNo) 
    {
        mType = type;
        mId = id;
        mName = name;
        if(running)
            mStatus = "play";
        else
            mStatus = "wait";
        mPlayersNo = playersNo;
    }

    public String id()
    {
        return(mId);
    }

    public String status()
    {
        return(mStatus);
    }
    
    public int playersNo()
    {
        return(mPlayersNo);
    }
    
    public String toString()
    {
        return(mId + ":" + mStatus + "(" + mPlayersNo + ")");
    }
    
    public String name() { return(mName); }
}
