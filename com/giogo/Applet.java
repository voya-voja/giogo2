/*
 * Applet.java
 *
 * Created on June 16, 2003, 6:17 PM
 */

package com.giogo;

import java.awt.event.MouseEvent;

/**
 *
 * @author  inexum
 */
public abstract class Applet extends java.applet.Applet implements Presenter 
{
    private String mName = "Applet";
    private String mSessionId = "0";
    private ClientSession mSession;
    private boolean mActive = false;
    
    public Applet() {}
    
    protected Applet(String name)
    {
        mName = name;
    }
    
    public String name()
    {
        return(mName);
    }
    
    /** Initialization method that will be called after the applet is loaded
     *  into the browser.
     */
    public void init() 
    {
        String name = getParameter("name");
        if(name != null)
            mName = name;
        String sessionId = getParameter("session.id");
        if(sessionId != null)
            mSessionId = sessionId;
        mSession = ClientSessionManager.Instance().getSession(mSessionId);
        mSession.register(this);

        setBackground(new java.awt.Color(255, 255, 255));
        String background = getParameter("background");
        if(background != null)
        {
            int index = background.indexOf('x');
            if(index < 0) index = 0;
            int rgb = Integer.parseInt(background.substring(index+1), 16);
            setBackground(new java.awt.Color(rgb));
        }
    }

    public void activate()
    {
        enableEvents(MouseEvent.MOUSE_CLICKED);
        mActive = true;
        repaint();
    }

    public void deactivate()
    {
        disableEvents(MouseEvent.MOUSE_CLICKED);
        mActive = false;
        repaint();
    }
    
    public boolean isActive() { return(mActive); }
    
    abstract public void update(Object obj);
    
    protected String sessionId() { return(mSessionId); }
    protected ClientSession session() { return(mSession); }
}
