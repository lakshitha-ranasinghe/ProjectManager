/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nibm.repository;

import com.nibm.entity.MainArea;
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
 */
@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class MainAreaDataAccess {

    @PersistenceContext
    protected EntityManager em;

    public void create(MainArea instance) {
        em.persist(instance);
    }

    public void destroy(Integer id) {
        MainArea instance = findById(id);
        em.remove(instance);
    }

    public MainArea findById(Integer id) {
        return em.find(MainArea.class, id);
    }

    public void update(MainArea instance) {
        em.merge(instance);
    }

    public List<MainArea> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<MainArea> cq = cb.createQuery(MainArea.class);
        TypedQuery<MainArea> result = em.createQuery(cq.select(cq.from(MainArea.class)));
        return result.getResultList();
    }
}
