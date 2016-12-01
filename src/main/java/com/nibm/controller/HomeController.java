/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nibm.controller;

import com.nibm.common.Enums;
import com.nibm.common.InactiveEmployee;
import com.nibm.entity.Bug;
import com.nibm.entity.Employee;
import com.nibm.entity.StickyNote;
import com.nibm.entity.SystemRelease;
import com.nibm.entity.Task;
import com.nibm.repository.BugDataAccess;
import com.nibm.repository.EmployeeDataAccess;
import com.nibm.repository.ReleaseDataAccess;
import com.nibm.repository.StickyNoteDataAccess;
import com.nibm.repository.TaskDataAccess;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Lakshitha
 */
@Controller
@RequestMapping(value = "/")
public class HomeController {

    private final static String SUCCESS = "success";

    @Autowired
    StickyNoteDataAccess stickyNoteDataAccess;

    @Autowired
    EmployeeDataAccess employeeDataAccess;

    @Autowired
    BugDataAccess bugDataAccess;

    @Autowired
    TaskDataAccess taskDataAccess;

    @Autowired
    ReleaseDataAccess releaseDataAccess;

    @RequestMapping(method = GET)
    public String home(java.util.Map model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Employee createdBy = employeeDataAccess.findByUsername(user.getUsername());
        List<StickyNote> notes = stickyNoteDataAccess.findByEmployee(createdBy.getId());
        model.put("notes", notes);
        model.put("name", createdBy.getFullName().split(" ")[0]);

        int developerBugCout = 0, developerTaskCount = 0;
        int othersBugCout = 0, othersTaskCount = 0;
        int engineerBugResolvedCount = 0, engineerTaskResolvedCount = 0;

        List<Bug> onlyMeBugs = bugDataAccess.onlyMe(createdBy);
       
        for (Bug bug : onlyMeBugs) {
            if (bug.getStatus() == Enums.BugStatus.Assigned) {
                developerBugCout++;
            } else if (bug.getStatus() == Enums.BugStatus.Released) {
                othersBugCout++;
            }
        }

        if (createdBy.getType() != Enums.EmployeeType.QA) {
            List<Task> onlyMeTasks = taskDataAccess.onlyMe(createdBy);
            for (Task task : onlyMeTasks) {
                if (task.getStatus() == Enums.TaskStatus.Assigned && task.getAssignedTo().equals(createdBy)) {
                    developerTaskCount++;
                }
            }
        }
        else{
            othersTaskCount = taskDataAccess.releasedOnly().size();
        }

        engineerBugResolvedCount = bugDataAccess.resolvedOnly().size();
        engineerTaskResolvedCount = taskDataAccess.completedOnly().size();

        if (createdBy.getType() == Enums.EmployeeType.Developer) {
            model.put("bugAmount", developerBugCout);
            model.put("taskAmount", developerTaskCount);
            model.put("bugDescription", "you have " + developerBugCout + " bugs to resolve");
            model.put("taskDescription", "you have " + developerTaskCount + " tasks to complete");
        } else if (createdBy.getType() == Enums.EmployeeType.Engineer) {
            model.put("bugAmount", developerBugCout + engineerBugResolvedCount);
            model.put("taskAmount", developerTaskCount + engineerTaskResolvedCount);
            model.put("bugDescription", "you have " + developerBugCout + " bugs to resolve and "
                    + engineerBugResolvedCount + " bugs to release");
            model.put("taskDescription", "you have " + developerTaskCount + " tasks to complete and "
                    + engineerTaskResolvedCount + " tasks to release");
        } else {
            model.put("bugAmount", othersBugCout);
            model.put("taskAmount", othersTaskCount);
            model.put("bugDescription", "you have " + othersBugCout + " bugs to test");
            model.put("taskDescription", "you have " + othersTaskCount + " tasks to test");
        }

        SystemRelease latestRelease = releaseDataAccess.getLatestRelease();
        SystemRelease latestLiveRelease = releaseDataAccess.getLatestLiveRelease();
        if (latestRelease != null && latestLiveRelease != null) {
            model.put("description", "Latest Test release on "
                    + latestRelease.getDateOfRelease() + ". Latest Live Release on " + latestLiveRelease.getDateOfRelease());
        } else if (latestRelease == null && latestLiveRelease != null) {
            model.put("description", "Latest Live Release on " + latestLiveRelease.getDateOfRelease() + ". No Test releases yet");
        } else if (latestRelease != null && latestLiveRelease == null) {
            model.put("description", "Latest Test Release on " + latestRelease.getDateOfRelease() + ". No Live releases yet");
        } else {
            model.put("description", "No Releases yet ");
        }

        model.put("totalReported", String.valueOf(bugDataAccess.findAll().size()));
        String individualReports = "";
        for (Employee employee : employeeDataAccess.finalAllQA()) {
            individualReports = individualReports + " " + employee.getFullName() + " - <b>" + String.valueOf(bugDataAccess.onlyMe(employee).size() + "</b> ");
        }
        model.put("individualReports", individualReports);

        model.put("totalComplete", bugDataAccess.fixedReleased().size());

        String individualFix = "";
        List<Employee> developers = employeeDataAccess.findDevelopers();

        for (Employee employee : InactiveEmployee.getInactiveEmployees()) {
            developers.remove(employee);
        }

        Collections.sort(developers, new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return o1.getFullName().compareTo(o2.getFullName());
            }
        });

        for (Employee employee : developers) {
            individualFix = individualFix + " " + employee.getFullName() + " - <b>" + String.valueOf(bugDataAccess.onlyMeFixed(employee).size() + "</b> ");
        }

        model.put("totalPending", bugDataAccess.assignedOnly().size());
        model.put("individualFixes", individualFix);

        String individualPending = "";
        for (Employee employee : developers) {
            individualPending = individualPending + " " + employee.getFullName() + " - <b>" + String.valueOf(bugDataAccess.onlyMeAssigned(employee).size() + "</b> ");
        }
        model.put("individualPending", individualPending);
        return "home";
    }

    @RequestMapping(value = "stick", method = GET)
    @ResponseBody
    public String stick(@RequestParam String description) {
        StickyNote stickyNote = new StickyNote();
        stickyNote.setDescription(description);
        stickyNote.setCreatedDate(new Date());
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Employee createdBy = employeeDataAccess.findByUsername(user.getUsername());

        stickyNote.setCreatedBy(createdBy);

        stickyNoteDataAccess.create(stickyNote);
        return SUCCESS;
    }

    @RequestMapping(value = "delete", method = GET)
    @ResponseBody
    public String delete(@RequestParam Integer id) {
        stickyNoteDataAccess.destroy(id);
        return SUCCESS;
    }
}
