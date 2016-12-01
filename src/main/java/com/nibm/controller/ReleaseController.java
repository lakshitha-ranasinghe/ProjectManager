/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nibm.controller;

import com.nibm.common.Enums.ReleaseType;
import com.nibm.entity.Bug;
import com.nibm.entity.SystemRelease;
import com.nibm.entity.Task;
import com.nibm.repository.BugDataAccess;
import com.nibm.repository.ReleaseDataAccess;
import com.nibm.repository.TaskDataAccess;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value = "/release")
public class ReleaseController {

    @Autowired
    BugDataAccess bugDataAccess;

    @Autowired
    TaskDataAccess taskDataAccess;

    @Autowired
    ReleaseDataAccess releaseDataAccess;

    @RequestMapping(value = "newRelease", method = GET)
    public String release(Map model) {
        List<Bug> bugs = bugDataAccess.resolvedOnly();
        List<Task> tasks = taskDataAccess.completedOnly();
        model.put("bugs", bugs);
        model.put("tasks", tasks);
        return "release";
    }

    @RequestMapping(value = "processRelease", method = GET)
    @ResponseBody
    public String processRelease(@RequestParam(value = "bugList[]") Integer[] bugList,
            @RequestParam(value = "taskList[]") Integer[] taskList) {

        int noOfBugs = 0;
        int noOfTasks = 0;

        SystemRelease release = new SystemRelease();

        if (taskList[0] == 0) {
            release.setTasks(0);
        } else {
            for (Integer id : taskList) {
                taskDataAccess.releaseTask(id);
            }
            release.setTasks(taskList.length);
            noOfTasks = taskList.length;
        }

        if (bugList[0] == 0) {
            release.setBugFixes(0);
        } else {
            for (Integer id : bugList) {
                bugDataAccess.releaseBugFix(id);
            }
            release.setBugFixes(bugList.length);
            noOfBugs = bugList.length;
        }

        release.setDateOfRelease(new Date());
        release.setReleaseType(ReleaseType.Test);
        releaseDataAccess.create(release);
        return noOfBugs + "@" + noOfTasks;
    }

    @RequestMapping(value = "liveRelease", method = GET)
    public String liveRelease(Map model) {
        List<Bug> closedBugs = bugDataAccess.closedOnly();
        List<Task> closedTasks = taskDataAccess.releasedOnly();
        model.put("bugs", closedBugs);
        model.put("tasks", closedTasks);
        return "liveRelease";
    }

    @RequestMapping(value = "processLiveRelease", method = GET)
    @ResponseBody
    public String processLiveRelease(@RequestParam(value = "bugList[]") Integer[] bugList,
            @RequestParam(value = "taskList[]") Integer[] taskList) {

        int noOfBugs = 0;
        int noOfTasks = 0;

        SystemRelease release = new SystemRelease();

        if (taskList[0] == 0) {
            release.setTasks(0);
        } else {
            for (Integer id : taskList) {
                taskDataAccess.liveReleaseTask(id);
            }
            release.setTasks(taskList.length);
            noOfTasks = taskList.length;
        }

        if (bugList[0] == 0) {
            release.setBugFixes(0);
        } else {
            for (Integer id : bugList) {
                bugDataAccess.liveReleaseBugFix(id);
            }
            release.setBugFixes(bugList.length);
            noOfBugs = bugList.length;
        }

        release.setDateOfRelease(new Date());
        release.setReleaseType(ReleaseType.Live);

        releaseDataAccess.create(release);
        return noOfBugs + "@" + noOfTasks;
    }
}
