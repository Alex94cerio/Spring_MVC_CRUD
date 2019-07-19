package com.project2.spring.service;

import com.project2.spring.dao.BaseDao;
import com.project2.spring.model.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("skillService")
@Transactional
public class SkillServiceImpl implements SimpleAttributeService<Skill> {


    private BaseDao<Integer, Skill> dao;

    public List<Skill> findAll() { return dao.findAll(); }

    @Autowired
    public void setDao(BaseDao<Integer, Skill> dao) {
        this.dao = dao;
    }
}
