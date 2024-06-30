/*
 * PreferenceBiddingApplet.java
 *
 * Created on August 29, 2003, 4:35 PM
 */

package com.giogo.game.card.preference;

import com.giogo.game.bidding.BiddingApplet;

import java.awt.event.ActionEvent;

/**
 *
 * @author  inexum
 */
public class PreferenceBiddingApplet extends BiddingApplet 
{
    public void actionPerformed( ActionEvent e )
    {
        session().activation().responseService( "PreferenceSelectedBid" );
        super.actionPerformed(e);
    }
}
