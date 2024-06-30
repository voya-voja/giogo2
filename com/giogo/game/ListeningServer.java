/*
 * ListenerServer.java
 *
 * Created on May 10, 2004, 4:39 PM
 */

package com.giogo.game;

import com.inexum.IPC.Session;

import java.net.Socket;
import java.io.IOException;

/**
 *
 * @author  inexum
 */
public class ListeningServer extends com.inexum.IPC.Server 
{
    /** Default constructor. */    
    public ListeningServer() {}

    /** Creates a new instance of a ListenerServer .
     *
     * @param url - a server URL.
     * @throws IOException if is unable to open a connection.
     */    
    public ListeningServer(int port) throws IOException 
    {
        super(port);
    }

    /** Create a listener session.
     *
     * @param socket - a listener socket.
     */    
    protected Session createSession(Socket socket)
    {
        return(new ListenerSession(socket));
    }
}
