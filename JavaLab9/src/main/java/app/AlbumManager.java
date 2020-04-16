/**
 * @author: Calin Irina, I2E2
 */

package app;

import entity.Album;
import entity.Artist;
import repo.AlbumRepository;
import repo.ArtistRepository;
import util.PersistenceUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class AlbumManager {
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

        Artist ar = ArtistRepository.findById(entityManager, (long) 23);
        System.out.println(ar.getName());

        List<Album> enrique_iglesias = AlbumRepository.findByName(entityManager, "Euphoria");
        System.out.println(enrique_iglesias);

        List<Album> albums = AlbumRepository.findByArtist(entityManager, (long) 23);
        System.out.println(albums);

        entityManager.close();
        factory.close();
    }
}