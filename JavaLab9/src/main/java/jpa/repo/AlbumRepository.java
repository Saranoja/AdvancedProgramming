/**
 * @author: Calin Irina, I2E2
 */

package jpa.repo;

import jpa.entity.Album;

import java.util.List;

public class AlbumRepository extends AbstractRepository<Album> {

    public AlbumRepository() {
        super();
    }

    public static Album findById(long id) {
        Album album = entityManager.find(Album.class, id);
        return album;
    }

    public static List<Album> findByName(String name) {
        List<Album> albums = (List<Album>) entityManager.createNamedQuery("album.findByName").setParameter("inputname", name).getResultList();
        return albums;
    }

    public static List<Album> findByArtist(long artistID) {
        List<Album> albums = (List<Album>) entityManager.createNamedQuery("album.findByArtist").setParameter("inputId", artistID).getResultList();
        return albums;
    }

    public static List<Album> getAllAlbums() {
        return (List<Album>) entityManager.createNamedQuery("album.getAllAlbums").getResultList();
    }

    public static String getGenre(long id) {
        return (String) entityManager.createNamedQuery("album.getGenre").setParameter("inputId", id).getSingleResult();
    }

    public static String getArtist(long id) {
        return (String) entityManager.createNamedQuery("album.getArtist").setParameter("inputId", id).getSingleResult();
    }

}
