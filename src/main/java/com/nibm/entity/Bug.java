/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nibm.entity;

import com.nibm.common.Enums.BugStatus;
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
public class Bug implements Serializable {
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
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date assignedDate; 
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date resolvedDate;
    @ManyToOne
    @NotNull
    private Employee assignedTo;
    @ManyToOne
    @NotNull
    private Employee reportedBy;
    @NotNull
    private BugStatus status;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date closedDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date releasedDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date liveReleasedData;
    private byte[] image;
    @ManyToOne
    private Page page;

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

    public Date getAssignedDate() {
        return assignedDate;
    }

    public void setAssignedDate(Date assignedDate) {
        this.assignedDate = assignedDate;
    }

    public Date getResolvedDate() {
        return resolvedDate;
    }

    public void setResolvedDate(Date resolvedDate) {
        this.resolvedDate = resolvedDate;
    }

    public Employee getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(Employee assignedTo) {
        this.assignedTo = assignedTo;
    }

    public Employee getReportedBy() {
        return reportedBy;
    }

    public void setReportedBy(Employee reportedBy) {
        this.reportedBy = reportedBy;
    }

    public BugStatus getStatus() {
        return status;
    }

    public void setStatus(BugStatus status) {
        this.status = status;
    }

    public Date getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(Date closedDate) {
        this.closedDate = closedDate;
    }

    public Date getReleasedDate() {
        return releasedDate;
    }

    public void setReleasedDate(Date releasedDate) {
        this.releasedDate = releasedDate;
    }

    public Date getLiveReleasedData() {
        return liveReleasedData;
    }

    public void setLiveReleasedData(Date liveReleasedData) {
        this.liveReleasedData = liveReleasedData;
    }
                
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }


    @Override
    public String toString() {
        return "Bug{" + "id=" + id + ", title=" + title + ", description=" + description + ", assignedDate=" + assignedDate + ", resolvedDate=" + resolvedDate + ", assignedTo=" + assignedTo + ", reportedBy=" + reportedBy + ", status=" + status + ", closedDate=" + closedDate + '}';
    }
    
}
