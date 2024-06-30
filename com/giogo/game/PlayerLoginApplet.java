/*
 * LoginApplet.java
 *
 * Created on June 13, 2003, 2:50 PM
 */

package com.giogo.game;

import com.giogo.ClientSessionManager;

import java.awt.Graphics;
import java.awt.Font;

/**
 *
 * @author  inexum
 */
public class PlayerLoginApplet extends PlayerApplet
{
    String mHost = "localhost";
    int mPort = 13000;
    String mGameId;
    String mMessage;

    /** Initialization method that will be called after the applet is loaded
     *  into the browser.
     */
    public void init() 
    {
        String sessionId = getParameter("session.id");
        if(sessionId == null)
            sessionId = sessionId();
        ClientSessionManager.Instance().createSession(sessionId);
        
        super.init();
        String param = getParameter("host");
        if(param != null)
            mHost = param;
        param = getParameter("port");
        if(param != null)
            mPort = Integer.parseInt(param);
        param = getParameter("gameId");
        if(param != null)
            mGameId = param;
        try
        {
            session().connect(mHost, mPort, mGameId, alias()); 
        }
        catch(java.io.IOException e)
        {
            e.printStackTrace();
            mMessage = "Failed to connect to '" + mHost + ":" 
                                    + mPort + "' or login as '" + alias() + "'!";
        }
        catch(Exception e)
        {
            e.printStackTrace();
            mMessage = "Failed to login: " + alias() + "@" + mHost + ":" 
                                                        + mPort + "/" + mGameId;
            mMessage = e.getMessage();
        }
    }
    
    public void paint(Graphics g)
    {
        if(mMessage != null)
        {
            g.setFont(new Font("Arial", Font.BOLD, 12));
            int lineEnd = mMessage.indexOf('\n');
            int lineStart = 0;
            int lineNo = 1;
            while(lineEnd != -1)
            {
                String message = mMessage.substring(lineStart, lineEnd);
                g.drawString(message, 5, lineNo * 20);
                lineStart = lineEnd + 1;
                lineEnd = mMessage.indexOf('\n', lineStart);
                lineNo++;
            }
            g.drawString(mMessage.substring(lineStart), 5, lineNo * 20);
        }
    }
    
    public void update(Object obj)
    {
        try
        {
            mMessage = (String)obj;
        }
        catch(ClassCastException e)
        {
            showStatus("Error: " + e.getMessage());
            e.printStackTrace();
        }
        repaint();
    }
    
    public void stop()
    {
        session().destroy();
        super.stop();
    }
}
