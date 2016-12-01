/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nibm.common;

/**
 *
 * @author Lakshitha
 */
public class Enums {
    public enum EmployeeType{
        Developer, Database, QA, Support,Engineer
    }
    public enum BugStatus{
        Closed,Resolved,Assigned,Active,Released,Invalid,Live_Released
    }
    public enum MenuType{
        Primary,Secondary,Ternary
    }
    public enum TaskStatus{
        Assigned, Completed, Released, Live_Released
    }
    public enum ReleaseType{
        Test, Live
    }
}
