/*
 * CardHand.java
 *
 * Created on Jun 03, 2003, 11:01 PM
 */

package com.giogo.game.card;

import com.giogo.game.Player;

/**
 *
 * @author  Giogo
 * @version 1.0
 */

public abstract class CardHand
{
	/// The card deck player is holding at the beginning of the hand
	private Deck        mPlayDeck;
	private Player      mOwner;
	private CardHand    mPreviousHand;
}