/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nibm.repository;

import com.nibm.common.Enums.EmployeeType;
import com.nibm.entity.Employee;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Lakshitha
 */
@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class EmployeeDataAccess {

    @PersistenceContext
    protected EntityManager em;

    public void create(Employee instance) {
        em.persist(instance);
    }

    public void destroy(Integer id) {
        Employee instance = findById(id);
        em.remove(instance);
    }

    public Employee findById(Integer id) {
        return em.find(Employee.class, id);
    }

    public void update(Employee instance) {
        em.merge(instance);
    }

    public List<Employee> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
        TypedQuery<Employee> result = em.createQuery(cq.select(cq.from(Employee.class)));
        return result.getResultList();
    }

    public List<Employee> findDevelopers() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> c = cb.createQuery(Employee.class);
        Root<Employee> emp = c.from(Employee.class);
        c.select(emp)
                .where(
                        cb.in(emp.get("type"))
                        .value(EmployeeType.Developer)
                        .value(EmployeeType.Engineer)
                );

        TypedQuery<Employee> query = em.createQuery(c);
        return query.getResultList();
    }
    
    public List<Employee> finalAllQA() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> c = cb.createQuery(Employee.class);
        Root<Employee> emp = c.from(Employee.class);
        c.select(emp)
                .where(
                        cb.in(emp.get("type"))
                        .value(EmployeeType.QA)
                );

        TypedQuery<Employee> query = em.createQuery(c);
        return query.getResultList();
    }

    public Employee findByUsername(String username) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> c = cb.createQuery(Employee.class);
        Root<Employee> emp = c.from(Employee.class);
        c.select(emp)
                .where(cb.equal(emp.get("userName"), username));

        TypedQuery<Employee> query = em.createQuery(c);
        return query.getSingleResult();
    }
}
