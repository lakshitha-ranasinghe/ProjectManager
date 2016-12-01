/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nibm.controller;

import com.nibm.common.Enums.MenuType;
import com.nibm.entity.MainArea;
import com.nibm.repository.GenericDataAccess;
import com.nibm.repository.MainAreaDataAccess;
import java.util.ArrayList;
import java.util.List;
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
@RequestMapping(value = "/menu")
public class MenuController {
    
    @Autowired
    MainAreaDataAccess mainAreaDataAccess;
    
    @RequestMapping(value = "add", method = GET)
    public String addMenu(Map model) {
        List<MenuType> menuTypes = new ArrayList<MenuType>();
        menuTypes.add(MenuType.Primary);
        menuTypes.add(MenuType.Secondary);
        menuTypes.add(MenuType.Ternary);
        model.put("menuTypes", menuTypes);
        return "addMenu";
    }

    @RequestMapping(value = "manage", method = GET)
    public String manage(Map model) {
        List<MainArea> areas = mainAreaDataAccess.findAll();
        model.put("mainAreas", areas);
        return "manageMenu";
    }

    @RequestMapping(value = "add", method = POST)
    public String processAddMenu(MainArea area) {
        mainAreaDataAccess.create(area);
        return "redirect:/";
    }
}
