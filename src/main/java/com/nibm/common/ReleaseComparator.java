/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nibm.common;

import com.nibm.entity.SystemRelease;
import java.util.Comparator;

/**
 *
 * @author Lakshitha
 */

public class ReleaseComparator implements Comparator<SystemRelease>{

    @Override
    public int compare(SystemRelease firstRelease, SystemRelease secondRelease) {
        return secondRelease.getDateOfRelease().compareTo(firstRelease.getDateOfRelease());
    }
    
}