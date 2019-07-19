package com.project2.spring.controller;

import com.project2.spring.model.MaritalStatus;
import com.project2.spring.model.Role;
import com.project2.spring.model.Skill;
import com.project2.spring.model.User;
import com.project2.spring.service.SimpleAttributeService;
import com.project2.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;


@Controller
public class EditCreateController {

    private UserService userService;

    private SimpleAttributeService<Skill> skillService;

    private SimpleAttributeService<Role> roleService;

    private SimpleAttributeService<MaritalStatus> maritalStatusService;

    private final MessageSource messageSource;

    public EditCreateController( MessageSource messageSource ) {
        this.messageSource = messageSource;
    }



    /**
     * This method will provide the medium to add OR edit a new user.
     */
    @RequestMapping(value = { "/newuser","/edit-user-{userId}" }, method = RequestMethod.GET)
    public String newUser(@PathVariable(required = false) Integer userId, ModelMap model) {
        User user = new User();
        if(userId != null) {
            user = userService.findById(userId);
        }
        model.addAttribute("user", user);
        if(userId != null) {
            return "edit";
        }
        return "registration";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * saving user in database. It also validates the user input
     */
    @RequestMapping(value = { "/newuser" }, method = RequestMethod.POST)
    public String saveUser(@Valid User user, BindingResult result,
                           ModelMap model, @RequestParam("file")MultipartFile file) throws IOException {

        if (result.hasErrors()) {
            return "registration";
        }
        userService.saveUser(user , file);
        model.addAttribute("success", "User " + user.getFirstname() + " "+ user.getLastname() + " registered successfully");
        //return "success";
        return "registrationsuccess";
    }

    @RequestMapping(value = { "/edit-user-{userId}" }, method = RequestMethod.POST)
    public String updateUser(@Valid User user, BindingResult result,
                             ModelMap model, @RequestParam("file")MultipartFile file) throws IOException{
        if (result.hasErrors()) {
            return "registration";
        }
        userService.updateUser(user, file);
        model.addAttribute("success", "User " + user.getFirstname() + " "+ user.getLastname() + " updated successfully");
        return "registrationsuccess";
    }

    @ModelAttribute("skills")
    public List<Skill> initalizeSkills(){
        return skillService.findAll();
    }

    @ModelAttribute("roles")
    public List<Role> initalizeRoles(){
        return roleService.findAll();
    }

    @ModelAttribute("maritalStatuses")
    public List<MaritalStatus> initalizeMaritalStatuses(){
        return maritalStatusService.findAll();
    }

    @Autowired
    public void setSkillService(@Qualifier("skillService") SimpleAttributeService<Skill> skillService) {
        this.skillService = skillService;
    }

    @Autowired
    public void setRoleService(@Qualifier("roleService") SimpleAttributeService<Role> roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setMaritalStatusService(@Qualifier("maritalStatusService") SimpleAttributeService<MaritalStatus> maritalStatusService) {
        this.maritalStatusService = maritalStatusService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}


