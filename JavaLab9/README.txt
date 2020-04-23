Persistence

*Update for the bonus part:
Added support for MusicGenres after creating a specific table and importing the entity + creating the repo
create table MusicGenres(album_id number, genre varchar2(50), PRIMARY KEY (album_id), FOREIGN KEY (album_id) REFERENCES albums(id));
Generated more albums using JavaFaker and populated MusicGenres, adding a genre for each album (see BonusManager/insertAlbums &
BonusManager/insertGenres)
For the largest set of albums such that no two albums have the same artist or belong to the same genre:
Implemented a greedy algorithm that finds a matching in a bipartite graph (well, kinda, because I haven't used any graph, only
the reference from https://en.wikipedia.org/wiki/3-dimensional_matching and adapted it for 2 dimensions)
The algorithm is pretty self-explainatory, it takes the first element from the albums list and, one by one, checks for collisions
in the list.
If there exists any collision, the current album is removed from the list. We do that until no album is left.
Created test units for the algorithm using JUnit (+ added all the dependencies) -  see /src/test/java/AlgorithmTest and UnitTesting.PNG
I've written 2 tests - one that checks the expected size of the largest set and another one that checks whether a certain album
that we know for a fact that doesn't collide with anything exists in our set - both tests passed


*Update for the optional part:
Added the Chart Entity + Chart Repository to the jpa package so it now supports charts + reorganised the hierarchy
Created the AbstractRepository abstract class with generics, which is now extended by the other classes in repo
Imported the code from Lab8 for the JDBC implementation and created a factory package which holds factories for each type
of repository (+ a RepoAbstractFactory interface). 
Depending on some string given as input in input.txt (either "jpa" or "jdbc"), a requested repository is created.
According to this, now the Controllers from jdbc extend AbstractRepository so that such an object can be returned and used.
I've done this for all the entities.
Note: I have tested with both types of input and it works fine. I have also attached screenshots taken after running.


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
