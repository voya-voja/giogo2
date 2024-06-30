/*
 * IGameServer.java
 *
 * Created on April 18, 2004, 12:10 AM
 */

package com.giogo;

import java.util.Map;

/**
 *
 * @author  inexum
 */
public interface IGamesServer 
{
    public GameInfo initiateGame(Map parameters) throws Exception;
    public GameParticipantsInfo participantsFor(String gameId) throws Exception;
}
