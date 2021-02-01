package com.familytree.gs.service;

import java.util.List;


public interface GenericService<T> {
	
	T findById(long id);
     
    void save(T object);
     
    void update(T object);
     
    void deleteById(long id);
 
    List<T> findAll(); 
     
    void deleteAll();
     
    public boolean isExist(T user);
}
