/**
 * @author: Calin Irina, I2E2
 */

//created an AbstractRepository class using generics and extended the others according to this one

package jpa.repo;

import jpa.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public abstract class AbstractRepository<T> {
    public static EntityManager entityManager;
    public static EntityTransaction entityTransaction;

    protected AbstractRepository() {
        entityManager = PersistenceUtil.getInstance().createEntityManager();
        entityTransaction = entityManager.getTransaction();
    }

    public void create(T entity) {
        beginTransaction();
        entityManager.persist(entity);
        commitTransaction();
        System.out.println("Created successfully.");
    }

    protected void beginTransaction() {
        try {
            entityTransaction.begin();
        } catch (IllegalStateException e) {
            rollbackTransaction();
        }
    }

    protected void commitTransaction() {
        try {
            entityTransaction.commit();
            //entityManager.close();
        } catch (IllegalStateException e) {
            rollbackTransaction();
        }
    }

    protected void rollbackTransaction() {
        try {
            entityTransaction.rollback();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }
}
