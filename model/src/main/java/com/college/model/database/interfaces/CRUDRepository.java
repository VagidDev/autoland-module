/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.college.model.database.interfaces;

import java.util.List;

/**
 *
 * @author Vagid Zibliuc
 * @param <ID>
 * @param <T>
 */
public interface CRUDRepository<ID,T> {
    T getById(ID id);
    List<T> getAll();
    T save(T t);
    boolean update(T t);
    boolean delete(T t);
    boolean deleteByID(ID id);
}
