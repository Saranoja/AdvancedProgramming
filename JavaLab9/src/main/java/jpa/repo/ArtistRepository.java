/**
 * @author: Calin Irina, I2E2
 */

package jpa.repo;

import jpa.entity.Artist;

import javax.persistence.EntityManager;
import java.util.List;

public class ArtistRepository extends AbstractRepository<Artist> {

    public ArtistRepository() {
        super();
    }

    public static Artist findById(EntityManager entityManager, long id){
        Artist artist =entityManager.find(Artist.class, id);
        return artist;
    }

    public static List<Artist> findByName(EntityManager entityManager, String name){
        List<Artist> artists = (List<Artist>) entityManager.createNamedQuery("artist.findByName").setParameter("inputname",name).getResultList();
        return artists;
    }

}