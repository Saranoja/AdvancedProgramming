/**
 * @author: Calin Irina, I2E2
 */


package factory;

//abstract factory in order to create the DAO objects

import jpa.repo.AbstractRepository;

public interface RepoAbstractFactory {

    public AbstractRepository createRepository(String type);
}
