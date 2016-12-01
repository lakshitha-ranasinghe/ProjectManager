/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nibm.repository;

import java.util.List;

/**
 *
 * @author Lakshitha
 * @param <T>
 */
public interface GenericDataAccess<T> {
    public void create(T instance);
    public void destroy(Integer id);
    public T findById(Integer id);
    public void update(T instance);
    public List<T> findAll();
}
