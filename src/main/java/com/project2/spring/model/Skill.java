package com.project2.spring.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Table(name = "competenza")
public class Skill implements Serializable {


    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Integer id;

    @NotEmpty
    @Column(name = "skill", length=30, nullable = false)
    private String name;

    public Skill(){}
    public Skill(Integer id){
        this.id = id;
    }

    public String toString(){
        return name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
