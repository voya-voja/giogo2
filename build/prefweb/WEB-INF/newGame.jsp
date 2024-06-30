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
<title>Gamaxy New Game</title>


    <SCRIPT LANGUAGE="JavaScript">
	function getPath()
	{
		window.location = 'startGame.jsp?type=' + '<% out.print( request.getParameter("type") ); %>' + '&alias=' + document.formStarting.alias.value
				 + '&name=' + document.formStarting.gameName.value + '&points=' + document.formStarting.points.value
				 + '&refe=' + document.formStarting.refe.value;
	}
    </SCRIPT>

</head>

<body>

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

<form method="GET"  name="formStarting" action="startGame.jsp">
<input type="hidden" name="type" value='<% out.print(request.getParameter("type")); %>'>
    <div align="center">
      <center>
	<table>
	<tr>
		<td align="right">Alias: </td>
		<td><input type="text" name="alias" size="20">  </td>
                <script>
                    document.formStarting.alias.focus();
                </script>
	</tr>
	<tr>
		<td align="right">Game Name: </td>
		<td><input type="text" name="name" size="20">  </td>
	</tr>
    <tr>
		<td align="right"># Points/player: </td>
		<td><input type="text" name="points" size="20" value="60">  </td>
    </tr>
    <tr>
		<td align="right">Maximum # Refes: </td>
		<td><input type="text" name="refe" size="20" value="2">  </td>
    </tr>
	<tr>
	<td align="right">&nbsp;</td>
	</tr>
	<tr>
		<td align="right">&nbsp;</td>
		  <td align="center"> 
	       	 <input type="submit" value="Submit" name="B1"> 
       		 <input type="reset" value="Reset" name="B2">  
	   	 </td>
	  </tr>
	  </table>
      </center>
    </div>
</form>


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
