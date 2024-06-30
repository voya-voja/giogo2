/*
 * ListenerSession.java
 *
 * Created on May 10, 2004, 4:37 PM
 */

package com.giogo.game;

import java.net.Socket;
import java.io.IOException;

/**
 *
 * @author  inexum
 */
public class ListenerSession extends com.inexum.IPC.Session 
{
    Game mGame;
    String mAlias;
    
    /** Creates a new instance of ListenerSession */
    public ListenerSession(Socket socket) 
    {
        super(socket);
    }
    
    /** On halt event closes a Session socket.
     *
     * @throws IOException if is unable to close a connection.
     */
    protected void onHalt() throws IOException 
    {
        if(mGame == null) return;
        String alias = getName();
        Listener listener = mGame.getListener(mAlias);
        if(listener != null)
            listener.inactive();
    }
    
    public void assign(Game game, String alias)
    {
        mGame = game;
        mAlias = alias;
        setName(game.id() + "_" + alias);
    }
}
