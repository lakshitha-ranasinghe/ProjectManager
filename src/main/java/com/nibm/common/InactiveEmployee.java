/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nibm.common;

import com.nibm.entity.Employee;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lakshitha
 */
public class InactiveEmployee {
    private static final List<Employee> inactiveEmployees = new ArrayList<Employee>();
    
    static{
        Employee lakshitha = new Employee();
        lakshitha.setId(1);
        inactiveEmployees.add(lakshitha);
        
        Employee tharindu = new Employee();
        tharindu.setId(4);
        inactiveEmployees.add(tharindu);
        
        Employee shehan = new Employee();
        shehan.setId(8);
        inactiveEmployees.add(shehan);
    }
    
    public static List<Employee> getInactiveEmployees(){
        return inactiveEmployees;
    }
}
