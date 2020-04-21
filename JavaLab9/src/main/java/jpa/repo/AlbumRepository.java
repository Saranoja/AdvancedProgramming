/**
 * @author: Calin Irina, I2E2
 */

package jpa.repo;

import jpa.entity.Album;

import javax.persistence.EntityManager;
import java.util.List;

public class AlbumRepository extends AbstractRepository<Album> {

    public AlbumRepository() {
        super();
    }

    public static Album findById(long id){
        Album album = entityManager.find(Album.class, id);
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
