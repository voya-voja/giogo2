/*
 * Bid.java
 *
 * Created on July 30, 2003, 4:10 PM
 */

package com.giogo.game.bidding;

import java.io.Serializable;

/**
 *
 * @author  inexum
 */
public abstract class Bid implements Serializable
{
    String mName;
    
    /** Creates a new instance of Bid */
    public Bid( String name )
    {
        mName = name;
    }
    
    public boolean isStop() { return( false ); }
    
    public String name() { return(mName); }
    
    abstract public boolean greaterEqual(Bid bid);

    abstract public boolean greater(Bid bid);
    
    public boolean equals(Object obj)
    {
        if(super.equals(obj)) return true;
        try
        { 
            return( equals((Bid)obj) ); 
        }
        catch(ClassCastException e){}
        return(false);
    }

    abstract public boolean equals(Bid bid);
}
