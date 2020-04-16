/**
 * @author: Calin Irina, I2E2
 */

package repo;

import entity.Album;

import javax.persistence.EntityManager;
import java.util.List;

public class AlbumRepository {
    public static void create(EntityManager entityManager, Album album){
        entityManager.getTransaction().begin();
        entityManager.persist(album);
        entityManager.getTransaction().commit();
    }

    public static Album findById(EntityManager entityManager, long id){
        Album album =entityManager.find(Album.class, id);
        return album;
    }

    public static List<Album> findByName(EntityManager entityManager, String name){
        List<Album> albums = (List<Album>) entityManager.createNamedQuery("album.findByName").setParameter("inputname",name).getResultList();
        return albums;
    }

    public static List<Album> findByArtist(EntityManager entityManager, long artistID) {
        List<Album> albums = (List<Album>) entityManager.createNamedQuery("album.findByArtist").setParameter("inputId",artistID).getResultList();
        return albums;
    }
}
