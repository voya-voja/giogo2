<html>

<head>
	<meta http-equiv="Content-Language" content="en-us">
	<meta name="author" content="giogo.com">
    <meta name="keywords" content="game, games, online games, play games, free games, online gaming.">
    <meta name="description" content="Play free online multiplayers' GAMES and chat with FRIENDS. Great games like Preference and more.">
    <meta name="GENERATOR" content="Microsoft FrontPage 5.0">
    <meta name="ProgId" content="FrontPage.Editor.Document">

	<title>Gamexy Games - Play free online GAMES, win big PRIZES, and chat with FRIENDS.</title>
	<meta name="googlebot" content="index/follow" />
	<meta name="robots" content="index/follow" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="Content-type" content="text/html; charset=utf-8" /> 	
	
		



    <style fprolloverstyle>A:hover {color: #FF0000; font-weight: bold}</style>
	<title>Preference</title>

<%@ page import = "javax.servlet.ServletContext"%>
<%@ page import = "com.giogo.game.GameViewController"%>

<% 
        GameViewController controller = new GameViewController(request);
%>

</head>

<body>
<table border="0" width="100%">
  <tr>
	<td width="20%">&nbsp;<a target="_blank" href="http://www.gamaxy.com/"><img border="0" src="http://www.gamaxy.com/images/gamaxy_grey.gif" width="100" height="27"></a></td>
    <td width="60%" background="http://www.gamaxy.com/images/globe.gif">
      <p align="center"><b><font face="Comic Sans MS" size="6">Preference</font></b></p>
    </td>
  
    <td width="20%">
      <p align="right"><a target="_blank" href="http://www.gamaxy.com/prefguide.html"><b><font face="Comic Sans MS">Preference Guide</font></b></a></p>
    </td>
  </tr>
</table>

<div align="center">
  <center>

<table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse" id="AutoNumber1" width="100%" >
  <tr>
    <td width="234" align="center" height="103" valign="top">
        <table>
        	<tr>
        		<td>
                            <p>
                                &nbsp;
                                <a shape="rect" coords="10, 10, 110, 710" 
                             target="tricks<%out.print(controller.session());%>" 
                href="tricks.jsp?session=<%out.print(controller.session());%>">
                                     Tricks
                                </a>
                            </p>
                            <p>
                                &nbsp;
                                <a coords="10, 10, 110, 710" 
                               target="talk<%out.print(controller.session());%>" 
                   href="talk.jsp?session=<%out.print(controller.session());%>">
                                     Talk
                                </a>
                            </p>
                        </td>
		</tr>
		</table>
     </td>
    <td width="663" >

<table width="662" >
  <tr>
    <td width="135" align="center" height="124">
        <APPLET codebase="./"
                code="com/giogo/game/ScoreTableApplet.class" width=135 height=96>
            <PARAM NAME = "name" VALUE ="player.left">
            <PARAM NAME = "session.id" VALUE ="<%out.print(controller.session());%>">
        </APPLET></td>
    <td width="6" align="center" height="124">
        &nbsp;</td>
    <td width="156" align="center" valign="bottom" height="124" style="border-left-style: double; border-left-width: 3; border-right-style: double; border-right-width: 3; border-top-style: double; border-top-width: 3">
        <table>
        	<tr>
        		<td>
        <APPLET  codebase="./"
                 code="com/giogo/game/card/preference/PreferenceCardApplet.class" width=58 height=78>
            <PARAM NAME = "name" VALUE ="card.throw.left">
            <PARAM NAME = "session.id" VALUE ="<%out.print(controller.session());%>">
        </APPLET>
        		</td>
        		<td>&nbsp;</td>
        		<td>&nbsp;</td>
        		<td>
        <APPLET  codebase="./"
                 code="com/giogo/game/card/preference/PreferenceCardApplet.class" width=58 height=78>
            <PARAM NAME = "name" VALUE ="card.throw.right">
            <PARAM NAME = "session.id" VALUE ="<%out.print(controller.session());%>">
        </APPLET>
        		</td>
        	</tr>
        </table>
    </td>
    <td width="6" align="center" height="124">
    </td>
    <td width="331" align="left" height="124">
        <APPLET codebase="./"
                code="com/giogo/game/ScoreTableApplet.class" width=135 height=96>
            <PARAM NAME = "name" VALUE ="player.right">
            <PARAM NAME = "session.id" VALUE ="<%out.print(controller.session());%>">
        </APPLET></td>
    <td width="6" align="left" height="124">
        &nbsp;</td>
  </tr>

  <tr>
    <td width="135" align="center" height="9">
           </td>
    <td width="6" align="center" height="9">
           </td>
    <td width="156" align="center" height="9" style="border-left-style: double; border-left-width: 3; border-right-style: double; border-right-width: 3">
        </td>
    <td width="6" align="center" height="9">
        </td>
    <td width="331" align="center" height="9">
    </td>
    <td width="6" align="center" height="9">
    </td>
  </tr>

  <tr>
    <td width="135" align="center" height="96">
    	<b>Widows </b>
    <table>
    	<tr>
    		<td>
        <APPLET  codebase="./"
                 code="com/giogo/game/card/preference/PreferenceCardApplet.class" width=58 height=78>
            <PARAM NAME = "name" VALUE ="card.widow.0">
        	<PARAM NAME = "session.id" VALUE ="<%out.print(controller.session());%>">
        </APPLET>
        </td>
        <td>
        <APPLET  codebase="./"
                 code="com/giogo/game/card/preference/PreferenceCardApplet.class" width=58 height=78>
            <PARAM NAME = "name" VALUE ="card.widow.1">
        	<PARAM NAME = "session.id" VALUE ="<%out.print(controller.session());%>">
        </APPLET>
        </td>
        </tr>
       </table>
    </td>
    <td width="6" align="center" height="96">
           &nbsp;</td>
    <td width="156" align="center" height="96" valign="baseline" style="border-left-style: double; border-left-width: 3; border-right-style: double; border-right-width: 3; border-bottom-style: double; border-bottom-width: 3">
        <APPLET  codebase="./"
                 code="com/giogo/game/card/preference/PreferenceCardApplet.class" width=58 height=78>
            <PARAM NAME = "name" VALUE ="card.throw">
            <PARAM NAME = "displayOnly" VALUE ="true">
            <PARAM NAME = "faceUp" VALUE ="TRUE">
            <PARAM NAME = "session.id" VALUE ="<%out.print(controller.session());%>">
        </APPLET></td>
    <td width="6" align="center" height="96">
        &nbsp;</td>
    <td width="331" align="center" height="96">
		<APPLET codebase="./"
                 code="com/giogo/game/PlayerLoginApplet.class" width=299 height=96>
            <PARAM NAME = "name" VALUE ="login">
            <PARAM NAME = "alias" VALUE ='<%out.print(controller.alias());%>'>
            <PARAM NAME = "gameId" VALUE ="<%out.print(controller.gameId());%>">
            <PARAM NAME = "host" VALUE ="<%out.print(controller.host());%>">
            <PARAM NAME = "port" VALUE ="<%out.print(controller.port());%>">
            <PARAM NAME = "session.id" VALUE ="<%out.print(controller.session());%>">
        </APPLET></td>
    <td width="6" align="center" height="96">
		&nbsp;</td>
  </tr>

  <tr>
    	<td width="156" align="center">
        </td>
    <td width="6" align="center">
        </td>
    <td width="331" align="center">
    </td>
    <td width="6" align="center">
    </td>
  </tr>

  <tr>
    <td width="135" align="left" valign="bottom" height="103">
    	&nbsp;<td width="6" align="left" valign="bottom" height="103">
    	&nbsp;<td width="156" align="center" height="103">
        <APPLET  codebase="./"
                 code="com/giogo/game/ScoreTableApplet.class" width=135 height=96>
            <PARAM NAME = "name" VALUE ="player">
            <PARAM NAME = "session.id" VALUE ="<%out.print(controller.session());%>">
        </APPLET></td>
    <td width="6" align="left" height="103">
        &nbsp;</td>
    <td width="331" align="left" height="103">
    &nbsp;
		<b>Hand</b>
 <table width="300" id="AutoNumber6">
  <tr>
    <td width="20%" align="center">
        <APPLET  codebase="./"
                 code="com/giogo/game/card/preference/PreferenceCardApplet.class" width=58 height=78>
            <PARAM NAME = "name" VALUE ="card.0">
            <PARAM NAME = "session.id" VALUE ="<%out.print(controller.session());%>">
        </APPLET>
    </td>
    <td width="20%" align="center">
        <APPLET  codebase="./"
                 code="com/giogo/game/card/preference/PreferenceCardApplet.class" width=58 height=78>
            <PARAM NAME = "name" VALUE ="card.1">
            <PARAM NAME = "session.id" VALUE ="<%out.print(controller.session());%>">
        </APPLET>
    </td>
    <td width="20%" align="center">
        <APPLET  codebase="./"
                 code="com/giogo/game/card/preference/PreferenceCardApplet.class" width=58 height=78>
            <PARAM NAME = "name" VALUE ="card.2">
            <PARAM NAME = "session.id" VALUE ="<%out.print(controller.session());%>">
        </APPLET>
    </td>
    <td width="20%" align="center">
        <APPLET  codebase="./"
                 code="com/giogo/game/card/preference/PreferenceCardApplet.class" width=58 height=78>
            <PARAM NAME = "name" VALUE ="card.3">
            <PARAM NAME = "session.id" VALUE ="<%out.print(controller.session());%>">
        </APPLET>
    </td>
    <td width="20%" align="center">
        <APPLET  codebase="./"
                 code="com/giogo/game/card/preference/PreferenceCardApplet.class" width=58 height=78>
            <PARAM NAME = "name" VALUE ="card.4">
            <PARAM NAME = "session.id" VALUE ="<%out.print(controller.session());%>">
        </APPLET>
    </td>
  </tr>
</table>
   </td>
    <td width="6" align="center" height="103">
    &nbsp;</td>
  </tr>

  <tr>
    <td width="135" align="center" height="103">
        <APPLET  codebase="./"
                 code="com/giogo/game/card/preference/PreferenceBiddingApplet.class" width=105 height=120>
            <PARAM NAME = "name" VALUE ="bidboard">
            <PARAM NAME = "session.id" VALUE ="<%out.print(controller.session());%>">
        </APPLET></td>
    <td width="6" align="center" height="103">
        </td>
    <td width="156" align="center" height="103">
    	&nbsp;</td>
    <td width="6" align="center" height="103">
        &nbsp;</td>
    <td width="331" align="left" valign="top" height="103">
        <table  id="AutoNumber6">
  <tr>
    <td width="20%" align="center">
    	<APPLET  codebase="./"
               code="com/giogo/game/card/preference/PreferenceCardApplet.class" width=58 height=78>
            <PARAM NAME = "name" VALUE ="card.5">
            <PARAM NAME = "session.id" VALUE ="<%out.print(controller.session());%>">
        </APPLET>
    </td>
    <td width="20%" align="center">
        <APPLET  codebase="./"
                 code="com/giogo/game/card/preference/PreferenceCardApplet.class" width=58 height=78>
            <PARAM NAME = "name" VALUE ="card.6">
            <PARAM NAME = "session.id" VALUE ="<%out.print(controller.session());%>">
        </APPLET>
    </td>
    <td width="20%" align="center">
        <APPLET  codebase="./"
                 code="com/giogo/game/card/preference/PreferenceCardApplet.class" width=58 height=78>
            <PARAM NAME = "name" VALUE ="card.7">
            <PARAM NAME = "session.id" VALUE ="<%out.print(controller.session());%>">
        </APPLET>
    </td>
    <td width="20%" align="center">
        <APPLET  codebase="./"
                 code="com/giogo/game/card/preference/PreferenceCardApplet.class" width=58 height=78>
            <PARAM NAME = "name" VALUE ="card.8">
            <PARAM NAME = "session.id" VALUE ="<%out.print(controller.session());%>">
        </APPLET>
                            </td>
                            <td width="20%" align="center">
        <APPLET  codebase="./"
                 code="com/giogo/game/card/preference/PreferenceCardApplet.class" width=58 height=78>
            <PARAM NAME = "name" VALUE ="card.9">
            <PARAM NAME = "session.id" VALUE ="<%out.print(controller.session());%>">
        </APPLET>
                            </td>
                        </tr>
                    </table>
                </td>
                <td width="6" align="center" height="103">
                    &nbsp;
                </td>
            </tr>
        </table>
    </td>
  </tr>
</table>


  </center>
</div>

<table border="1" width="100%">
  <tr>
    <td width="10%">
      <p align="left">&nbsp;<a target="_blank" href="http://www.gamaxy.com/"><img border="0" src="http://www.gamaxy.com/images/gamaxy.jpg" width="100" height="27"></a></p>
    </td>
    <td width="100%" background="http://www.gamaxy.com/images/globe.gif">
        <p align="center">Powered by <a target="_blank" href="http://giogo.gotdns.com/giogo">GIOGO</a></p>    
    </td>
    <td width="10%" background="http://www.gamaxy.com/images/globe.gif">
      <p align="left">&nbsp;<font face="Comic Sans MS"><a href="mailto:webmaster@inexum.com?subject=Gamaxy feedback">Feedback</a></font></p>
    </td>
  </tr>
</table>

</body>

</html>
