/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nibm.common;

import com.nibm.entity.Bug;
import java.util.Comparator;

/**
 *
 * @author Lakshitha
 */
public class BugComparator implements Comparator<Bug>{

    @Override
    public int compare(Bug firstBug, Bug secondBug) {
        return secondBug.getId().compareTo(firstBug.getId());
    }
    
}
