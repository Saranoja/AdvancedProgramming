/**
 * @author: Calin Irina, I2E2
 */

//getType() will read from input.txt and return the type of desired implementation, either jpa or jdbc
//depending on this type, AlbumRepositoryFactory will create one object - either using the jpa entities or the jdbc classes
//the controllers have been modified to extend AbstractRepository so no matter which way is chosen, methods work the same

package app;

import db.Database;
import factory.AlbumRepositoryFactory;
import jpa.entity.Album;
import jpa.repo.AbstractRepository;
import jpa.util.PersistenceUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.*;

public class AlbumManager {
    private static db.Database db;

    public static void main(String[] args) {
        EntityManagerFactory factory = PersistenceUtil.getInstance();
        EntityManager entityManager = factory.createEntityManager();

        //tested adding a new Artist / Album to the Database

        /* Artists artistsEntity = new Artists();
        artistsEntity.setId(23);
        artistsEntity.setCountry("Spain");
        artistsEntity.setName("Enrique Iglesias");
        ArtistRepository.create(entityManager,artistsEntity); */

        /* Albums albumsEntity = new Albums();
        albumsEntity.setName("Euphoria");
        albumsEntity.setReleaseYear((long)2010);
        albumsEntity.setId((long)266);
        albumsEntity.setArtistId(23);
        AlbumRepository.create(entityManager,albumsEntity); */

        /* Artist ar = ArtistRepository.findById(entityManager, (long) 23);
        System.out.println(ar.getName());

        List<Album> enrique_iglesias = AlbumRepository.findByName(entityManager, "Euphoria");
        System.out.println(enrique_iglesias);

        List<Album> albums = AlbumRepository.findByArtist(entityManager, (long) 23);
        System.out.println(albums); */

        String param = getType();
        init(param);

        //will create an entity and insert it into the database using jdbc implementation
        AlbumRepositoryFactory albumRepositoryFactory = new AlbumRepositoryFactory();
        AbstractRepository<Album> albumRepository = albumRepositoryFactory.createRepository(param);
        Album example = new Album();
        example.setArtistId(23);
        example.setId(889);
        example.setName("Banana club");
        example.setReleaseYear((long) 2011);
        albumRepository.create(example);

        entityManager.close();
        factory.close();
    }

    private static String getType() {
        String type = "";
        try {
            FileReader fr = new FileReader("input.txt");
            BufferedReader br = new BufferedReader(fr);
            type = br.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return type;
    }

    private static void init(String type) {
        if (type.equals("jdbc"))
            db = Database.getInstance();
    }

    public static Database getDb() {
        return db;
    }
}