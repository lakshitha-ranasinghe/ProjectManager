/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nibm.controller;

import com.nibm.common.BugComparator;
import com.nibm.common.Enums;
import com.nibm.common.Enums.BugStatus;
import com.nibm.common.Enums.MenuType;
import com.nibm.common.InactiveEmployee;
import com.nibm.common.MenuComparator;
import com.nibm.entity.Bug;
import com.nibm.entity.Employee;
import com.nibm.entity.MainArea;
import com.nibm.entity.Page;
import com.nibm.repository.BugDataAccess;
import com.nibm.repository.EmployeeDataAccess;
import com.nibm.repository.MainAreaDataAccess;
import com.nibm.repository.PageDataAccess;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Lakshitha
 */
@Controller
@RequestMapping(value = "/bug")
public class BugController {

    @Autowired
    MainAreaDataAccess mainAreaDataAccess;

    @Autowired
    EmployeeDataAccess employeeDataAccess;

    @Autowired
    BugDataAccess bugDataAccess;

    @Autowired
    ServletContext context;
    
    @Autowired
    PageDataAccess pageDataAccess;

    private final static String SUCCESS = "success";
    private final static String ERROR = "error";

    @RequestMapping(value = "add", method = GET)
    public String addBug(Map model) {
        List<MainArea> mainAreas = mainAreaDataAccess.findAll();
        List<MainArea> primaryArea = new ArrayList<MainArea>();
        List<MainArea> secondaryArea = new ArrayList<MainArea>();
        List<MainArea> terneryArea = new ArrayList<MainArea>();

        for (MainArea area : mainAreas) {
            if (area.getMenuType() == MenuType.Primary) {
                primaryArea.add(area);
            } else if (area.getMenuType() == MenuType.Secondary) {
                secondaryArea.add(area);
            } else {
                terneryArea.add(area);
            }
        }

        Collections.sort(primaryArea, new MenuComparator());
        Collections.sort(secondaryArea, new MenuComparator());
        Collections.sort(terneryArea, new MenuComparator());

        model.put("primaryMenu", primaryArea);
        model.put("secondaryMenu", secondaryArea);
        model.put("terneryMenu", terneryArea);

        List<Employee> employees = employeeDataAccess.findDevelopers();
        for (Employee employee : InactiveEmployee.getInactiveEmployees()) {
            employees.remove(employee);
        }
        model.put("assignedTo", employees);
        
        List<Page> pages = pageDataAccess.findAll();
        model.put("pagesToAssign", pages);
        
        return "addBug";
    }

    @RequestMapping(value = "add", method = POST)
    public String processAddBug(@RequestPart("bugImage") byte[] bugImage, Bug bug, Integer assignedEmployee, Integer pageId) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Employee reportedBy = employeeDataAccess.findByUsername(user.getUsername());
        
        Page page = pageDataAccess.findById(pageId);

        bug.setAssignedDate(new Date());
        bug.setStatus(Enums.BugStatus.Assigned);
        bug.setReportedBy(reportedBy);
        Employee assingnedTo = employeeDataAccess.findById(assignedEmployee);
        bug.setAssignedTo(assingnedTo);
        bug.setImage(bugImage);
        bug.setPage(page);

        bugDataAccess.create(bug);

        return "redirect:/bug/viewAll";
    }

    @RequestMapping(value = "viewFirst", method = GET)
    public String viewFirst(HttpServletRequest request, Map model) {
        List<Bug> bugs = bugDataAccess.findFirst(100);
        Collections.sort(bugs, new BugComparator());
        request.getSession().removeAttribute("statusChoice");
        model.put("bugs", bugs);
        //model.put("bugs", new ArrayList<Bug>(bugs.subList(0, 20)));
        return "viewAllBugs";
    }
    
    @RequestMapping(value = "viewAll", method = GET)
    public String viewAll(HttpServletRequest request, Map model) {
        List<Bug> bugs = bugDataAccess.findAll();
        Collections.sort(bugs, new BugComparator());
        request.getSession().removeAttribute("statusChoice");
        model.put("bugs", bugs);
        //model.put("bugs", new ArrayList<Bug>(bugs.subList(0, 20)));
        return "viewAllBugs";
    }

    @RequestMapping(value = "search", method = GET)
    public String search(Map model, String keyword) {
        List<Bug> bugs = bugDataAccess.findByTitle(keyword);
        Collections.sort(bugs, new BugComparator());
        model.put("bugs", bugs);
        return "viewAllBugs";
    }

    @RequestMapping(value = "info", method = GET)
    @ResponseBody
    public String info(@RequestParam Integer id) {
        Bug bug = bugDataAccess.findById(id);
        String data = bug.getReportedBy().getFullName() + "@" + bug.getAssignedDate() + "@";
        String resolvedDate = bug.getResolvedDate() == null ? "Not Resolved" : bug.getResolvedDate().toString();
        String releasedDate = bug.getReleasedDate() == null ? "Not Released" : bug.getReleasedDate().toString();
        String closedDate = bug.getClosedDate() == null ? "Not Closed" : bug.getClosedDate().toString();
        data = data + resolvedDate + "@" + releasedDate + "@" + closedDate;

        String env = System.getenv("OPENSHIFT_DATA_DIR");
        String imagePath = "/var/lib/openshift/576a2bf62d52714e4900006a/app-root/runtime/repo/src/main/webapp/external/image/" + bug.getId() + ".png";

        if (bug.getImage().length > 1) {
            InputStream in = new ByteArrayInputStream(bug.getImage());
            BufferedImage imageFile;
            try {
                imageFile = ImageIO.read(in);
                ImageIO.write(imageFile, "png", new File(imagePath));
            } catch (IOException ex) {
                Logger.getLogger(BugController.class.getName()).log(Level.SEVERE, null, ex);
            }
            data = data + "@" + "/external/image/" + bug.getId() + ".png";
            System.out.println("Multi Data " + data);
        } else {
            data = data + "@NoImage";
        }
        System.out.println("Real Path" + imagePath);
        System.out.println("Context Path " + data);
        return data;
    }

    @RequestMapping(value = "delete", method = GET)
    @ResponseBody
    public String delete(@RequestParam Integer id) throws Exception {
        bugDataAccess.destroy(id);
        return SUCCESS;
    }

    @RequestMapping(value = "resolve", method = GET)
    @ResponseBody
    public String resolve(@RequestParam Integer id) {
        bugDataAccess.resolveBug(id);
        return SUCCESS;
    }

    @RequestMapping(value = "release", method = GET)
    @ResponseBody
    public String release(@RequestParam Integer id) {
        bugDataAccess.releaseBugFix(id);
        return SUCCESS;
    }

    @RequestMapping(value = "close", method = GET)
    @ResponseBody
    public String close(@RequestParam Integer id) {
        System.out.println(id);
        bugDataAccess.closeBug(id);
        return SUCCESS;
    }

    @RequestMapping(value = "invalid", method = GET)
    @ResponseBody
    public String invalid(@RequestParam Integer id) {
        bugDataAccess.invalidBug(id);
        return SUCCESS;
    }

    @RequestMapping(value = "assign", method = GET)
    @ResponseBody
    public String assign(@RequestParam Integer id) {
        bugDataAccess.assignBug(id);
        return SUCCESS;
    }

    @RequestMapping(value = "assignedOnly", method = GET)
    public String assignedOnly(HttpServletRequest request, Map model) {
        request.getSession().setAttribute("statusChoice", BugStatus.Assigned);
        List<Bug> assignedBugs = bugDataAccess.assignedOnly();
        model.put("bugs", assignedBugs);
        return "viewAllBugs";
    }

    @RequestMapping(value = "resolvedOnly", method = GET)
    public String resolvedOnly(HttpServletRequest request, Map model) {
        request.getSession().setAttribute("statusChoice", BugStatus.Resolved);
        List<Bug> resolvedBugs = bugDataAccess.resolvedOnly();
        model.put("bugs", resolvedBugs);
        return "viewAllBugs";
    }

    @RequestMapping(value = "releasedOnly", method = GET)
    public String releasedOnly(HttpServletRequest request, Map model) {
        request.getSession().setAttribute("statusChoice", BugStatus.Released);
        List<Bug> releasedBugs = bugDataAccess.releasedOnly();
        model.put("bugs", releasedBugs);
        return "viewAllBugs";
    }

    @RequestMapping(value = "closedOnly", method = GET)
    public String closedOnly(HttpServletRequest request, Map model) {
        request.getSession().setAttribute("statusChoice", BugStatus.Closed);
        List<Bug> closedBugs = bugDataAccess.closedOnly();
        model.put("bugs", closedBugs);
        return "viewAllBugs";
    }

    @RequestMapping(value = "onlyMe", method = GET)
    public String onlyMe(HttpServletRequest request, Map model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Employee employee = employeeDataAccess.findByUsername(user.getUsername());
        List<Bug> onlyMeBugs = bugDataAccess.onlyMe(employee);
        BugStatus status = (BugStatus) request.getSession().getAttribute("statusChoice");
        request.getSession().setAttribute("userChoice", "onlyMe");
        if (status != null) {
            List<Bug> toRemove = new ArrayList<Bug>();
            for (Bug bug : onlyMeBugs) {
                if (bug.getStatus() != status) {
                    toRemove.add(bug);
                }
            }
            onlyMeBugs.removeAll(toRemove);
        }
        model.put("bugs", onlyMeBugs);
        return "viewAllBugs";
    }

    @RequestMapping(value = "allUsers", method = GET)
    public String allUsers(HttpServletRequest request, Map model) {
        List<Bug> allBugs = bugDataAccess.findAll();
        BugStatus status = (BugStatus) request.getSession().getAttribute("statusChoice");
        request.getSession().removeAttribute("userChoice");
        if (status != null) {
            List<Bug> toRemove = new ArrayList<Bug>();
            for (Bug bug : allBugs) {
                if (bug.getStatus() != status) {
                    toRemove.add(bug);
                }
            }
            allBugs.removeAll(toRemove);
        }
        model.put("bugs", allBugs);
        return "viewAllBugs";
    }
}
