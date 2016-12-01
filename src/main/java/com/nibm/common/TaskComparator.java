/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nibm.common;

import com.nibm.entity.Task;
import java.util.Comparator;

/**
 *
 * @author Lakshitha
 */
public class TaskComparator implements Comparator<Task>{

    @Override
    public int compare(Task firstTask, Task secondTask) {
        return secondTask.getId().compareTo(firstTask.getId());
    }
    
}
