/*
 * PlayerInfo.java
 *
 * Created on Jun 03, 2003, 11:01 PM
 */

package com.giogo.game;

/**
 *
 * @author  Giogo
 * @version 1.0
 */

public class PlayerInfo implements java.io.Serializable
{
    private int mId;
    private String mAlias;

    public PlayerInfo(int id, String alias)
    {
	mId = id;
        mAlias = alias;
    }

    public int id() { return(mId); }
    public String alias() { return(mAlias); }
}
