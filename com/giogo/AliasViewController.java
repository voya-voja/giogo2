/*
 * AliasViewController.java
 *
 * Created on May 8, 2004, 10:11 PM
 */

package com.giogo;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author  inexum
 */
public class AliasViewController 
{
    GameParticipantsInfo mParticipants;
    
    /** Creates a new instance of AliasViewController */
    public AliasViewController(HttpServletRequest request) 
    {
        try
        {
            mParticipants = 
                GiogoServerProxy.Instance().gameParticipants(request.getParameter("game"));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public String[] getPlayers()
    {
        return(mParticipants.getPlayers());
    }
    
    public String[] getSpectators()
    {
        return(mParticipants.getSpectators());
    }
    
    public String getAliasesString()
    {
        String aliases = "new Array(";
        String[] players = mParticipants.getPlayers();
        if(players != null)
            aliases = append(aliases, players);
        String[] spectators = mParticipants.getSpectators();
        if(players != null && spectators != null && spectators.length > 0 && spectators[0] != null)
            aliases += ", ";
        if(spectators != null && spectators.length > 0 && spectators[0] != null)
            aliases = append(aliases, spectators);
        aliases += ");";
        return(aliases);
    }
    
    private String append(String aliases, String[] participants)
    {
        for(int index = 0; index < participants.length; index++)
        {
            aliases += "'" + participants[index] + "'";
            if(index < participants.length - 1)
            {
                if(participants[index + 1] == null) break;
                aliases += ", ";
            }
        }
        return(aliases);
    }
}
