package com.project2.spring.model;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "utente",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"firstname", "lastname"})})
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Integer id;

    @NotEmpty
    @Column(length=30, nullable = false)
    private String firstname;

    @NotEmpty
    @Column(length=30, nullable = false)
    private String lastname;

    @Column(length=30)
    private String countryname;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past
    @NotNull
    @Column(nullable = false)
    private Date birthdate;

    @ManyToOne
    @JoinColumn( name = "role")
    private Role role;

    @ManyToOne
    @JoinColumn( name = "maritalStatus")
    private MaritalStatus maritalStatus;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "competenze_utenti",
            joinColumns = { @JoinColumn(name = "id_utente") },
            inverseJoinColumns = { @JoinColumn(name = "id_competenza") }
    )
    private List<Skill> skills;


    @Column (length = 100, name = "file_name")
    private String fileName;

    public User(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCountryname() {
        return countryname;
    }

    public void setCountryname(String countryname) {
        this.countryname = countryname;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}