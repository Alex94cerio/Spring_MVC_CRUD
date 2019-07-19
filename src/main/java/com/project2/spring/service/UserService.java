package com.project2.spring.service;

import com.project2.spring.model.User;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService {

    User findById(Integer id);

    void saveUser(User user, MultipartFile image) throws IOException;

    void updateUser(User user, MultipartFile image) throws IOException;

    void deleteUserById(Integer id);

    List<User> findAllUsers();

    List<User> searchUsers(String filterType, String filterText);
}
