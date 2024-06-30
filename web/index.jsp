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

	<title>List of Games</title>

</head> 


<body bgproperties="fixed">
<%@page contentType="text/html"%>

<%@ page import = "com.giogo.GamesViewController"%>
<%@ page import = "com.giogo.GameInfo"%>

<!------Play online games for free or play to win money!  We have online casino games, multiplayer games, online scrabble, online chess, mah jong, solitaire, checkers, backgammon, blocktionary, wordscape, dominoes, hearts, euchre, reversi, klondike, pyramid and many other games.  Click above to start playing now!------->
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

<table border="1" width="100%" summary="online games, multiplayer games, fun games, online game, web games, free games, prizes, free play, download games, play now, play games" style="font-family: Book Antiqua; font-size: 10pt; font-weight: bold">
	<tr>
		<td width="174">
              <font face="Verdana">
              <a href="newGame.jsp?type=Preference">New Game</a>
              &nbsp;
              </font>
		</td>
		<td width="955">
			<table width="100%">
				<%
				    try
				    {
				        String gameName = "Preference";
			       		GamesViewController controler = new GamesViewController();
				        GameInfo[] games = controler.getListOfGames(gameName);
				        for(int gCount = 0; gCount < games.length; gCount++)
				        {
                                            if((gCount % 3) == 0)
                                                out.println("<tr>");
				            out.println("<td width='33%'><a href=\"alias.jsp?game=" + games[gCount].id() + "\" + class=\"gamesproxy\">");
				       	     if("play".equals(games[gCount].status()))
				                out.println("<font face='Verdana' color='#008C00' size='2'>");
				            else if("wait".equals(games[gCount].status()))
			       		         out.println("<font face='Verdana' color='#8C0000' size='2'>");
			       		    else
			       		         out.println("<font face='Verdana' size='2'>");
			       		    
				            out.println(games[gCount].name() + " " + "</font></a> ");
				            out.println("<font size='1'> (" + games[gCount].playersNo() + " players)</font></td>");
                                            if((gCount % 3) == 2)
                                                out.println("</tr>");
				        }
				    }
				    catch(Exception e)
				    {
				        out.println(e.getLocalizedMessage());
				    }
				%>
			</table>
		</td>
	</tr>
	

</table>

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



</BODY>
</HTML>

