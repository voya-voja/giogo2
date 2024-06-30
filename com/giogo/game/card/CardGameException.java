/*
 * CardGameException.java
 *
 * Created on Jun 03, 2003, 11:01 PM
 */

package com.giogo.game.card;

import com.giogo.game.GameException;

/**
 *
 * @author  Giogo
 * @version 1.0
 */

public class CardGameException extends GameException
{
    public CardGameException( String message )
    {
        super( message );
    }
    
    public CardGameException( String message, Exception cause )
    {
        super( message, cause );
    }
}