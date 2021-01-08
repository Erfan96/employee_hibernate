package service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

public abstract class EntityDao<T, U> {
    protected EntityManager entityManager;

    public EntityDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(T entity) {
        entityManager.persist(entity);
    }

    public T load(U id) {
       return entityManager.find(getEntityClass(), id);
    }

    public void delete(T entity) {
        entityManager.remove(entity);
    }

    public void update(T entity) {
        entityManager.merge(entity);
    }

    public abstract Class<T> getEntityClass();

    public CriteriaBuilder getCriteriaBuilder() {
     return entityManager.getCriteriaBuilder();
    }

    public CriteriaQuery<T> getCriteriaQuery() {
        return getCriteriaBuilder().createQuery(getEntityClass());
    }
}
