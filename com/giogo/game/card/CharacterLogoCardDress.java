/*
 * CharacterLogoCardDress.java
 *
 * Created on Jun 03, 2003, 11:01 PM
 */

package com.giogo.game.card;

import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;

/**
 *
 * @author  Giogo
 * @version 1.0
 */

public class CharacterLogoCardDress implements CardDress 
{
    private static String gmLogo = "gamaxy";
    private static Color gmBackColor = new Color(0,0,0);
    private static Color gmFaceColor = new Color(204, 204, 204);
    private static Color gmActiveColor = new Color(0, 255, 0);
    private static Font gmFont;
    
    public void paintActive(Graphics g, int width, int height)
    {
        g.setColor(gmActiveColor);
        paint(g, width, height);
    }

    public void paintFace(Graphics g, int width, int height)
    {
        g.setColor(gmFaceColor);
        paint(g, width, height);
    }
    
    public void paintBack(Graphics g, int width, int height)
    {
        g.setColor(gmBackColor);
        paint(g, width, height);
    }
    
    protected void paint(Graphics g, int width, int height)
    {
        Font font = gmFont;
        if(font == null)
        {
            int fontSize = width/ gmLogo.length() + 1;
            font = new Font("LithographLight", Font.BOLD, fontSize);
        }
        g.setFont(font);
        g.drawString(gmLogo, 2, (height+font.getSize())/2);
    }
}