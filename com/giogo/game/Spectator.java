/*
 * Spectator.java
 *
 * Created on Jun 03, 2003, 11:01 PM
 */

package com.giogo.game;

/**
 *
 * @author  Giogo
 * @version 1.0
 */

public class Spectator extends Listener
{
    public Spectator(Game game)
    {
        super("Spectator #" + game.getSpectators().size(), game);
    }
    
    public Spectator(String alias, Game game)
    {
        super(alias, game);
    }
}