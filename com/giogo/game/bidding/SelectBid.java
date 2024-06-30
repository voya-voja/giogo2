/*
 * SelectBid.java
 *
 * Created on October 20, 2003, 8:38 PM
 */

package com.giogo.game.bidding;

import com.giogo.ClientResponse;
import com.giogo.ActivateClientSession;
/**
 *
 * @author  inexum
 */
public class SelectBid extends ActivateClientSession
{
    private int mCardsNo = 1;
    
    protected ClientResponse onExecute(Object selection) 
    {
        return( new ClientResponse("SelectedBid", selection) );
    }
    
}
