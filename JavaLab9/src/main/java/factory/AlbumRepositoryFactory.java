/**
 * @author: Calin Irina, I2E2
 */

package factory;

import jpa.repo.AbstractRepository;
import jpa.repo.AlbumRepository;

public class AlbumRepositoryFactory implements RepoAbstractFactory {
    @Override
    public AbstractRepository createRepository(String type) {
        if (type.equals("jdbc")) {
            return new controllers.AlbumController(app.AlbumManager.getDb());
        } else if (type.equals("jpa")) {
            return new AlbumRepository();
        } else return null;
    }
}
