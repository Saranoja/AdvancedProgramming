/**
 * @author: Calin Irina, I2E2
 */

package jpa.repo;

import jpa.entity.Artist;

import java.util.List;

public class ArtistRepository extends AbstractRepository<Artist> {

    public ArtistRepository() {
        super();
    }

    public static Artist findById(long id) {
        Artist artist = entityManager.find(Artist.class, id);
        return artist;
    }

    public static List<Artist> findByName(String name) {
        List<Artist> artists = (List<Artist>) entityManager.createNamedQuery("artist.findByName").setParameter("inputname", name).getResultList();
        return artists;
    }

    public static List<Artist> getAllArtists() {
        return (List<Artist>) entityManager.createNamedQuery("artist.getAllArtists").getResultList();
    }

}