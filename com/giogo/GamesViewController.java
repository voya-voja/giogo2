/*
 * GamesViewController.java
 *
 * Created on April 8, 2004, 6:53 PM
 */

package com.giogo;

/**
 *
 * @author  nvojinov
 */
public class GamesViewController 
{
    /** Creates a new instance of GamesViewController */
    public GamesViewController() 
    {
    }
    
    public GameInfo[] getListOfGames(String gameName) throws Exception
    {
        return((GameInfo[])GiogoServerProxy.Instance().underwayGames(gameName));
    }
    
}
