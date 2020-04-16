/**
 * @author: Calin Irina, I2E2
 */

package util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceUtil {
    private static EntityManagerFactory instance;

    private PersistenceUtil() {
        instance = Persistence.createEntityManagerFactory("MusicAlbumsPU");
    }

    public static EntityManagerFactory getInstance() {
        if (instance == null)
            new PersistenceUtil();
        return instance;
    }
}