/*
 * TricksUpdateApplet.java
 *
 * Created on December 31, 2005, 8:16 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.giogo.game.card;

import com.giogo.game.PlayerApplet;
import com.giogo.ClientRequest;

/**
 *
 * @author nvojinovic
 */
public class TricksUpdateApplet extends PlayerApplet
{
    /** Creates a new instance of TricksUpdateApplet */
    public TricksUpdateApplet()
    {
    }
    
    /** Initialization method that will be called after the applet is loaded
     *  into the browser.
     */
    public void init() 
    {
        super.init();
        try
        {
            session().send(new ClientRequest("TricksUpdate"));
            String anAlias = session().alias();
            alias(anAlias);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }    
}
