/*
 * ActivateClientSession.java
 *
 * Created on August 1, 2003, 9:32 PM
 */

package com.giogo;

/**
 *
 * @author  inexum
 */
public class ActivateClientSession implements ClientAction
{
    private ClientSession mSession;
    private String mResponseService;
    private String[] mAppletNames;
    
    /** Creates a new instance of ActivateClientSession */
    public ActivateClientSession() 
    {
    }
    
    public ActivateClientSession(String[] activePresenters)
    {
        mAppletNames = activePresenters;
    }
    
    public String[] appletNames() { return mAppletNames; }
    
    public void execute(ClientSession session)
    {
        mSession = session;
        mSession.activate(this);
    }
    
    public void execute(Object selection)
    {
        ClientResponse response = onExecute(selection);
        if(response != null && mSession != null)
        {
            if(mResponseService != null)
                response.service(mResponseService);
            mSession.send(response);
            mSession.deactivate();
        }
    }
    
    public String name() { return(getClass().getName()); }
    
    public void responseService(String service) { mResponseService = service; }
        
    protected ClientResponse onExecute(Object selection) 
    {
        return(null);
    }
    
    public void cancel(Object selection)
    {
    }
}

