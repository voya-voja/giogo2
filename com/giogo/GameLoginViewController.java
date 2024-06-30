/*
 * GameLoginViewController.java
 *
 * Created on May 20, 2004, 10:44 AM
 */

package com.giogo;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author  inexum
 */
public class GameLoginViewController 
{
    String mAlias;
    String mGameId;
    GameParticipantsInfo mParticipants;
    
    /** Creates a new instance of GameLoginViewController */
    public GameLoginViewController(HttpServletRequest request) 
    {
        try
        {
            mAlias = request.getParameter("alias");
            mGameId = request.getParameter("game");
            mParticipants = 
                GiogoServerProxy.Instance().gameParticipants(mGameId);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public String gameId() { return(mGameId); }
    public String alias() { return(mAlias); }

    public String isLoggedOn()
    {
        String[] players = mParticipants.getPlayers();
        for(int index=0; index < players.length; index++)
            if(players[index].equals(mAlias))
                return("true");
        
        String[] spectators = mParticipants.getSpectators();
        for(int index=0; index < spectators.length; index++)
            if(spectators[index].equals(mAlias))
                return("true");
        
        return("false");
    }
}
