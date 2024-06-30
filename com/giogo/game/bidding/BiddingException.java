/*
 * BiddingException.java
 *
 * Created on August 28, 2003, 6:30 PM
 */

package com.giogo.game.bidding;

/**
 *
 * @author  inexum
 */
public class BiddingException extends com.giogo.game.GameException {
    
    /** Creates a new instance of BiddingException */
    public BiddingException( String message )
    {
            super( message );
    }
    public BiddingException( String message, Throwable cause )
    {
            super( message, cause );
    }
    
}
