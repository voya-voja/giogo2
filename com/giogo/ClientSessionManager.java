/*
 * PresenterManager.java
 *
 * Created on June 8, 2003, 11:54 AM
 */

package com.giogo;

import java.util.Map;
import java.util.HashMap;

/**
 *
 * @author  inexum
 */
public class ClientSessionManager  
{
    static public boolean debug = true;
    
    private Map mSessions = new HashMap();
    
    private static ClientSessionManager gmInstance = new ClientSessionManager();
    
    public static ClientSessionManager Instance()
    {
        return(gmInstance);
    }
    
    public synchronized ClientSession getSession(String sessionId)
    {
        if(ClientSessionManager.debug)
            System.out.println("ClientSessionManager.getSession(sessionId: '" 
                                    + sessionId + "')");
        ClientSession session = (ClientSession)mSessions.get(sessionId);
        while(session == null)
        {
            try{ wait(200); } catch(InterruptedException e) { }
            session = (ClientSession)mSessions.get(sessionId);
        }
        return(session);
    }

    public synchronized ClientSession createSession(String sessionId)
    {
        if(ClientSessionManager.debug)
            System.out.println("ClientSessionManager.createSession(sessionId: '" 
                                    + sessionId + "')");
        ClientSession session = (ClientSession)mSessions.get(sessionId);
        if(session == null)
        {
           session = new ClientSession(sessionId);
           mSessions.put(sessionId, session);
           notifyAll();
        }
        return(session);
    }

    public synchronized ClientSession removeSession(String sessionId)
    {
        if(ClientSessionManager.debug)
            System.out.println("ClientSessionManager.removeSession(sessionId: '" 
                                    + sessionId + "')");
        ClientSession session = (ClientSession)mSessions.remove(sessionId);
        notifyAll();
        return(session);
    }
     
    /** Creates a new instance of PresenterManager */
    private ClientSessionManager() 
    {
        if(ClientSessionManager.debug)
            System.out.println("ClientSessionManager()");
    }
}
