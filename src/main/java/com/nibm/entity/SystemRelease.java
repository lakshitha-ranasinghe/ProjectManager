/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nibm.entity;

import com.nibm.common.Enums.ReleaseType;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 *
 * @author Lakshitha
 */
@Entity
public class SystemRelease implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateOfRelease;
    private Integer bugFixes;
    private Integer tasks;
    private ReleaseType releaseType;

    public Date getDateOfRelease() {
        return dateOfRelease;
    }

    public void setDateOfRelease(Date releaseDate) {
        this.dateOfRelease = releaseDate;
    }

    public Integer getBugFixes() {
        return bugFixes;
    }

    public void setBugFixes(Integer noOfBugFixes) {
        this.bugFixes = noOfBugFixes;
    }

    public Integer getTasks() {
        return tasks;
    }

    public void setTasks(Integer noOfTasks) {
        this.tasks = noOfTasks;
    }

    public ReleaseType getReleaseType() {
        return releaseType;
    }

    public void setReleaseType(ReleaseType releaseType) {
        this.releaseType = releaseType;
    }
    
}
