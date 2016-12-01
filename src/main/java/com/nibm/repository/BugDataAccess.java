/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nibm.repository;

import com.nibm.common.Enums;
import com.nibm.common.Enums.BugStatus;
import com.nibm.entity.Bug;
import com.nibm.entity.Employee;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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
public class BugDataAccess {

    @PersistenceContext
    protected EntityManager em;

    public void create(Bug instance) {
        em.persist(instance);
    }

    public void destroy(Integer id) {
        Bug instance = findById(id);
        em.remove(instance);

    }

    public Bug findById(Integer id) {
        return em.find(Bug.class, id);
    }

    public void update(Bug instance) {
        em.merge(instance);
    }

    public List<Bug> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Bug> cq = cb.createQuery(Bug.class);
        TypedQuery<Bug> result = em.createQuery(cq.select(cq.from(Bug.class)));
        return result.getResultList();
    }
    
    public List<Bug> findFirst(int max) {
        TypedQuery<Bug> query = null;
        query = em.createQuery("SELECT e FROM Bug e ORDER BY e.id DESC" ,
                Bug.class);
        query.setFirstResult(0);
        query.setMaxResults(max);
        List<Bug> bugs = query.getResultList();
        return bugs;
    }

    public List<Bug> findByTitle(String keyword) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Bug> c = cb.createQuery(Bug.class);
        Root<Bug> bugs = c.from(Bug.class);
        c.select(bugs).where(
                cb.like(bugs.<String>get("title"), "%" + keyword + "%")
        );

        TypedQuery<Bug> query = em.createQuery(c);
        return query.getResultList();
    }

    public List<Bug> findByReleaseDataAndType(Date releasedDate, BugStatus status) {
        TypedQuery<Bug> query = null;
        if (status == BugStatus.Released) {
            query = em.createQuery("SELECT e FROM Bug e WHERE e.releasedDate = :releasedDate",
                    Bug.class);
        } else if (status == BugStatus.Live_Released) {
            query = em.createQuery("SELECT e FROM Bug e WHERE e.liveReleasedData = :releasedDate",
                    Bug.class);
        }
        query.setParameter("releasedDate", releasedDate);
        //query.setParameter("status", status);
        List<Bug> bugs = query.getResultList();
        Collections.sort(bugs, new Comparator<Bug>() {
            @Override
            public int compare(Bug o1, Bug o2) {
                return o1.getId().compareTo(o2.getId());
            }
        });
        return bugs;
    }

    public void resolveBug(Integer id) {
        Bug bug = em.find(Bug.class, id);
        bug.setStatus(Enums.BugStatus.Resolved);
        bug.setResolvedDate(new Date());
        em.close();
    }

    public void releaseBugFix(Integer id) {
        Bug bug = em.find(Bug.class, id);
        bug.setStatus(Enums.BugStatus.Released);
        bug.setReleasedDate(new Date());
        em.close();
    }

    public void liveReleaseBugFix(Integer id) {
        Bug bug = em.find(Bug.class, id);
        bug.setStatus(Enums.BugStatus.Live_Released);
        bug.setLiveReleasedData(new Date());
        em.close();
    }

    public void closeBug(Integer id) {
        Bug bug = em.find(Bug.class, id);
        bug.setStatus(Enums.BugStatus.Closed);
        bug.setClosedDate(new Date());
        em.close();
    }

    public void invalidBug(Integer id) {
        Bug bug = em.find(Bug.class, id);
        bug.setStatus(Enums.BugStatus.Invalid);
        em.close();
    }

    public void assignBug(Integer id) {
        Bug bug = em.find(Bug.class, id);
        bug.setStatus(Enums.BugStatus.Assigned);
        em.close();
    }

    public List<Bug> assignedOnly() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Bug> c = cb.createQuery(Bug.class);
        Root<Bug> bug = c.from(Bug.class);
        c.select(bug)
                .where(cb.equal(bug.get("status"), Enums.BugStatus.Assigned));
        c.orderBy(cb.desc(bug.get("id")));
        TypedQuery<Bug> query = em.createQuery(c);
        return query.getResultList();
    }

    public List<Bug> resolvedOnly() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Bug> c = cb.createQuery(Bug.class);
        Root<Bug> bug = c.from(Bug.class);
        c.select(bug)
                .where(cb.equal(bug.get("status"), Enums.BugStatus.Resolved));
        c.orderBy(cb.desc(bug.get("id")));
        TypedQuery<Bug> query = em.createQuery(c);
        return query.getResultList();
    }

    public List<Bug> releasedOnly() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Bug> c = cb.createQuery(Bug.class);
        Root<Bug> bug = c.from(Bug.class);
        c.select(bug)
                .where(cb.equal(bug.get("status"), Enums.BugStatus.Released));
        c.orderBy(cb.desc(bug.get("id")));
        TypedQuery<Bug> query = em.createQuery(c);
        return query.getResultList();
    }

    public List<Bug> closedOnly() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Bug> c = cb.createQuery(Bug.class);
        Root<Bug> bug = c.from(Bug.class);
        c.select(bug)
                .where(cb.equal(bug.get("status"), Enums.BugStatus.Closed));
        c.orderBy(cb.desc(bug.get("id")));
        TypedQuery<Bug> query = em.createQuery(c);
        return query.getResultList();
    }

    public List<Bug> fixedReleased() {
        TypedQuery<Bug> query = null;
        query = em.createQuery("SELECT e FROM Bug e WHERE e.status != :firstStatus AND "
                + "e.status != :secondStatus",
                Bug.class);
        query.setParameter("firstStatus", BugStatus.Active);
        query.setParameter("secondStatus", BugStatus.Assigned);
        List<Bug> bugs = query.getResultList();
        return bugs;
    }

    public List<Bug> onlyMe(Employee employee) {
        //Replace with Criteria
        TypedQuery<Bug> query = null;
        if (employee.getType() == Enums.EmployeeType.Developer || employee.getType() == Enums.EmployeeType.Engineer) {
            query = em.createQuery("SELECT e FROM Bug e WHERE e.assignedTo.id = :id order by e.id desc",
                    Bug.class);
        } else if (employee.getType() == Enums.EmployeeType.QA) {
            query = em.createQuery("SELECT e FROM Bug e WHERE e.reportedBy.id = :id order by e.id desc",
                    Bug.class);
        } else {
            query = em.createQuery("SELECT e FROM Bug e WHERE e.reportedBy.id = :id order by e.id desc",
                    Bug.class);
        }
        query.setParameter("id", employee.getId());
        List<Bug> bugs = query.getResultList();
        Collections.sort(bugs, new Comparator<Bug>() {
            @Override
            public int compare(Bug o1, Bug o2) {
                return o1.getStatus().compareTo(o2.getStatus());
            }
        });
        return bugs;
    }
    
    public List<Bug> onlyMeFixed(Employee employee) {
        //Replace with Criteria
        TypedQuery<Bug> query = null;
        if (employee.getType() == Enums.EmployeeType.Developer || employee.getType() == Enums.EmployeeType.Engineer) {
            query = em.createQuery("SELECT e FROM Bug e WHERE e.assignedTo.id = :id AND e.status != :firstStatus and e.status != :secondStatus",
                    Bug.class);
        } else if (employee.getType() == Enums.EmployeeType.QA) {
            query = em.createQuery("SELECT e FROM Bug e WHERE e.reportedBy.id = :id AND e.status != :firstStatus and e.status != :secondStatus",
                    Bug.class);
        } else {
            query = em.createQuery("SELECT e FROM Bug e WHERE e.reportedBy.id = :id AND e.status != :firstStatus and e.status != :secondStatus",
                    Bug.class);
        }
        query.setParameter("id", employee.getId());
        query.setParameter("firstStatus", BugStatus.Active);
        query.setParameter("secondStatus", BugStatus.Assigned);
        List<Bug> bugs = query.getResultList();
        Collections.sort(bugs, new Comparator<Bug>() {
            @Override
            public int compare(Bug o1, Bug o2) {
                return o1.getStatus().compareTo(o2.getStatus());
            }
        });
        return bugs;
    }
    
    public List<Bug> findByPrimaryMenuOnly(String primaryMenu){
        TypedQuery<Bug> query = em.createQuery("SELECT e FROM Bug e WHERE e.title like :title",
                    Bug.class);
       
        query.setParameter("title", primaryMenu+"%");
        List<Bug> bugs = query.getResultList();
        Collections.sort(bugs, new Comparator<Bug>() {
            @Override
            public int compare(Bug o1, Bug o2) {
                return o1.getStatus().compareTo(o2.getStatus());
            }
        });
        return bugs;
    }
    
    public List<Bug> onlyMeAssigned(Employee employee) {
        //Replace with Criteria
        TypedQuery<Bug> query = null;
        if (employee.getType() == Enums.EmployeeType.Developer || employee.getType() == Enums.EmployeeType.Engineer) {
            query = em.createQuery("SELECT e FROM Bug e WHERE e.assignedTo.id = :id AND (e.status = :firstStatus or e.status = :secondStatus)",
                    Bug.class);
        } else if (employee.getType() == Enums.EmployeeType.QA) {
            query = em.createQuery("SELECT e FROM Bug e WHERE e.reportedBy.id = :id AND (e.status = :firstStatus or e.status = :secondStatus)",
                    Bug.class);
        } else {
            query = em.createQuery("SELECT e FROM Bug e WHERE e.reportedBy.id = :id AND (e.status = :firstStatus or e.status = :secondStatus)",
                    Bug.class);
        }
        query.setParameter("id", employee.getId());
        query.setParameter("firstStatus", BugStatus.Active);
        query.setParameter("secondStatus", BugStatus.Assigned);
        List<Bug> bugs = query.getResultList();
        Collections.sort(bugs, new Comparator<Bug>() {
            @Override
            public int compare(Bug o1, Bug o2) {
                return o1.getStatus().compareTo(o2.getStatus());
            }
        });
        return bugs;
    } 
}
