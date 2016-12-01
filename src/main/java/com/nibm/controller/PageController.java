/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nibm.controller;

import com.nibm.entity.Page;
import com.nibm.repository.PageDataAccess;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 *
 * @author Lakshitha
 */
@Controller
@RequestMapping(value = "/page")
public class PageController {

    @Autowired
    PageDataAccess pageDataAccess;

    @RequestMapping(value = "add", method = GET)
    public String addMenu(Map model) {
        return "addPage";
    }

    @RequestMapping(value = "add", method = POST)
    public String processAddMenu(Page page) {
        pageDataAccess.create(page);
        return "redirect:/";
    }
}
