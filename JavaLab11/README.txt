REST Services

*Update for the Bonus part:
I have generated the API Documentation using Swagger (see SwaggerConfig)
Added dependencies for Spring Security & created the necessary classes to secure the services (/security)


*Update for the Optional part:
Created services for different operations on games (and players) i.e. inserting and reading
using the support from Spring Boot
Created custom exceptions handled by a RestControllerAdvice (the CustomError was only
used for printing the stack trace)
Generated a self-signed certificate & secured the communication using https through Keytool
Created the SecurityConfig class to require https requests


Created a Spring Boot project using Initializr (added rest & jdbc dependencies)
+ created 2 more tables in the database: Player(id, name) and Game(id, player1_id, player2_id)
+ created a PlayerController class - which manages the requests by calling different
methods from the PlayersRepo
Note: Tests are included in the attached images