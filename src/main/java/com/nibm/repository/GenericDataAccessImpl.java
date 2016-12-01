/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nibm.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Lakshitha
 * @param <T>
 */
@Repository
@Transactional(propagation = Propagation.REQUIRED)
public abstract class GenericDataAccessImpl<T> implements GenericDataAccess <T>{

    @PersistenceContext
    protected EntityManager em;
    
    private final Class<T> entityClass;
    
    public GenericDataAccessImpl(Class<T> entityClass){
        this.entityClass = entityClass;
    }

    @Override
    public void create(T instance) {
        em.persist(instance);
    }

    @Override
    public void destroy(Integer id) {
        T instance = findById(id);
        em.remove(instance);
    }

    @Override
    public T findById(Integer id) {
        return em.find(entityClass, id);
    }

    @Override
    public void update(T instance) {
        em.merge(instance);
    }

    @Override
    public List<T> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        TypedQuery<T> result = em.createQuery(cq.select(cq.from(entityClass)));
        return result.getResultList();
    }
    
    
}
