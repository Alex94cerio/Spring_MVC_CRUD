package com.project2.spring.converter;

import com.project2.spring.model.Skill;
import com.project2.spring.service.SimpleAttributeService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SkillIdToSkillConverter implements Converter<Object, Skill> {


    private final SimpleAttributeService<Skill> skillService;

    public SkillIdToSkillConverter(SimpleAttributeService<Skill> skillService) {
        this.skillService = skillService;
    }

    public Skill convert(Object element) {
        Integer id = Integer.parseInt((String)element);
        Skill skill = new Skill();
        skill.setId(id);
        return skill;
    }
}
