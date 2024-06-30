<%@ page import = "com.giogo.GameLoginViewController"%>

<html>

<head>
	<meta name="author" content="giogo.com">
    <meta name="keywords" content="game, games, online games, play games, free games, online gaming.">
    <meta name="description" content="Play free online multiplayers' GAMES and chat with FRIENDS. Great games like Preference and more.">
    <meta name="GENERATOR" content="Microsoft FrontPage 4.0">
    <meta name="ProgId" content="FrontPage.Editor.Document">

	<title>Gamexy Games - Play free online GAMES, win big PRIZES, and chat with FRIENDS.</title>
	<meta name="googlebot" content="index/follow" />
	<meta name="robots" content="index/follow" />
	<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
	<meta http-equiv="Content-type" content="text/html; charset=utf-8" /> 	
	
		



    <style fprolloverstyle>A:hover {color: #FF0000; font-weight: bold}
    </style>
<title>Gamaxy  Game Login</title>


<% 
        GameLoginViewController controller = new GameLoginViewController(request);
%>

<SCRIPT LANGUAGE="JavaScript">
	function getPath()
	{
            if(<% out.print(controller.isLoggedOn()); %>)
            {
                window.location = 'alias.jsp?game=' 
                    + '<% out.print(controller.gameId()); %>' 
                    + '&message=Alias \'<% out.print(controller.alias()); %>\''
                                + ' has been used!'
            }
            else
            {
                window.location = 'game.jsp?game=' 
                    + '<% out.print(controller.gameId()); %>' 
                    + '&alias=' + '<% out.print(controller.alias()); %>';
            }
	}
    </SCRIPT>

</head>

<body onload='getPath()'>
<table border="0" width="100%">
  <tr>
	<td width="20%">&nbsp;<a href="http://www.gamaxy.com/"><img border="0" src="http://www.gamaxy.com/images/gamaxy_grey.gif" width="100" height="27"></a></td>
    <td width="60%" background="http://www.gamaxy.com/images/globe.gif">
      <p align="center"><b><font face="Comic Sans MS" size="6">Preference</font></b></p>
    </td>
  
    <td width="20%">
      <p align="right"><a href="http://www.gamaxy.com/prefguide.html"><b><font face="Comic Sans MS">Preference Guide</font></b></a></p>
    </td>
  </tr>
</table>

<div align="center">
  <center>


  <p align="center" style="word-spacing: 4"><em><strong><font face="Verdana" size="3" color="#0000FF">Login in progress!
  </font></strong></em> 
    </p>

  </center>
</div>


<table border="1" width="100%">
  <tr>
    <td width="10%">
      <p align="left">&nbsp;<a href="http://www.gamaxy.com/"><img border="0" src="http://www.gamaxy.com/images/gamaxy.jpg" width="100" height="27"></a></p>
    </td>
    <td width="100%" background="http://www.gamaxy.com/images/globe.gif">
        <p align="center">Powered by <a href="http://giogo.gotdns.com/giogo">GIOGO</a></p>    
    </td>
    <td width="10%" background="http://www.gamaxy.com/images/globe.gif">
      <p align="left">&nbsp;<font face="Comic Sans MS"><a href="mailto:webmaster@inexum.com?subject=Gamaxy feedback">Feedback</a></font></p>
    </td>
  </tr>
</table>

</body>

</html>
