/*
 * Queue.java
 *
 * Created on December 31, 2005, 11:59 AM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.inexum.util;

import java.util.LinkedList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

/**
 *
 * @author nvojinovic
 */
public class Queue 
{
    private Collection  mQueue = null;
    private int         mMaximalSize = 1024;
    private Iterator    mIterator;
    
    /** Creates a new instance of Queue */
    public Queue() 
    {
        mQueue = createQueueCollector(false);
        mIterator = mQueue.iterator();
    }
    
    /** Creates a new instance of synchronized Queue if requested
     */
    public Queue(boolean syncQueue) 
    {
        mQueue = createQueueCollector(syncQueue);
        mIterator = mQueue.iterator();
    }

    protected Collection createQueueCollector(boolean syncQueue)
    {
        Collection queue = null;
        if(syncQueue)
            queue = Collections.synchronizedList(new LinkedList());
        else
            queue = new LinkedList();
        return(queue);
    }
    
    public boolean hasNext()
    {
        return(mIterator.hasNext());
    }
    
    public void add(Object object)
    {
        mQueue.add(object);
        mIterator = mQueue.iterator();
    }
    
    public Object remove()
    {
        Object object = mIterator.next();
        mIterator.remove();
        return(object);
    }
}
