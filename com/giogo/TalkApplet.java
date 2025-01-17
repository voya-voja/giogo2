/*
 * TalkApplet.java
 *
 * Created on April 23, 2004, 7:45 PM
 */

package com.giogo;

import java.awt.TextArea;
import java.awt.event.ActionListener;

/**
 *
 * @author  inexum
 */
public class TalkApplet extends Applet implements ActionListener
{
    /** Initializes the applet TalkApplet */
    public void init() 
    {
        super.init();
        initComponents();
        mSendButton.addActionListener(this);
    }
    
    /** This method is called from within the init() method to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        mEdit = new TextArea("", 0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
        mSendButton = new java.awt.Button();
        mDisplay = new TextArea("", 0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);

        setLayout(new java.awt.BorderLayout());

        add(mEdit, java.awt.BorderLayout.NORTH);

        mSendButton.setActionCommand("send");
        mSendButton.setLabel("Send");
        mSendButton.setName("send");
        add(mSendButton, java.awt.BorderLayout.SOUTH);

        mDisplay.setEditable(false);
        add(mDisplay, java.awt.BorderLayout.CENTER);

    }//GEN-END:initComponents

    public void actionPerformed(java.awt.event.ActionEvent actionEvent) 
    {
        if("send".compareToIgnoreCase(actionEvent.getActionCommand()) == 0)
        {
            session().sendMessage(mEdit.getText());
            mEdit.setText("");
        }
    }    
    
    public void update(Object obj)
    {
        try
        {
            String message = (String)obj;
            if(mDisplay.getText().length() > 0)
                mDisplay.append("\n\n");
            mDisplay.append(message);
        }
        catch(ClassCastException e)
        {
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button mSendButton;
    private java.awt.TextArea mEdit;
    private java.awt.TextArea mDisplay;
    // End of variables declaration//GEN-END:variables
    
}
