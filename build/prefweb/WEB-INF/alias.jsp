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
<title>Gamaxy Player Alias</title>

<%@ page import = "com.giogo.AliasViewController"%>
<%
    AliasViewController controller = new AliasViewController(request);
%>

<SCRIPT LANGUAGE="JavaScript">
	function isAliasOK()
	{
		if(!isNaN(document.formAlias.alias.value) && document.formAlias.alias.value == 0)
		{
			alert("The alias cannot be null!");
			return false;
		}
		var aliases = <% out.print(controller.getAliasesString()); %>
		for(id in aliases)
		{
			if(document.formAlias.alias.value == aliases[id])
			{
				alert("The alias '" + document.formAlias.alias.value + "' has been used!");
				return false;
			}
		}
                return true;
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
  <p align="center" style="word-spacing: 4">
    <em><strong><font face="Verdana" size="3" color="#FF0000">
        <% 
            String message = request.getParameter("message");
            if(message != null)
                out.print(message);
        %>
    </font></strong></em> 
  <center>
    <form method="GET" name="formAlias" action='gameLogin.jsp'>
    <input type=hidden name=game value='<%out.print( request.getParameter("game")); %>' >
      <p align="center">Alias: <input type="text" name="alias" size="20"> 
<script>
document.formAlias.alias.focus();
</script>

            <input type="submit" value="Submit" name="submit"> 
            <input type="reset" value="Reset" name="reset"> 
        </p>
    </form>
  </center>

  <table width="100%">
    <tr>
    </tr>
    <%
        String[] players = controller.getPlayers();
        int playersLength = 0;
        if(players != null)
            playersLength = players.length;
        String[] spectators = controller.getSpectators();
        int spectatorsLength = 0;
        if(spectators != null)
            spectatorsLength = spectators.length;
    %>
    <tr>
        <td width="10%"><font color='#0000ff' face="Comic Sans MS">&nbsp;</font></td>
        <td width="45%"><p align="center"><font color='#0000ff' face="Comic Sans MS">&nbsp;Game Players</font> (<% out.print(playersLength); %>)</td>
        <td ><p align="center"><font color='#0000ff' face="Comic Sans MS">&nbsp;Game Spectators</font> (<% out.print(spectatorsLength); %>)</td>
    </tr>
    <%
        for(int count = 0; count < playersLength || count < spectatorsLength; count++)
        {
            out.println("<tr>");
            out.println("<td width='10%'>&nbsp;</td>");
            out.println("<td width='45%'><p align='center'>&nbsp;");
            if(count < playersLength)
                out.println(players[count]);
            out.println("</td>");

            out.println("<td><p align='center'>&nbsp;");
            if(count < spectatorsLength && spectators[count] != null)
                out.println(spectators[count]);
            out.println("</td>");
        }
    %>
  </table>
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
