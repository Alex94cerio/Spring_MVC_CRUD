package com.project2.spring.service;

import com.project2.spring.dao.BaseDao;
import com.project2.spring.model.MaritalStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("maritalStatusService")
@Transactional
public class MaritalStatusServiceImpl implements SimpleAttributeService<MaritalStatus> {


    private BaseDao<Integer, MaritalStatus> dao;

    public List<MaritalStatus> findAll() { return dao.findAll(); }

    @Autowired
    public void setDao(@Qualifier("maritalStatusDao") BaseDao<Integer, MaritalStatus> dao) {
        this.dao = dao;
    }
}
