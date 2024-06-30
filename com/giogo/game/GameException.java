/*
 * GameException.java
 *
 * Created on Jun 03, 2003, 11:01 PM
 */

package com.giogo.game;

/**
 *
 * @author  Giogo
 * @version 1.0
 */

public class GameException extends com.giogo.GiogoException
{
    public GameException( String message )
    {
            super( message );
    }
    
    public GameException( String message, Throwable cause )
    {
            super( message, cause );
    }
}