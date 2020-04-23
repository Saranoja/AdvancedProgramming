/**
 * @author: Calin Irina, I2E2
 */

package jpa.repo;

import jpa.entity.MusicGenre;

public class GenresRepository extends AbstractRepository<MusicGenre> {

    public GenresRepository() {
        super();
    }

    public MusicGenre findByAlbumId(long id) {
        MusicGenre musicGenre = entityManager.find(MusicGenre.class, id);
        return musicGenre;
    }
}
