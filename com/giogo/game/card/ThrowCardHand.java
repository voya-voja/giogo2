/*
 * ThrowCardHand.java
 *
 * Created on Jun 03, 2003, 11:01 PM
 */

package com.giogo.game.card;

import java.util.List;

/**
 *
 * @author  Giogo
 * @version 1.0
 */

public class ThrowCardHand extends CardHand
{
	/// List of the cards user took at the end of the hand
	private List	mTricks;

	/// List of the cards player threw at during the hand
	private List	mThrows;
}