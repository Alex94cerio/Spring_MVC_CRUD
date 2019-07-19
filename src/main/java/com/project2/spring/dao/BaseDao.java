package com.project2.spring.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<PK extends Serializable, T> {

    T findById(PK id);
    List<T> findAll();
    void save(T elem);
    void deleteById(PK key);


}
