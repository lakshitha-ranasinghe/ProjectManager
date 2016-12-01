/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nibm.controller;

import com.nibm.common.Enums;
import com.nibm.common.MenuComparator;
import com.nibm.common.ReleaseComparator;
import com.nibm.entity.Bug;
import com.nibm.entity.MainArea;
import com.nibm.entity.SystemRelease;
import com.nibm.entity.Task;
import com.nibm.repository.BugDataAccess;
import com.nibm.repository.MainAreaDataAccess;
import com.nibm.repository.ReleaseDataAccess;
import com.nibm.repository.TaskDataAccess;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Lakshitha
 */
@Controller
@RequestMapping(value = "/report")
public class ReportController {

    @Autowired
    ReleaseDataAccess releaseDataAccess;

    @Autowired
    BugDataAccess bugDataAccess;

    @Autowired
    TaskDataAccess taskDataAccess;

    @Autowired
    MainAreaDataAccess mainAreaDataAccess;

    @RequestMapping(value = "releaseReport", method = GET)
    public String releaseReport(Map model) {
        List<SystemRelease> releases = releaseDataAccess.findAll();
        Collections.sort(releases, new ReleaseComparator());
        model.put("releases", releases);
        return "releaseReport";
    }

    @RequestMapping(value = "processBugReleaseReport", method = GET)
    public String processBugReleaseReport(@RequestParam Integer id, HttpServletResponse response) throws JRException, IOException, Exception {
        List bugDetails = new ArrayList();
        SystemRelease systemRelease = releaseDataAccess.findById(id);
        List<Bug> bugs = null;
        if (systemRelease.getReleaseType() == Enums.ReleaseType.Test) {
            bugs = bugDataAccess.findByReleaseDataAndType(systemRelease.getDateOfRelease(), Enums.BugStatus.Released);
        } else {
            bugs = bugDataAccess.findByReleaseDataAndType(systemRelease.getDateOfRelease(), Enums.BugStatus.Live_Released);
        }
        for (Bug bug : bugs) {
            Map<String, String> obj = new HashMap<String, String>();
            obj.put("ID", bug.getId().toString());
            obj.put("Description", bug.getDescription());
            if (bug.getReleasedDate() != null) {
                obj.put("TestReleaseDate", bug.getReleasedDate().toString());
            } else {
                throw new Exception("Test Release Date Not Found for Bug ID " + bug.getId());
            }
            if (bug.getLiveReleasedData() != null) {
                obj.put("LiveReleaseDate", bug.getLiveReleasedData().toString());
            } else {
                obj.put("LiveReleaseDate", "-");
            }
            bugDetails.add(obj);
        }

        Map reportData = new HashMap();
        reportData.put("Title", "Bug Report");
        JasperReport jr = JasperCompileManager.compileReport("/var/lib/openshift/576a2bf62d52714e4900006a/app-root/runtime/repo/src/main/java/com/nibm/report/BugReport.jrxml");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jr, reportData, new JRMapCollectionDataSource(bugDetails));
        response.setContentType("application/pdf");
        response.setHeader("Content-disposition", "inline; filename=BugReport.pdf");
        OutputStream outStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
        return "releaseReport";
    }

    @RequestMapping(value = "processTaskReleaseReport", method = GET)
    public String processTaskeRleaseReport(@RequestParam Integer id, HttpServletResponse response) throws JRException, IOException, Exception {
        List taskDetails = new ArrayList();
        SystemRelease systemRelease = releaseDataAccess.findById(id);
        List<Task> tasks = null;
        if (systemRelease.getReleaseType() == Enums.ReleaseType.Test) {
            tasks = taskDataAccess.findByReleaseDataAndType(systemRelease.getDateOfRelease(), Enums.TaskStatus.Released);
        } else {
            tasks = taskDataAccess.findByReleaseDataAndType(systemRelease.getDateOfRelease(), Enums.TaskStatus.Live_Released);
        }
        for (Task task : tasks) {
            Map<String, String> obj = new HashMap<String, String>();
            obj.put("ID", task.getId().toString());
            obj.put("Description", task.getDescription());
            if (task.getReleasedDate() != null) {
                obj.put("TestReleaseDate", task.getReleasedDate().toString());
            } else {
                throw new Exception("Test Release Date Not Found for Task ID " + task.getId());
            }
            if (task.getLiveReleasedDate() != null) {
                obj.put("LiveReleaseDate", task.getLiveReleasedDate().toString());
            } else {
                obj.put("LiveReleaseDate", "-");
            }
            taskDetails.add(obj);
        }

        Map reportData = new HashMap();
        reportData.put("Title", "Task Report");
        JasperReport jr = JasperCompileManager.compileReport("C:\\Users\\Lakshitha\\Desktop\\OpenShift\\bugmanager\\src\\main\\java\\com\\nibm\\report\\BugReport.jrxml");
//        JasperReport jr = JasperCompileManager.compileReport("/var/lib/openshift/576a2bf62d52714e4900006a/app-root/runtime/repo/src/main/java/com/nibm/report/BugReport.jrxml");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jr, reportData, new JRMapCollectionDataSource(taskDetails));
        response.setContentType("application/pdf");
        response.setHeader("Content-disposition", "inline; filename=TaskReport.pdf");
        OutputStream outStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
        return "releaseReport";
    }

    @RequestMapping(value = "processOverallReleaseReport", method = GET)
    public String processOverallRleaseReport(@RequestParam Integer id, HttpServletResponse response) throws JRException, IOException, Exception {
        List reportRecords = new ArrayList();
        SystemRelease systemRelease = releaseDataAccess.findById(id);
        List<Task> tasks = null;
        if (systemRelease.getReleaseType() == Enums.ReleaseType.Test) {
            tasks = taskDataAccess.findByReleaseDataAndType(systemRelease.getDateOfRelease(), Enums.TaskStatus.Released);
        } else {
            tasks = taskDataAccess.findByReleaseDataAndType(systemRelease.getDateOfRelease(), Enums.TaskStatus.Live_Released);
        }
        for (Task task : tasks) {
            Map<String, String> obj = new HashMap<String, String>();
            obj.put("ID", task.getId().toString());
            obj.put("Type", "Task");
            obj.put("Description", task.getDescription());
            obj.put("Title", task.getTitle());
            obj.put("ReportedBy", task.getAssignedBy().getFirstName());
            obj.put("ReportedDate", task.getAssignedDate().toString());
            obj.put("ResolvedBy", task.getAssignedTo().getFirstName());
            reportRecords.add(obj);
        }

        List<Bug> bugs = null;
        if (systemRelease.getReleaseType() == Enums.ReleaseType.Test) {
            bugs = bugDataAccess.findByReleaseDataAndType(systemRelease.getDateOfRelease(), Enums.BugStatus.Released);
        } else {
            bugs = bugDataAccess.findByReleaseDataAndType(systemRelease.getDateOfRelease(), Enums.BugStatus.Live_Released);
        }
        for (Bug bug : bugs) {
            Map<String, String> obj = new HashMap<String, String>();
            obj.put("ID", bug.getId().toString());
            obj.put("Type", "Bug");
            obj.put("Description", bug.getDescription());
            obj.put("Title", bug.getTitle());
            obj.put("ReportedBy", bug.getReportedBy().getFirstName());
            obj.put("ReportedDate", bug.getAssignedDate().toString());
            obj.put("ResolvedBy", bug.getAssignedTo().getFirstName());
            reportRecords.add(obj);
        }

        Map reportData = new HashMap();
        reportData.put("Title", "Release Report");
        reportData.put("ResolvedBugs", String.valueOf(bugs.size()));
        reportData.put("CompletedTasks", String.valueOf(tasks.size()));
        reportData.put("TestReleasedDate", bugs.get(1).getReleasedDate().toString());
        reportData.put("LiveReleasedDate", bugs.get(1).getLiveReleasedData() == null ? "-" : bugs.get(1).getLiveReleasedData().toString());
//        JasperReport jr = JasperCompileManager.compileReport("C:\\Users\\Lakshitha\\Desktop\\OpenShift\\bugmanager\\src\\main\\java\\com\\nibm\\report\\ReleaseReport.jrxml");
        JasperReport jr = JasperCompileManager.compileReport("/var/lib/openshift/576a2bf62d52714e4900006a/app-root/runtime/repo/src/main/java/com/nibm/report/ReleaseReport.jrxml");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jr, reportData, new JRMapCollectionDataSource(reportRecords));
        response.setContentType("application/pdf");
        response.setHeader("Content-disposition", "inline; filename=TaskReport.pdf");
        OutputStream outStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
        return "releaseReport";
    }

    @RequestMapping(value = "customReport", method = GET)
    public String customReport(Map model) {
        List<MainArea> mainAreas = mainAreaDataAccess.findAll();
        List<MainArea> primaryArea = new ArrayList<MainArea>();
        List<MainArea> secondaryArea = new ArrayList<MainArea>();
        List<MainArea> terneryArea = new ArrayList<MainArea>();

        for (MainArea area : mainAreas) {
            if (area.getMenuType() == Enums.MenuType.Primary) {
                primaryArea.add(area);
            } else if (area.getMenuType() == Enums.MenuType.Secondary) {
                secondaryArea.add(area);
            } else {
                terneryArea.add(area);
            }
        }
        
        MainArea mainArea = new MainArea();
        mainArea.setId(0);
        mainArea.setAreaName("-");
        secondaryArea.add(mainArea);

        Collections.sort(primaryArea, new MenuComparator());
        Collections.sort(secondaryArea, new MenuComparator());
        Collections.sort(terneryArea, new MenuComparator());

        model.put("primaryMenu", primaryArea);
        model.put("secondaryMenu", secondaryArea);
        model.put("terneryMenu", terneryArea);
        
        return "customReport";
    }
    
    @RequestMapping(value = "processCustomReport", method = POST)
    public String processCustomReport(String primaryMenu, String secondaryMenu, String terneryMenu, HttpServletResponse response) throws JRException, IOException, Exception {
        List reportRecords = new ArrayList();
        List<Bug> bugs = null;
        
        if(secondaryMenu.equals("-")){
            bugs = bugDataAccess.findByPrimaryMenuOnly(primaryMenu);
        }
        else if(terneryMenu.equals("-")){
            
        }
        
        for (Bug bug : bugs) {
            Map<String, String> obj = new HashMap<String, String>();
            obj.put("ID", bug.getId().toString());
            obj.put("Type", "Bug");
            obj.put("Description", bug.getDescription());
            obj.put("Title", bug.getTitle());
            obj.put("ReportedBy", bug.getReportedBy().getFirstName());
            obj.put("ReportedDate", bug.getAssignedDate().toString());
            obj.put("ResolvedBy", bug.getAssignedTo().getFirstName());
            reportRecords.add(obj);
        }

        Map reportData = new HashMap();
        reportData.put("Title", "Custom Report");
        reportData.put("ResolvedBugs", "-");
        reportData.put("CompletedTasks", "-");
        reportData.put("TestReleasedDate", "-");
        reportData.put("LiveReleasedDate", "-");
//        JasperReport jr = JasperCompileManager.compileReport("C:\\Users\\Lakshitha\\Desktop\\OpenShift\\bugmanager\\src\\main\\java\\com\\nibm\\report\\ReleaseReport.jrxml");
        JasperReport jr = JasperCompileManager.compileReport("/var/lib/openshift/576a2bf62d52714e4900006a/app-root/runtime/repo/src/main/java/com/nibm/report/ReleaseReport.jrxml");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jr, reportData, new JRMapCollectionDataSource(reportRecords));
        response.setContentType("application/pdf");
        response.setHeader("Content-disposition", "inline; filename=TaskReport.pdf");
        OutputStream outStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
        return "customReport";
    }
}
