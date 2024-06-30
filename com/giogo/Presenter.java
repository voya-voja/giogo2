/*
 * Presenter.java
 *
 * Created on June 24, 2003, 7:14 PM
 */

package com.giogo;

/**
 *
 * @author  inexum
 */
public interface Presenter {
    
    String name();
    
    void update(Object obj);
    void activate();
    void deactivate();
}
