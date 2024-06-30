/*
 * Suit.java
 *
 * Created on Jun 03, 2003, 11:01 PM
 */

package com.giogo.game.card;

import java.awt.Color;
import java.io.Serializable;

/**
 *
 * @author  Giogo
 * @version 1.0
 */

public interface Suit extends Comparable, Serializable
{
	public boolean equals(Suit suit);

	public int compareTo(Suit suit);

	public Color color();
}