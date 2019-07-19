package com.project2.spring.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Table(name = "stato_civile")
public class MaritalStatus implements Serializable {


    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Integer id;

    @NotEmpty
    @Column(name = "maritalStatus", length=30, nullable = false)
    private String name;

    public MaritalStatus(){}

    public MaritalStatus(Integer id){
        this.id = id;
    }

    public String toString(){
        return name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
