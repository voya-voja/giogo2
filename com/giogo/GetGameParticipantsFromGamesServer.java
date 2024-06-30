/*
 * GetGameParticipantsFromGamesServer.java
 *
 * Created on May 10, 2004, 2:46 PM
 */

package com.giogo;

import com.inexum.IPC.ServiceRegistry;

/**
 *
 * @author  inexum
 */
public class GetGameParticipantsFromGamesServer extends GetGameParticipants 
{
    /** Creates a new instance of GetGameParticipantsFromGamesServer */
    public GetGameParticipantsFromGamesServer(String gameId) 
    {
        super(gameId);
    }
    
    /** Define specific functionality.
     *
     * @return an object that the function returns
     * @throws Exception - any application specific exception that is thrown by
     * the function
     */
    public Object run() throws Exception 
    {
        ServiceRegistry registry = ServiceRegistry.getDefaultInstance();
        IGamesServer server = (IGamesServer)registry.getServiceForName("com.giogo.IGamesServer");
        return(server.participantsFor(gameId()));
    }
}
