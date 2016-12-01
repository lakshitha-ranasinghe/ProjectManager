/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nibm.repository;

import com.nibm.entity.MainArea;
import com.nibm.entity.Page;
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
public class PageDataAccess {
    @PersistenceContext
    protected EntityManager em;

    public void create(Page instance) {
        em.persist(instance);
    }

    public void destroy(Integer id) {
        Page instance = findById(id);
        em.remove(instance);
    }

    public Page findById(Integer id) {
        return em.find(Page.class, id);
    }

    public void update(Page instance) {
        em.merge(instance);
    }

    public List<Page> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Page> cq = cb.createQuery(Page.class);
        TypedQuery<Page> result = em.createQuery(cq.select(cq.from(Page.class)));
        return result.getResultList();
    }
}
