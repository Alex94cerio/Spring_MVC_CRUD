package com.project2.spring.controller;


import com.project2.spring.model.User;
import com.project2.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AppController {

    private UserService userService;

    private final MessageSource messageSource;

    public AppController( MessageSource messageSource ) {
        this.messageSource = messageSource;
    }

    @RequestMapping(value = {"/", "/Home"}, method = RequestMethod.GET)
    public String listUser(ModelMap model){
        List<User> users = userService.findAllUsers();
        model.addAttribute("userList", users);
        return "home";
    }


    @RequestMapping(value = {"/", "/Home"}, method = RequestMethod.POST)
    public String listUserWithFilter(@RequestParam("filterType") String filterType,
                                     @RequestParam("filterText") String filterText,
                                     ModelMap model){
        List<User> users = userService.searchUsers(filterType, filterText);
        model.addAttribute("filterType", filterType);
        model.addAttribute("filterText", filterText);
        model.addAttribute("userList", users);
        return "home";
    }

    /**
     * This method will delete an user by it's Id value.
     */
    @RequestMapping(value = { "/delete-user-{userId}" }, method = RequestMethod.GET)
    public String deleteUser(@PathVariable Integer userId) {
        userService.deleteUserById(userId);
        return "redirect:/Home";
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
