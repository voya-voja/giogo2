/*
 * AppletScanfTest.java
 *
 * Created on July 3, 2003, 12:49 PM
 */

package com.giogo.game.bidding;

//Example for scanf
//Copyright by Softhema 2002
//Autor:Harald Stübinger
//All rights reserved.

import java.awt.*;
import java.awt.event.*;
//import softhema.system.toolkits.*;
import java.applet.*;

public class AppletScanfTest extends Applet
{
  Panel panel1 = new Panel();
  Label label1 = new Label();
  Label label2 = new Label();
  Label label3 = new Label();
  Label label4 = new Label();
  Panel panel2 = new Panel();
  Label label5 = new Label();
  BorderLayout borderLayout2 = new BorderLayout();
  Label label6 = new Label();
  Panel panel3 = new Panel();
  BorderLayout borderLayout3 = new BorderLayout();
  Panel panel4 = new Panel();
  Panel panel5 = new Panel();
  TextArea txtInput = new TextArea();
  Label label7 = new Label();
  BorderLayout borderLayout4 = new BorderLayout();
  Panel panel6 = new Panel();
  Label label8 = new Label();
  TextField txtFormatString = new TextField();
  BorderLayout borderLayout5 = new BorderLayout();
  Panel panel7 = new Panel();
  BorderLayout borderLayout6 = new BorderLayout();
  Button btnGenerate = new Button();
  Panel panel8 = new Panel();
  Panel panel9 = new Panel();
  Label label9 = new Label();
  Label label10 = new Label();
  Label label11 = new Label();
  Label label12 = new Label();
  BorderLayout borderLayout8 = new BorderLayout();
  Panel panel10 = new Panel();
  Label label13 = new Label();
  TextArea txtAS2 = new TextArea();
  BorderLayout borderLayout9 = new BorderLayout();
  TextArea txtAS3 = new TextArea();
  Label label14 = new Label();
  BorderLayout borderLayout10 = new BorderLayout();
  Panel panel11 = new Panel();
  TextArea txtAS4 = new TextArea();
  Label label15 = new Label();
  BorderLayout borderLayout11 = new BorderLayout();
  Panel panel12 = new Panel();
  TextArea txtAS5 = new TextArea();
  Label label16 = new Label();
  BorderLayout borderLayout12 = new BorderLayout();
  Panel panel13 = new Panel();
  TextArea txtAS0 = new TextArea();
  Label label17 = new Label();
  BorderLayout borderLayout13 = new BorderLayout();
  Panel panel14 = new Panel();
  TextArea txtAS1 = new TextArea();
  Label label18 = new Label();
  BorderLayout borderLayout14 = new BorderLayout();
  Panel panel15 = new Panel();
  BorderLayout borderLayout15 = new BorderLayout();
  GridLayout gridLayout1 = new GridLayout();
  Panel panel16 = new Panel();
  BorderLayout borderLayout1 = new BorderLayout();
  Panel panel17 = new Panel();
  Button btnExample2 = new Button();
  Button btnExample3 = new Button();
  Button btnExample4 = new Button();
  Button btnExample1 = new Button();
  Button btnExample5 = new Button();
  Button btnExample6 = new Button();
  GridLayout gridLayout2 = new GridLayout();
  BorderLayout borderLayout16 = new BorderLayout();
  Panel panel18 = new Panel();
  BorderLayout borderLayout7 = new BorderLayout();
  Panel panel19 = new Panel();
  Label label19 = new Label();
  TextField txtCount = new TextField();
  FlowLayout flowLayout1 = new FlowLayout();
  BorderLayout borderLayout17 = new BorderLayout();

  public AppletScanfTest()
  {
   try 
   {
    this.setLayout(borderLayout1);
    panel1.setLayout(borderLayout2);
    label5.setText("String[] as = new String[6];");
    panel2.setLayout(borderLayout3);
    label6.setText("count = softhema.system.toolkits.ToolkitString.sscanf( input, formatstring, " +
    "as);");
    panel3.setLayout(borderLayout15);
    panel4.setLayout(borderLayout4);
    label7.setText("input:");
    panel5.setLayout(borderLayout6);
    panel6.setLayout(borderLayout5);
    label8.setText("formatstring:");
    panel7.setLayout(borderLayout17);
    btnGenerate.setLabel("scan input");
    btnGenerate.addActionListener(new java.awt.event.ActionListener()
    {

      public void actionPerformed(ActionEvent e)
      {
        btnGenerate_actionPerformed(e);
      }
    });
    panel8.setLayout(borderLayout8);
    panel9.setLayout(gridLayout1);
    borderLayout6.setVgap(5);
    panel10.setLayout(borderLayout9);
    label13.setText("as[2] :");
    txtAS2.setEditable(false);
    txtAS3.setEditable(false);
    label14.setText("as[3] :");
    panel11.setLayout(borderLayout10);
    txtAS4.setEditable(false);
    label15.setText("as[4] :");
    panel12.setLayout(borderLayout11);
    txtAS5.setEditable(false);
    label16.setText("as[5] :");
    panel13.setLayout(borderLayout12);
    txtAS0.setEditable(false);
    label17.setText("as[0] :");
    panel14.setLayout(borderLayout13);
    txtAS1.setEditable(false);
    label18.setText("as[1] :");
    panel15.setLayout(borderLayout14);
    gridLayout1.setRows(6);
    panel16.setLayout(borderLayout16);
    panel17.setLayout(gridLayout2);
    btnExample2.setLabel("Example 2");
    btnExample2.addActionListener(new java.awt.event.ActionListener()
    {

      public void actionPerformed(ActionEvent e)
      {
        btnExample2_actionPerformed(e);
      }
    });
    btnExample3.setLabel("Example 3");
    btnExample3.addActionListener(new java.awt.event.ActionListener()
    {

      public void actionPerformed(ActionEvent e)
      {
        btnExample3_actionPerformed(e);
      }
    });
    btnExample4.setLabel("Example 4");
    btnExample4.addActionListener(new java.awt.event.ActionListener()
    {

      public void actionPerformed(ActionEvent e)
      {
        btnExample4_actionPerformed(e);
      }
    });
    btnExample1.setLabel("Example 1");
    btnExample1.addActionListener(new java.awt.event.ActionListener()
    {

      public void actionPerformed(ActionEvent e)
      {
        btnExample1_actionPerformed(e);
      }
    });
    btnExample5.setLabel("Example 5");
    btnExample5.addActionListener(new java.awt.event.ActionListener()
    {

      public void actionPerformed(ActionEvent e)
      {
        btnExample5_actionPerformed(e);
      }
    });
    btnExample6.setLabel("Example 6");
    btnExample6.addActionListener(new java.awt.event.ActionListener()
    {

      public void actionPerformed(ActionEvent e)
      {
        btnExample6_actionPerformed(e);
      }
    });
    gridLayout2.setHgap(3);
    borderLayout3.setVgap(4);
    panel18.setLayout(borderLayout7);
    panel19.setLayout(flowLayout1);
    label19.setText("count:");
    borderLayout17.setVgap(2);
    borderLayout15.setVgap(3);
    txtCount.setEditable(false);
    txtCount.setColumns(10);
    this.add(label1, BorderLayout.NORTH);
    this.add(label2, BorderLayout.EAST);
    this.add(label3, BorderLayout.SOUTH);
    this.add(label4, BorderLayout.WEST);
    this.add(panel16, BorderLayout.CENTER);
    panel16.add(panel1, BorderLayout.CENTER);
    panel1.add(panel2, BorderLayout.CENTER);
    panel2.add(label6, BorderLayout.NORTH);
    panel2.add(panel3, BorderLayout.CENTER);
    panel3.add(panel4, BorderLayout.NORTH);
    panel4.add(txtInput, BorderLayout.CENTER);
    panel4.add(label7, BorderLayout.WEST);
    panel3.add(panel5, BorderLayout.CENTER);
    panel5.add(panel6, BorderLayout.NORTH);
    panel6.add(label8, BorderLayout.WEST);
    panel6.add(txtFormatString, BorderLayout.CENTER);
    panel5.add(panel7, BorderLayout.CENTER);
    panel7.add(panel18, BorderLayout.CENTER);
    panel18.add(panel8, BorderLayout.CENTER);
    panel8.add(panel9, BorderLayout.CENTER);
    panel9.add(panel14, null);
    panel14.add(label17, BorderLayout.WEST);
    panel14.add(txtAS0, BorderLayout.CENTER);
    panel9.add(panel15, null);
    panel15.add(label18, BorderLayout.WEST);
    panel15.add(txtAS1, BorderLayout.CENTER);
    panel9.add(panel10, null);
    panel10.add(label13, BorderLayout.WEST);
    panel10.add(txtAS2, BorderLayout.CENTER);
    panel9.add(panel11, null);
    panel11.add(label14, BorderLayout.WEST);
    panel11.add(txtAS3, BorderLayout.CENTER);
    panel9.add(panel12, null);
    panel12.add(label15, BorderLayout.WEST);
    panel12.add(txtAS4, BorderLayout.CENTER);
    panel9.add(panel13, null);
    panel13.add(label16, BorderLayout.WEST);
    panel13.add(txtAS5, BorderLayout.CENTER);
    panel8.add(label9, BorderLayout.NORTH);
    panel8.add(label10, BorderLayout.WEST);
    panel8.add(label11, BorderLayout.EAST);
    panel8.add(label12, BorderLayout.SOUTH);
    panel18.add(btnGenerate, BorderLayout.NORTH);
    panel7.add(panel19, BorderLayout.SOUTH);
    panel19.add(label19, null);
    panel19.add(txtCount, null);
    panel1.add(label5, BorderLayout.NORTH);
    panel16.add(panel17, BorderLayout.NORTH);
    panel17.add(btnExample1, null);
    panel17.add(btnExample2, null);
    panel17.add(btnExample3, null);
    panel17.add(btnExample4, null);
    panel17.add(btnExample5, null);
    panel17.add(btnExample6, null);

    setBackground( SystemColor.control );
   }
   catch(Exception e)
   {
      e.printStackTrace();
   }
  }

  void btnExample1_actionPerformed(ActionEvent e)
  {
    txtInput.setText("Hello World");
    txtFormatString.setText("%s %s");
    txtAS0.setText("");
    txtAS1.setText("");
    txtAS2.setText("");
    txtAS3.setText("");
    txtAS4.setText("");
    txtAS5.setText("");
    txtCount.setText("");
  }

  void btnExample2_actionPerformed(ActionEvent e)
  {
    txtInput.setText("ff\nff");
    txtFormatString.setText("%s %x");
    txtAS0.setText("");
    txtAS1.setText("");
    txtAS2.setText("");
    txtAS3.setText("");
    txtAS4.setText("");
    txtAS5.setText("");
    txtCount.setText("");
  }

  void btnExample3_actionPerformed(ActionEvent e)
  {
    txtInput.setText("a b c");
    txtFormatString.setText("%2$s %1$s %0$s");
    txtAS0.setText("");
    txtAS1.setText("");
    txtAS2.setText("");
    txtAS3.setText("");
    txtAS4.setText("");
    txtAS5.setText("");
    txtCount.setText("");
  }

  void btnExample4_actionPerformed(ActionEvent e)
  {
    txtInput.setText("10\n10\n10");
    txtFormatString.setText("%d %x %o");
    txtAS0.setText("");
    txtAS1.setText("");
    txtAS2.setText("");
    txtAS3.setText("");
    txtAS4.setText("");
    txtAS5.setText("");
    txtCount.setText("");
  }

  void btnExample5_actionPerformed(ActionEvent e)
  {
    txtInput.setText("10\n0xf\n010\n123456789123456789123456789123456789123456789" +
                     "\n123456789123456789123456789123456789123456789");
    txtFormatString.setText("%i %i %i %ld %lx");
    txtAS0.setText("");
    txtAS1.setText("");
    txtAS2.setText("");
    txtAS3.setText("");
    txtAS4.setText("");
    txtAS5.setText("");
    txtCount.setText("");
  }

  void btnExample6_actionPerformed(ActionEvent e)
  {
    txtInput.setText("abcdefghijklmnopqrstuvwxyz");
    txtFormatString.setText("%[a-d] %[a-h] %[^m] %s");
    txtAS0.setText("");
    txtAS1.setText("");
    txtAS2.setText("");
    txtAS3.setText("");
    txtAS4.setText("");
    txtAS5.setText("");
    txtCount.setText("");
  }

  void btnGenerate_actionPerformed(ActionEvent e)
  {
    try
    {
        String sInput = txtInput.getText();
        String sFormat = txtFormatString.getText();
        String[] as = new String[6];

//        sFormat = ToolkitString.strUnescape_Java( sFormat );

        as[0] = "";
        as[1] = "";
        as[2] = "";
        as[3] = "";
        as[4] = "";
        as[5] = "";

//        int count = ToolkitString.sscanf(sInput, sFormat, as);

        txtAS0.setText( as[0] );
        txtAS1.setText( as[1] );
        txtAS2.setText( as[2] );
        txtAS3.setText( as[3] );
        txtAS4.setText( as[4] );
        txtAS5.setText( as[5] );

//        txtCount.setText( Integer.toString(count) );
    }
    catch( Throwable t )
    {
       txtAS0.setText( t.getClass().getName() + "; " + t.getMessage() );
       txtAS1.setText("");
       txtAS2.setText("");
       txtAS3.setText("");
       txtAS4.setText("");
       txtAS5.setText("");
       txtCount.setText("");
    }
  }
}


