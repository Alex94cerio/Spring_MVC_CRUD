package com.project2.spring.dao;

import org.springframework.beans.factory.InitializingBean;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public abstract class AbstractDao<PK extends Serializable, T> implements InitializingBean, BaseDao<PK, T> {

    private final Class<T> persistentClass;
    @PersistenceContext
    EntityManager entityManager;

    private CriteriaBuilder criteriaBuilder;
    private CriteriaQuery<T> criteriaQuery;
    private Root<T> root;

    @SuppressWarnings("unchecked")
    public AbstractDao(){
        this.persistentClass =(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];

    }

    public void afterPropertiesSet(){
        criteriaBuilder = entityManager.getCriteriaBuilder();
        criteriaQuery = criteriaBuilder.createQuery(persistentClass);
        root = criteriaQuery.from(persistentClass);
    }

    protected T getByKey(PK key) {
        return (T) entityManager.find(persistentClass, key);
    }

    protected EntityManager getEntityManager(){
        return this.entityManager;
    }

    protected void persist(T entity) {
        entityManager.persist(entity);
    }

    protected void update(T entity) {
        entityManager.merge(entity);
    }

    protected void delete(T entity) {
        entityManager.remove(entity);
    }



    // THESE ARE METHODS THAT CAN BE USED FROM SERVICES

    public T findById(PK id){
        return getByKey(id);
    }

    public void deleteById(PK id) { delete(getByKey(id));
    }

    public List<T> findAll(){
        TypedQuery<T> query = entityManager.createQuery(criteriaQuery.select(root));
        return query.getResultList();
    }

    public void save(T elem){ persist(elem); }

    public CriteriaBuilder getCriteriaBuilder() {
        return criteriaBuilder;
    }

    public CriteriaQuery<T> getCriteriaQuery() {
        return criteriaQuery;
    }

    public Root<T> getRoot() {
        return root;
    }

    public void setCriteriaBuilder(CriteriaBuilder criteriaBuilder) {
        this.criteriaBuilder = criteriaBuilder;
    }

    public void setCriteriaQuery(CriteriaQuery<T> criteriaQuery) {
        this.criteriaQuery = criteriaQuery;
    }

    public void setRoot(Root<T> root) {
        this.root = root;
    }

    public void resetCriteriaQuery(){
        criteriaQuery = criteriaBuilder.createQuery(persistentClass);
        root = criteriaQuery.from(persistentClass);
    }
}

