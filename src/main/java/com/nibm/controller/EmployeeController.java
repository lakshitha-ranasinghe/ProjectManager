/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nibm.controller;

import com.nibm.common.InactiveEmployee;
import com.nibm.entity.Employee;
import com.nibm.repository.EmployeeDataAccess;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Lakshitha
 */
@Controller
@RequestMapping(value = "/employee")
public class EmployeeController {

    @Autowired
    EmployeeDataAccess employeeDataAccess; 

    @RequestMapping(value = "registration", method = GET)
    public String register() {
        return "newEmployee";
    }

    @RequestMapping(value = "manage", method = GET)
    public String manage(Map model) {
        List<Employee> employees = employeeDataAccess.findAll();
        for(Employee employee : InactiveEmployee.getInactiveEmployees()){
            employees.remove(employee);
        }
        model.put("employees", employees);
        return "manageEmployee";
    }

    @RequestMapping(value = "registration", method = POST)
    public String processRegistration(Employee employee) {
        employeeDataAccess.create(employee);
        return "redirect:/";
    }
           
    @RequestMapping(value = "delete")
    @ResponseBody
    public String delete(@RequestParam Integer id){
        employeeDataAccess.destroy(id);
        return "success";
    }
}
