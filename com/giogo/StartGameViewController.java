/*
 * StartGameViewController.java
 *
 * Created on April 28, 2004, 7:52 PM
 */

package com.giogo;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author  inexum
 */
public class StartGameViewController 
{
    String mGameId;
    String mAlias;
    
    /** Creates a new instance of StartGameViewController */
    public StartGameViewController(HttpServletRequest request) throws Exception
    {
        HashMap parameters = new HashMap();
        parameters.put("type", request.getParameter("type"));
        parameters.put("alias", request.getParameter("alias"));
        parameters.put("name", request.getParameter("name"));
        parameters.put("points", request.getParameter("points"));
        parameters.put("refe", request.getParameter("refe"));
        
        mGameId = GiogoServerProxy.Instance().startGame(parameters);
        mAlias = request.getParameter("alias");
    }
    
    public String gameId() { return(mGameId); }
    public String alias() { return(mAlias); }
}
