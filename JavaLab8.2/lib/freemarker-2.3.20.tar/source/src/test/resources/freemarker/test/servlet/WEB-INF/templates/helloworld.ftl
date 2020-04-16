<html>
<head>
  <title> Catalog </title>
</head>
<body>
  <h1> Content: </h1>
  <ul>
  	<#list documents as dp>
  		<p> ID: ${dp.id} </p>
  		<p> Tag: ${dp.tags} </p>
  	</#list>
  </ul>
</body>
<footer>
  <p> Author: Calin Irina, E2 <p>
<footer>
</html>