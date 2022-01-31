package dao.generic;

import db.EntityManagerHelper;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.Serializable;
import java.util.List;

public abstract class AbstractJpaDao<K, T extends Serializable> implements IGenericDao<K, T> {

    protected EntityManager entityManager;
    private Class<T> _class;

    public AbstractJpaDao() {
        this.entityManager = EntityManagerHelper.getEntityManager();
    }

    public void setClass(Class<T> _classToSet) {
        this._class = _classToSet;
    }

    public T findOne(K id) {
        return entityManager.find(_class, id);
    }

    public List<T> findAll() {
        return entityManager.createQuery("select e from " + _class.getName() + " as e", _class).getResultList();
    }

    public void save(T entity) {
        EntityTransaction t = this.entityManager.getTransaction();
        t.begin();
        entityManager.persist(entity);
        t.commit();
    }

    public T update(final T entity) {
        EntityTransaction t = this.entityManager.getTransaction();
        t.begin();
        T res = entityManager.merge(entity);
        t.commit();
        return res;
    }

    public void delete(T entity) {
        EntityTransaction t = this.entityManager.getTransaction();
        t.begin();
        entityManager.remove(entity);
        t.commit();
    }

    public void deleteById(K entityId) {
        T entity = findOne(entityId);
        delete(entity);
    }
}
