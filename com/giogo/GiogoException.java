/*
 * GameException.java
 *
 * Created on Jun 03, 2003, 11:01 PM
 */

package com.giogo;

/**
 *
 * @author  Giogo
 * @version 1.0
 */

public class GiogoException extends Exception
{
    public GiogoException()
    {
    }
    
    public GiogoException(String message)
    {
            super( message );
    }
    
    public GiogoException(String message, Throwable cause)
    {
            super( message, cause );
    }
}