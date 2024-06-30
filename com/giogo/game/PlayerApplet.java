/*
 * PlayerApplet.java
 *
 * Created on June 8, 2003, 12:02 PM
 */

package com.giogo.game;

import java.awt.Graphics; 

import com.giogo.Applet;

/**
 *
 * @author  inexum
 */
public class PlayerApplet extends Applet 
{
    private String mAlias = "player";
    private int mId = 0;
    
    public PlayerApplet() 
    {
    }
    /** Initialization method that will be called after the applet is loaded
     *  into the browser.
     */
    public void init() 
    {
        super.init();
        String alias = getParameter("alias");
        if( alias != null )
            mAlias = alias;
        String id = getParameter("id");
        if( id != null )
            mId = Integer.parseInt( id );
    }
    
    protected PlayerApplet( String name ) 
    {
        mAlias = name;
    }
    
    public void alias(String anAlias)
    {
        mAlias = anAlias;
    }
    
    public String alias()
    {
        return(mAlias);
    }
    
    public int id()
    {
        return( mId );
    }
    
    public void update(Object obj) 
    {
        try
        {
            update((PlayerInfo)obj);
        }
        catch(ClassCastException e)
        {
            showStatus( "Error: " + e.getMessage() );
            e.printStackTrace();
        }
    }
    
    public void update(PlayerInfo playerInfo) 
    {
        mAlias = playerInfo.alias();
        mId = playerInfo.id();
        repaint();
    }
    
    public void paint( Graphics g )
    {
        if( mAlias != null )
        {
            g.drawString( mAlias, 0, 10 );
        }
    }
}
