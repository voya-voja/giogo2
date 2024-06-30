/*
 * CardApplet.java
 *
 * Created on June 5, 2003, 8:28 PM
 */

package com.giogo.game.card;

import com.giogo.Applet;

import java.awt.Graphics;
import java.awt.Event;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;

/**
 *
 * @author  inexum
 */
public class CardApplet extends Applet implements CardPresenter 
{
    private Card    mCard;
    private SuitPresenter    mSuitPresenter;
    private CardDress    mCardDress = new CharacterLogoCardDress();
    
    public Card getCard() { return(mCard); }
    public void setCard(Card card) { mCard = card; }

    /** Initialization method that will be called after the applet is loaded
     *  into the browser.
     */
    public void init() 
    {
        super.init();
        if("true".equalsIgnoreCase(getParameter("enable")))
            activate();
    }

    public void paint(Graphics g)
    {
        Color c = g.getColor();
        g.setColor(new Color(255, 255, 255));
        g.fillRect(0,0, getWidth(), getHeight());
        g.setColor(c);
        if(mCard == null)
        {
            g.clearRect(0,0, getWidth(), getHeight());
            return;
        }

        int width = getWidth() - 2;
        int height = getHeight() - 2;
        if(width * 3 > height * 2)
            width = 2 * height/3 + 1;
        else
            height = 3 * width/2;
        g.drawRoundRect(1, 1, width, height, width/8, height/8);
        if(mCard.isFaceUp())
        {
            if(session().isActive() && isActive())
                mCardDress.paintActive(g, width, height);
            else
                mCardDress.paintFace(g, width, height);
            try
            {
                mSuitPresenter.paint(mCard.getSuit(), g, width, height);
            }
            catch(ClassNotFoundException e)
            {
                showStatus("Error: " + e.getMessage());
                e.printStackTrace();
            }

            if(mCard.getValue() != '\000')
            {
                g.setFont(new Font("Arial", Font.BOLD, (height - 12)/2));
                if(mCard.getValue() != '0')
                    g.drawString("" + mCard.getValue(), width/2, height - 4);
                else
                    g.drawString("" + 10, (width-16)/2, height - 4);
            }
        }
        else
        {
            mCardDress.paintBack(g, width, height);
        }
    }

    public void update(Object obj)
    {
        try
        {
            mCard = (Card)obj;
            deactivate();
        }
        catch(ClassCastException e)
        {
            showStatus("Error: " + e.getMessage());
            e.printStackTrace();
        }
        repaint();
    }
    
    protected CardApplet(SuitPresenter suitPresenter)
    {
        mSuitPresenter = suitPresenter;
    }

    protected void processMouseEvent(MouseEvent e)
    {
        if(e.getID() == e.MOUSE_CLICKED)
        {
            mCard = cardClicked();
            validate();
            repaint();
        }
    }
    
    protected Card cardClicked()
    {
        if(mCard != null && session().isActive())
        {
            if(mCard.isFaceUp())
            {
                session().selected(mCard);
                mCard.faceDown();
            }
            else
            {
                session().unselected(mCard);
                mCard.faceUp();
            }
        }
        return(mCard);
    }
}