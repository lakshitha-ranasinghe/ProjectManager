/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nibm.common;

import com.nibm.entity.MainArea;
import java.util.Comparator;

/**
 *
 * @author Lakshitha
 */
public class MenuComparator implements Comparator<MainArea>{

    @Override
    public int compare(MainArea firstMenu, MainArea secondMenu) {
        return firstMenu.getAreaName().compareTo(secondMenu.getAreaName());
    }
    
}
