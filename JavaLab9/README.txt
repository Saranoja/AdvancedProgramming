Persistence

Super little code I've written exactly as described in the lab.
*For the configuration part I've created a Maven project, where I added the specific hibernate dependencies 
in pom.xml. I've also created META-INF, where I added hibernate.cfg.xml with the connection driver.
After connecting to the Oracle database, I've generated the persistence mapping from the db schema, which
created Artist and Album entities.
PersistenceUtil class: singleton, its constructor creates the EntityManagerFactory
AlbumRepository & ArtistRepository: contain the required methods create, findById, findByName + findByArtist
(which refer to the NamedQueries from the entities)
AlbumManager class: just to test the functionality. It also creates the EntityManager which is given as
argument for all the methods in the repos
Note: Output.PNG shows an output example after calling findById, findByName, findByArtist
