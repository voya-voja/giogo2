/*
 * GameViewController.java
 *
 * Created on April 26, 2004, 3:09 PM
 */

package com.giogo.game;

import com.giogo.GiogoServerProxy;
import com.giogo.GamesServerInfo;
import com.giogo.IGameCreator;

import javax.servlet.http.HttpServletRequest;


/**
 *
 * @author  inexum
 */
public class GameViewController 
{
    GamesServerInfo  mGamesServerInfo = null;
    String  mGameId;
    String  mAlias;
    static int gmSessionId = 0;

    /** Creates a new instance of GameViewController */
    public GameViewController(HttpServletRequest request)
    {
        mGameId = request.getParameter("game");
        mAlias = request.getParameter("alias");
	gmSessionId++;
    }
    
    public String alias() { return(mAlias); }
    public String gameId() { return(mGameId); }

    public String session()
    {
        return(Integer.toString(gmSessionId));
    }
    
    public String host()
    {
        if(mGamesServerInfo == null)
        {
            try
            {
                mGamesServerInfo = 
                        GiogoServerProxy.Instance().getGamesServerInfo(mGameId);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        return(mGamesServerInfo.host());
    }
    
    public String port()
    {
        if(mGamesServerInfo == null)
        {
            try
            {
                mGamesServerInfo = 
                        GiogoServerProxy.Instance().getGamesServerInfo(mGameId);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        return(Integer.toString(mGamesServerInfo.port()));
    }
}
