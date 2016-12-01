/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nibm.controller;

import com.nibm.common.Enums;
import com.nibm.common.InactiveEmployee;
import com.nibm.common.TaskComparator;
import com.nibm.entity.Employee;
import com.nibm.entity.Task;
import com.nibm.repository.EmployeeDataAccess;
import com.nibm.repository.TaskDataAccess;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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
@RequestMapping(value = "/task")
public class TaskController {

    @Autowired
    EmployeeDataAccess employeeDataAccess;

    @Autowired
    TaskDataAccess taskDataAccess;

    private final static String SUCCESS = "success";

    @RequestMapping(value = "add", method = GET)
    public String addTask(Map model) {
        List<Employee> employees = employeeDataAccess.findDevelopers();
        for (Employee employee : InactiveEmployee.getInactiveEmployees()) {
            employees.remove(employee);
        }
        model.put("assignedTo", employees);
        return "addTask";
    }

    @RequestMapping(value = "add", method = POST)
    public String processAddBug(Task task, Integer assignedEmployee) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Employee reportedBy = employeeDataAccess.findByUsername(user.getUsername());
        Employee assingnedTo = employeeDataAccess.findById(assignedEmployee);

        task.setAssignedDate(new Date());
        task.setAssignedTo(assingnedTo);
        task.setAssignedBy(reportedBy);
        task.setStatus(Enums.TaskStatus.Assigned);
        taskDataAccess.create(task);
        return "redirect:/task/viewAll";
    }

    @RequestMapping(value = "viewAll", method = GET)
    public String viewAll(Map model) {
        List<Task> bugs = taskDataAccess.findAll();
        Collections.sort(bugs, new TaskComparator());
        model.put("tasks", bugs);
        return "viewAllTasks";
    }

    @RequestMapping(value = "search", method = GET)
    public String search(Map model, String keyword) {
        List<Task> tasks = taskDataAccess.findByTitle(keyword);
        Collections.sort(tasks, new TaskComparator());
        model.put("tasks", tasks);
        return "viewAllTasks";
    }

    @RequestMapping(value = "info", method = GET)
    @ResponseBody
    public String info(@RequestParam Integer id) {
        Task task = taskDataAccess.findById(id);
        String data = task.getAssignedDate().toString() + "@";
        String completedDate = task.getActualResolvedDate() == null ? "Not Completed" : task.getActualResolvedDate().toString();
        String releasedDate = task.getReleasedDate() == null ? "Not Released" : task.getReleasedDate().toString();
        data = data + completedDate + "@" + releasedDate;
        return data;
    }

    @RequestMapping(value = "delete", method = GET)
    @ResponseBody
    public String delete(@RequestParam Integer id) {
        taskDataAccess.destroy(id);
        return SUCCESS;
    }

    @RequestMapping(value = "complete", method = GET)
    @ResponseBody
    public String completed(@RequestParam Integer id) {
        taskDataAccess.completeTask(id);
        return SUCCESS;
    }

    @RequestMapping(value = "release", method = GET)
    @ResponseBody
    public String release(@RequestParam Integer id) {
        taskDataAccess.releaseTask(id);
        return SUCCESS;
    }

    @RequestMapping(value = "assignedOnly", method = GET)
    public String assignedOnly(Map model) {
        List<Task> assignedTasks = taskDataAccess.assignedOnly();
        model.put("tasks", assignedTasks);
        return "viewAllTasks";
    }

    @RequestMapping(value = "completedOnly", method = GET)
    public String resolvedOnly(Map model) {
        List<Task> completedTasks = taskDataAccess.completedOnly();
        model.put("tasks", completedTasks);
        return "viewAllTasks";
    }

    @RequestMapping(value = "releasedOnly", method = GET)
    public String releasedOnly(Map model) {
        List<Task> releasedTasks = taskDataAccess.releasedOnly();
        model.put("tasks", releasedTasks);
        return "viewAllTasks";
    }

    @RequestMapping(value = "onlyMe", method = GET)
    public String onlyMe(Map model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Employee employee = employeeDataAccess.findByUsername(user.getUsername());

        if (employee.getType() == Enums.EmployeeType.QA) {
            List<Task> releasedTasks = taskDataAccess.releasedOnly();
            model.put("tasks", releasedTasks);
        } else {
            List<Task> onlyMeTasks = taskDataAccess.onlyMe(employee);
            model.put("tasks", onlyMeTasks);
        }
        return "viewAllTasks";
    }
}
