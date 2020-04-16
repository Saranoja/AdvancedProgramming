<html>
<head>
  <title> Chart </title>
</head>
<body>
  <h1> Artists chart: </h1>
  <#list artist as artist>
    <p> Artist name: ${artist.name}, Popularity: ${artist.popularity} </p>
  </#list>
</body>
<footer>
  <p> Author: Calin Irina, E2 <p>
<footer>
</html>