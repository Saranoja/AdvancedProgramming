<html>
<head>
  <title> Gomoku </title>
</head>
<body>
  <h1> Game flow: </h1>
  <#list allMoves as move>
    <p> Player ${move.playerIndex} move: column ${move.col}, row ${move.row} </p>
  </#list>
  <p> Done. <#if player??> ${player.mark} won <#else> Game interrupted </#if>  </p>
</body>
<footer>
  <p> Author: Calin Irina, E2 <p>
<footer>
</html>