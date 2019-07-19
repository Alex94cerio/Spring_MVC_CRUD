package com.project2.spring.service;

import com.project2.spring.dao.BaseDao;
import com.project2.spring.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("roleService")
@Transactional
public class RoleServiceImpl implements SimpleAttributeService<Role> {


    private BaseDao<Integer, Role> dao;

    public List<Role> findAll() { return dao.findAll(); }

    @Autowired
    public void setDao(BaseDao<Integer, Role> dao) {
        this.dao = dao;
    }
}
