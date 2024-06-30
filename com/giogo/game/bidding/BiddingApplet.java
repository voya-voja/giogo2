/*
 * BiddingApplet.java
 *
 * Created on July 2, 2003, 3:32 PM
 */

package com.giogo.game.bidding;

import com.giogo.Applet;
import com.giogo.ClientResponse;

import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

/**
 *
 * @author  inexum
 */
public class BiddingApplet extends Applet implements ActionListener
{
    Bid[] mBids;
    
    /** Initialization method that will be called after the applet is loaded
     *  into the browser.
     */
    public void init() 
    {
        setLayout( new GridLayout( 4, 2, 3, 2 ) );
        super.init();
    }
    
    public void actionPerformed( ActionEvent e )
    {
        for( int bCount = 0; bCount < mBids.length; bCount++ )
        {
            if( mBids[bCount].name().equals( e.getActionCommand() ) )
            {
                session().selected(mBids[bCount]);
                break;
            }
        }
        removeAll();
    }

    public void update( Object obj ) 
    {
        removeAll();
        try
        {
            mBids = (Bid[])obj;
            for( int bCount = 0; bCount < mBids.length; bCount++ )
            {
                Button b = new Button( mBids[bCount].name() );
                b.addActionListener( this );
                add( b, bCount );
            }
            validate();
        }
        catch( ClassCastException e )
        {
            showStatus( "Error: " + e.getMessage() );
            e.printStackTrace();
        }
    }
}
