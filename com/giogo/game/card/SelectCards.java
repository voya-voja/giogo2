/*
 * SelectCards.java
 *
 * Created on October 20, 2003, 5:38 PM
 */

package com.giogo.game.card;

import com.giogo.ActivateClientSession;
import com.giogo.ClientResponse;

/**
 *
 * @author  inexum
 */
public class SelectCards extends ActivateClientSession 
{
    int mCardsNo = 1;
    Card[] mCards;
    
    /** Creates a new instance of SelectCards */
    public SelectCards() 
    {
        mCards = new Card[mCardsNo];
    }
    
    /** Creates a new instance of SelectCards */
    public SelectCards(int cardsNo) 
    {
        mCardsNo = cardsNo;
        mCards = new Card[cardsNo];
    }
    
    protected ClientResponse onExecute(Object selection) 
    {
        mCardsNo--;
        mCards[mCardsNo] = (Card)selection;
        if(mCardsNo == 0)
            return(new ClientResponse("SelectedCards", mCards));
        
        return(null);
    }
    
    public void cancel(Object selection)
    {
        Card card = (Card)selection;
        boolean moveCards = false;
        for(int index = 0; index < mCards.length; index++)
        {
            if(moveCards)
                mCards[index - 1] = mCards[index];
            else if(card == mCards[index])
                moveCards = true;
        }
    }
}
