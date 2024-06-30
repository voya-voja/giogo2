/*
 * PresentFinalScore.java
 *
 * Created on March 18, 2004, 5:26 PM
 */

package com.giogo.game;

import com.giogo.ClientAction;
import com.giogo.ClientUpdate;
import com.giogo.PresenterUpdate;
import com.giogo.StopClientSession;

/**
 *
 * @author  inexum
 */
public class PresentFinalScore extends Action 
{
    String mScoreMessage = "";
    
    /** Creates a new instance of PresentFinalScore */
    public PresentFinalScore(Game game, String[] playerNames, String[] playerScores) 
    {
        super(game);
        for(int pCount= 0; pCount < playerNames.length; pCount++)
            mScoreMessage += (pCount + 1) + ". " 
                            + playerNames[pCount] + " = "
                            + playerScores[pCount] + "\n";
    }
    
    public ClientAction gameViewAction() 
    {
        Specification spec = (Specification)game().specification();
        ClientAction[] actions = {
            new StopClientSession(),
            new PresenterUpdate(spec.loginPresenterName(), mScoreMessage)
        };
        return(new ClientUpdate(actions));
    }
    
}
