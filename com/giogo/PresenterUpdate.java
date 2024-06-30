/*
 * PresenterUpdate.java
 *
 * Created on June 8, 2003, 5:20 PM
 */

package com.giogo;

import java.io.Serializable;

/**
 *
 * @author  inexum
 */
public class PresenterUpdate implements ClientAction 
{
    static protected class State implements Serializable
    {
        private final int mValue;

        public State(int stateValue)
        {
            mValue = stateValue;
        }
        
        public boolean equals(Object obj)
        {
            try
            {
                return(equals((State)obj));
            }
            catch(ClassCastException e){}
            return(false);
        }
        
        public boolean equals(State right)
        {
            return(mValue == right.mValue);
        }       
    }

    public static final Object cNoData = new Integer(0);
    
    public static final State cUnchanged = new State(0);
    public static final State cEnable = new State(1);
    public static final State cDisable = new State(2);
    
    public PresenterUpdate(String name)
    {
        mName = name;
    }

    public PresenterUpdate(String name, Object info)
    {
        mName = name;
        mInfo = info;
    }

    public PresenterUpdate(String name, Object info, State state)
    {
        mName = name;
        mInfo = info;
        mState = state;
    }

    public void execute(ClientSession session)
    {
        Presenter presenter = session.getPresenter(mName);
        if(presenter != null)
        {
            if(mInfo == null || !mInfo.equals(cNoData))
                presenter.update(mInfo);
            
            if(mState.equals(cEnable))
                presenter.activate();
            else if(mState.equals(cDisable))
                presenter.deactivate();
        }
    }

    public String name() { return(getClass().getName()); }
    
    private String mName;
    private Object mInfo;
    private State mState = cUnchanged;
}
