/*
 * WipeAllPresenters.java
 *
 * Created on July 11, 2003, 5:48 PM
 */

package com.giogo.game;

import com.giogo.ClientAction;
import com.giogo.ClientSession;
import com.giogo.Presenter;

import java.util.Collection;
import java.util.Iterator;

/**
 *
 * @author  inexum
 */
public class WipeAllPresenters implements ClientAction
{
    /** Creates a new instance of WipeAllPresenters */
    public WipeAllPresenters()
    {
    }
    
    public void execute(ClientSession session)
    {
        Collection presentersCollection = session.getPresenters().values();
        Iterator presenters = presentersCollection.iterator();
        while(presenters.hasNext())
        {
            Presenter presenter = (Presenter)presenters.next();
            presenter.update(null);
        }
    }
    
    public String name() { return(getClass().getName()); }
}
