/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nibm.entity;

import com.nibm.common.Enums.TaskStatus;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Lakshitha
 */
@Entity
public class Task implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    private TaskStatus status;
    @NotNull
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date assignedDate;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date expectedResolveData;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date actualResolvedDate;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date releasedDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date liveReleasedDate;
    @ManyToOne
    @NotNull
    private Employee assignedTo;
    @ManyToOne
    @NotNull
    private Employee assignedBy;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Date getAssignedDate() {
        return assignedDate;
    }

    public void setAssignedDate(Date assignedDate) {
        this.assignedDate = assignedDate;
    }

    public Date getExpectedResolveData() {
        return expectedResolveData;
    }

    public void setExpectedResolveData(Date expectedResolveData) {
        this.expectedResolveData = expectedResolveData;
    }

    public Date getActualResolvedDate() {
        return actualResolvedDate;
    }

    public void setActualResolvedDate(Date actualResolvedDate) {
        this.actualResolvedDate = actualResolvedDate;
    }

    public Employee getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(Employee assignedTo) {
        this.assignedTo = assignedTo;
    }

    public Employee getAssignedBy() {
        return assignedBy;
    }

    public void setAssignedBy(Employee assignedBy) {
        this.assignedBy = assignedBy;
    }

    public Date getReleasedDate() {
        return releasedDate;
    }

    public void setReleasedDate(Date releasedDate) {
        this.releasedDate = releasedDate;
    }

    public Date getLiveReleasedDate() {
        return liveReleasedDate;
    }

    public void setLiveReleasedDate(Date liveReleasedDate) {
        this.liveReleasedDate = liveReleasedDate;
    }
    
}
