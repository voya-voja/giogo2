/*
 * GameParticipantsInfo.java
 *
 * Created on May 10, 2004, 1:32 PM
 */

package com.giogo;

/**
 *
 * @author  inexum
 */
public class GameParticipantsInfo implements java.io.Serializable 
{
    String[] mPlayers;
    String[] mSpectators;
    
    /** Creates a new instance of GameParticipantsInfo */
    public GameParticipantsInfo(String[] players, String[] spectators) 
    {
        mPlayers = players;
        mSpectators = spectators;
    }
    
    public String[] getPlayers() { return(mPlayers); }
    public String[] getSpectators() { return(mSpectators); }
}
