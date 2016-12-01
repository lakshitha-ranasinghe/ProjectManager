/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nibm.repository;

import com.nibm.entity.StickyNote;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
public class StickyNoteDataAccess {

    @PersistenceContext
    protected EntityManager em;

    public void create(StickyNote instance) {
        em.persist(instance);
    }

    public void destroy(Integer id) {
        StickyNote instance = findById(id);
        em.remove(instance);
    }

    public StickyNote findById(Integer id) {
        return em.find(StickyNote.class, id);
    }

    public void update(StickyNote instance) {
        em.merge(instance);
    }

    public List<StickyNote> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<StickyNote> cq = cb.createQuery(StickyNote.class);
        TypedQuery<StickyNote> result = em.createQuery(cq.select(cq.from(StickyNote.class)));
        return result.getResultList();
    }

    public List<StickyNote> findByEmployee(Integer id) {
        //Replace with Criteria
        TypedQuery<StickyNote> query = em.createQuery("SELECT e FROM StickyNote e WHERE e.createdBy.id = :id",
                StickyNote.class);
        query.setParameter("id", id);
        return query.getResultList();
    }
}
