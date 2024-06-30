/*
 * CardBack.java
 *
 * Created on Jun 03, 2003, 11:01 PM
 */

package com.giogo.game.card;

import java.awt.Graphics;

/**
 *
 * @author  Giogo
 * @version 1.0
 */

public interface CardDress
{
    public void paintActive(Graphics g, int width, int height);
    public void paintFace(Graphics g, int width, int height);
    public void paintBack(Graphics g, int width, int height);
}