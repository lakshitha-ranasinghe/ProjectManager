/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nibm.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 *
 * @author Lakshitha
 */
@ControllerAdvice
@EnableWebMvc
public class ExceptionController {

    @ExceptionHandler(Throwable.class)
    public ModelAndView handleException(Throwable t) {
        ModelAndView mnv = new ModelAndView("error", "errorMessage", t.getMessage());
        System.out.println("Message : " + t.getMessage());
        return mnv;
    }
}
