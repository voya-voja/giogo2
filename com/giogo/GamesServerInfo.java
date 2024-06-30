/*
 * GameServerInfo.java
 *
 * Created on April 2, 2004, 4:46 PM
 */

package com.giogo;

import java.io.Serializable;

/**
 *
 * @author  nvojinov
 */
public class GamesServerInfo implements Serializable
{
    String mName;
    String mHost;
    int    mPort;

    /** Creates a new instance of GameServerInfo */
    public GamesServerInfo(String name, String host, int port) 
    {
        mName = name;
        mHost = host;
        mPort = port;
    }
    
    public void name(String name) { mName = name; }
    
    public String name() { return(mName); }
    public String host() { return(mHost); }
    public int port() { return(mPort); }
}
