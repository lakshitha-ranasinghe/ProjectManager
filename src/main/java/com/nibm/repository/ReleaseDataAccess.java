/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nibm.repository;

import com.nibm.entity.Bug;
import com.nibm.entity.SystemRelease;
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
public class ReleaseDataAccess {

    @PersistenceContext
    protected EntityManager em;

    public void create(SystemRelease instance) {
        em.persist(instance);
    }

    public void destroy(Integer id) {
        SystemRelease instance = findById(id);
        em.remove(instance);
    }

    public SystemRelease findById(Integer id) {
        return em.find(SystemRelease.class, id);
    }

    public void update(Bug instance) {
        em.merge(instance);
    }

    public List<SystemRelease> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<SystemRelease> cq = cb.createQuery(SystemRelease.class);
        TypedQuery<SystemRelease> result = em.createQuery(cq.select(cq.from(SystemRelease.class)));
        return result.getResultList();
    }

    public SystemRelease getLatestRelease() {
        TypedQuery<SystemRelease> query = em.createQuery("SELECT r from SystemRelease r WHERE r.releaseType = 0 ORDER BY r.dateOfRelease desc", SystemRelease.class);
        try {
            SystemRelease release = query.setMaxResults(1).getSingleResult();
            return release;
        }
        catch(Exception ex){
            return null;
        }
    }
    public SystemRelease getLatestLiveRelease() {
        TypedQuery<SystemRelease> query = em.createQuery("SELECT r from SystemRelease r WHERE r.releaseType = 1 ORDER BY r.dateOfRelease desc", SystemRelease.class);
        try {
            SystemRelease release = query.setMaxResults(1).getSingleResult();
            return release;
        }
        catch(Exception ex){
            return null;
        }
    }
}
