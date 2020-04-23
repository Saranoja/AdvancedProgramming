JDBC app:
allows a user to connect to a relational database by using JDBC, submit SQL statements and get the result
Database class: written with respect to the singleton design pattern, in order to allow only one connection 
to the database. The connection is made inside the constructor, while methods setResultSet and doUpdate
will execute queries and, formerly, insert statements
ArtistController DAO class: create method builds the statement for an insertion, while findByName will display
the id for the requested artist
AlbumController DAO class: same as ArtistController, except findByArtist will display the album for a requested
artist
Main class: just to test the functionality