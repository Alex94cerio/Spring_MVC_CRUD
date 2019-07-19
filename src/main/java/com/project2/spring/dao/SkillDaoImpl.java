package com.project2.spring.dao;

import com.project2.spring.model.Skill;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("skillDao")
public class SkillDaoImpl extends AbstractDao<Integer, Skill> {

}
