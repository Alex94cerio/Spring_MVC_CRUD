package com.project2.spring.service;

import java.util.List;

public interface SimpleAttributeService<T> {

    List<T> findAll();
}
