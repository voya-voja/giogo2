/*
 * IGiogoServer.java
 *
 * Created on April 17, 2004, 11:11 PM
 */

package com.giogo;

/**
 *
 * @author  inexum
 */
public interface IGiogoServer 
{
    public void remove(GameInfo gameInfo);
    public void update(GameInfo gameInfo);
    public void update(GamesServerInfo serverInfo, String[] supportedGames);
    public String newGameServerName();
}
