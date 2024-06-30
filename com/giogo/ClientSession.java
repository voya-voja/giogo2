/*
 * ClientSession.java
 *
 * Created on June 28, 2003, 11:27 AM
 */

package com.giogo;

import java.util.Map;
import java.util.HashMap;
import java.io.IOException;

import com.inexum.IPC.Client;
import com.inexum.util.Queue;

/**
 *
 * @author  inexum
 */
public class ClientSession extends Thread
{
    private Map mPresenters = new HashMap();
    private String mGameId;
    private String mAlias;
    private Client mClient;
    private Presenter mDisplay;
    private String mSessionId;
    private boolean mStop = false;
    private ActivateClientSession mActivation;
    private int mReconnectTries = 0;
    private String mMessage = "";
    private Queue mQueue = null; 
    
    public ClientSession(String sessionId)
    {
        super("Presenter Manager");
        if(ClientSessionManager.debug)
            System.out.println(mAlias + ": ClientSession(sessionId:'" 
                                                            + sessionId + "')");
        mSessionId = sessionId;
        mQueue = new Queue(true);
    }

    void halt() 
    {
        if(ClientSessionManager.debug)
            System.out.println(mAlias + ": ClientSession.halt()");
	 mStop = true; 
    }

    public synchronized void run()
    {
        if(ClientSessionManager.debug)
            System.out.println(mAlias + ": ClientSession.run()");
        mDisplay.update("Wellcome!");
        while(!mStop)
        {
            if(isActive())
            {
                try{ wait(); } catch(Exception e) {}
            }
            if(mQueue.hasNext())
            {
                ClientRequest request = (ClientRequest)mQueue.remove();
                execute(request);
            }
            else
            {
                execute(new ClientRequest("NextAction"));
            }
            if(ClientSessionManager.debug)
                System.out.println(mAlias + ": ClientSession.run(): '" + mMessage + "'");
        }  
    }

    public void connect(String host, int port, String gameId, String alias)
                                                    throws java.io.IOException
    {
        if(ClientSessionManager.debug)
            System.out.println(mAlias + ": ClientSession.connect(host: '" + host + "', port: '"
                            + port + "',gameId: '" + gameId + "', alias: '"
                            + alias + "')");
        mClient = new Client(host, port);
        mClient.connect();
        mDisplay.update("Connected!");
        
        mGameId= gameId;
        mAlias = alias;
        execute(new ClientRequest("Login"));
        mDisplay.update("Logged!");
        start();
    }

    public void register(Presenter presenter)
    {
        if(ClientSessionManager.debug)
            System.out.println(mAlias + ": ClientSession.getPresenter(presenter:'" 
                                            + presenter.name() + "')");
        String name = presenter.name();
        mPresenters.put(name, presenter);
        if(mDisplay == null && "login".equalsIgnoreCase(name))
            mDisplay = presenter;
    }

    public Presenter getPresenter(String name)
    {
        if(ClientSessionManager.debug)
            System.out.println(mAlias + ": ClientSession.getPresenter(name:'" + name + "')");
        return((Presenter)mPresenters.get(name));
    }

    public boolean isActive() 
    { 
        if(ClientSessionManager.debug)
            System.out.println(mAlias + ": ClientSession.isActive()");
        return(mActivation != null); 
    }
    
    public void activate(ActivateClientSession activation) 
    {
        if(ClientSessionManager.debug)
            System.out.println(mAlias + ": ClientSession.activate()");
        mActivation = activation;
        mDisplay.update("Active!");
    }
    
    public ActivateClientSession activation() 
    { 
        if(ClientSessionManager.debug)
            System.out.println(mAlias + ": ClientSession.activation()");
        return(mActivation); 
    }
    
    public synchronized void deactivate()
    { 
        if(ClientSessionManager.debug)
            System.out.println(mAlias + ": ClientSession.deactivate()");
        mActivation = null;
        mDisplay.update(null);
        notifyAll();
    }
    
    public void destroy()
    {
        if(ClientSessionManager.debug)
            System.out.println(mAlias + ": ClientSession.destroy()");
        try
        {
            mClient.disconnect();
            ClientSessionManager.Instance().removeSession(mSessionId);
        }
        catch(java.io.IOException e)
        {
            e.printStackTrace();
        }
   
        mStop = true;
    }
    
    public synchronized Map getPresenters() 
    { 
        if(ClientSessionManager.debug)
            System.out.println(mAlias + ": ClientSession.getPresenters()");
        return(mPresenters); 
    }
    
    public void execute(ClientRequest request)
    {
        if(ClientSessionManager.debug)
            System.out.println(mAlias + ": ClientSession.execute(request for: '" 
                                + request.service() + "')");
        try
        {
            if(ClientSessionManager.debug)
                System.out.println(mAlias + ": pre GameId");
            request.gameId(mGameId);
            request.alias(mAlias);
            if(ClientSessionManager.debug)
                System.out.println(mAlias + ": ClientSession.send(): msg.size = " 
                                + mMessage.length());
            if(mMessage.length() > 0)
                request.message(releaseMessage());
            ClientAction action = (ClientAction)mClient.execute(request);
            if(action != null)
                action.execute(this);
            else
                mDisplay.update("Game replay ...");
        }
        catch(IOException e)
        {
            mDisplay.update("Error: " + e.getMessage());
            reconnect();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            mDisplay.update("Error: " + e.getMessage());
        }
    }

    public void send(ClientRequest request)
    {
        if(ClientSessionManager.debug)
            System.out.println(mAlias + ": ClientSession.send(request for: '" 
                                + request.service() + "')");
        mQueue.add(request);
    }
    
    public void send(ClientResponse response)
    {
        if(ClientSessionManager.debug)
            System.out.println(mAlias + ": ClientSession.send(response for: '" 
                                + response.service() + "')");
        try
        {
            response.gameId(mGameId);
            response.alias(mAlias);
            if(ClientSessionManager.debug)
                System.out.println(mAlias + ": ClientSession.send(): msg.size = " 
                                + mMessage.length());
            if(mMessage.length() > 0)
                response.message(releaseMessage());
            mClient.execute(response);
        }
        catch(IOException e)
        {
            mDisplay.update("Error: " + e.getMessage());
            reconnect();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            mDisplay.update("Error: " + e.getMessage());
        }
    }
    
    private void reconnect()
    {
        if(ClientSessionManager.debug)
            System.out.println(mAlias + ": ClientSession.reconnect()");
        try
        { 
            wait(10000); 
            if(mStop) return;

            if(ClientSessionManager.debug)
                System.out.println(mAlias + ": ClientSession.reconnect() -> Reconnecting ...");
            mClient.connect();
            mReconnectTries = 0;
        }
        catch(InterruptedException e) 
        {
        }
        catch(IOException e)
        {
            if(mReconnectTries == 12)
                mStop = true;
            mReconnectTries++;
        }
    }
    
    public void selected(Object selection)
    {
        if(ClientSessionManager.debug)
            System.out.println(mAlias + ": ClientSession.selected(objct of: '" 
                                + selection.getClass().toString() + "')");
        if(isActive())
            mActivation.execute(selection);
    }

    public void unselected(Object selection)
    {
        if(ClientSessionManager.debug)
            System.out.println(mAlias + ": ClientSession.unselected(objct of: '" 
                                + selection.getClass().toString() + "')");
        if(isActive())
            mActivation.cancel(selection);
    }
    
    public void sendMessage(String message)
    {
        if(ClientSessionManager.debug)
            System.out.println(mAlias + ": ClientSession.sendMessage('" + message + "')");
        if(mMessage.length() > 0)
            mMessage += ("\n\n");
        mMessage += mAlias + ": " + message;
        if(ClientSessionManager.debug)
            System.out.println(mAlias + ": ClientSession.sendMessage(): '" + mMessage + "'");
    }

    private String releaseMessage()
    {
        if(ClientSessionManager.debug)
            System.out.println(mAlias + ": ClientSession.releaseMessage('" + mMessage + "')");
        String message = mMessage;
        mMessage = "";
        return(message);
    }
    
    public String alias() { return(mAlias); }
}
