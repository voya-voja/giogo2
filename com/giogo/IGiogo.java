/*
 * IGiogo.java
 *
 * Created on April 16, 2004, 10:16 AM
 */

package com.giogo;

import java.util.Map;

/**
 *
 * @author  nvojinov
 */
public interface IGiogo 
{
    public GamesServerInfo getGamesServerInfo(String gameId) throws Exception;
    public GameInfo[] underwayGames(String gameName) throws Exception;    
    public String startGame(Map parameters) throws Exception;
    public GameParticipantsInfo gameParticipants(String gameId) throws Exception;
}
