/*
 * IGameCreator.java
 *
 * Created on April 27, 2004, 10:07 PM
 */

package com.giogo;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author  inexum
 */
public interface IGameCreator 
{
    String gameID(HttpServletRequest request);
}
