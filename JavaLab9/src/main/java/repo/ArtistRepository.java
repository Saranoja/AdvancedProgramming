/**
 * @author: Calin Irina, I2E2
 */

package repo;

import entity.Artist;

import javax.persistence.EntityManager;
import java.util.List;

public class ArtistRepository {

    public static void create(EntityManager entityManager, Artist artist){
        entityManager.getTransaction().begin();
        entityManager.persist(artist);
        entityManager.getTransaction().commit();
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