/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nibm.repository;

import com.nibm.common.Enums;
import com.nibm.entity.Bug;
import com.nibm.entity.Employee;
import com.nibm.entity.Task;
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
public class TaskDataAccess {

    @PersistenceContext
    protected EntityManager em;

    public void create(Task instance) {
        em.persist(instance);
    }

    public void destroy(Integer id) {
        Task instance = findById(id);
        em.remove(instance);
    }

    public Task findById(Integer id) {
        return em.find(Task.class, id);
    }

    public void update(Task instance) {
        em.merge(instance);
    }

    public List<Task> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Task> cq = cb.createQuery(Task.class);
        TypedQuery<Task> result = em.createQuery(cq.select(cq.from(Task.class)));
        return result.getResultList();
    }

    public List<Task> findByTitle(String keyword) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Task> c = cb.createQuery(Task.class);
        Root<Task> tasks = c.from(Task.class);
        c.select(tasks).where(
                cb.like(tasks.<String>get("title"), "%" + keyword + "%")
        );

        TypedQuery<Task> query = em.createQuery(c);
        return query.getResultList();
    }

    public void completeTask(Integer id) {
        Task task = em.find(Task.class, id);
        task.setActualResolvedDate(new Date());
        task.setStatus(Enums.TaskStatus.Completed);
        em.close();
    }
    
    public void releaseTask(Integer id) {
        Task task = em.find(Task.class, id);
        task.setStatus(Enums.TaskStatus.Released);
        task.setReleasedDate(new Date());
        em.close();
    }
    
    public void liveReleaseTask(Integer id) {
        Task task = em.find(Task.class, id);
        task.setStatus(Enums.TaskStatus.Live_Released);
        task.setLiveReleasedDate(new Date());
        em.close();
    }
    
    public List<Task> assignedOnly() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Task> c = cb.createQuery(Task.class);
        Root<Task> task = c.from(Task.class);
        c.select(task)
                .where(cb.equal(task.get("status"), Enums.TaskStatus.Assigned));

        TypedQuery<Task> query = em.createQuery(c);
        return query.getResultList();
    }

    public List<Task> completedOnly() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Task> c = cb.createQuery(Task.class);
        Root<Task> task = c.from(Task.class);
        c.select(task)
                .where(cb.equal(task.get("status"), Enums.TaskStatus.Completed));

        TypedQuery<Task> query = em.createQuery(c);
        return query.getResultList();
    }

    public List<Task> releasedOnly() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Task> c = cb.createQuery(Task.class);
        Root<Task> task = c.from(Task.class);
        c.select(task)
                .where(cb.equal(task.get("status"), Enums.TaskStatus.Released));

        TypedQuery<Task> query = em.createQuery(c);
        return query.getResultList();
    }

    public List<Task> onlyMe(Employee employee) {
        //Replace with Criteria
        TypedQuery<Task> query  = null;
        if (employee.getType() == Enums.EmployeeType.Developer) {
            query = em.createQuery("SELECT e FROM Task e WHERE e.assignedTo.id = :id",
                    Task.class);
        } else if (employee.getType() == Enums.EmployeeType.Engineer) {
            query = em.createQuery("SELECT e FROM Task e WHERE e.assignedBy.id = :id OR e.assignedTo.id = :id",
                    Task.class);
        } else {
            query = em.createQuery("SELECT e FROM Task e WHERE e.assignedTo.id = :id",
                    Task.class);  
        }
        query.setParameter("id", employee.getId());
        List<Task> tasks = query.getResultList();
        Collections.sort(tasks,new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                return o1.getStatus().compareTo(o2.getStatus());
            }
        });       
        return tasks;
    }
    
    public List<Task> findByReleaseDataAndType(Date releasedDate, Enums.TaskStatus status) {
        TypedQuery<Task> query = null;
        if (status == Enums.TaskStatus.Released) {
            query = em.createQuery("SELECT e FROM Task e WHERE e.releasedDate = :releasedDate",
                    Task.class);
        }
        else if(status == Enums.TaskStatus.Live_Released){
            query = em.createQuery("SELECT e FROM Task e WHERE e.liveReleasedDate = :releasedDate",
                    Task.class);
        }
        query.setParameter("releasedDate", releasedDate);
        //query.setParameter("status", status);
        List<Task> tasks = query.getResultList();
        Collections.sort(tasks, new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                return o1.getId().compareTo(o2.getId());
            }
        });
        return tasks;
    }
}
