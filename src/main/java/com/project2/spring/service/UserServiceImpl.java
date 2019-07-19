package com.project2.spring.service;

import com.project2.spring.dao.BaseDao;
import com.project2.spring.dao.UserDaoImpl;
import com.project2.spring.model.User;
import com.sun.org.apache.xpath.internal.operations.Mult;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {


    @Value("${image.path}")
    private String imagePath;

    private UserDaoImpl dao;

    public User findById(Integer id) { return dao.findById(id);}


    public void saveUser(User user, MultipartFile file) throws IOException {
        saveFile(user, file);
        //Now do something with file...
        dao.save(user);
    }

    public void updateUser(User user , MultipartFile file) throws IOException {
        User entity = dao.findById(user.getId());
        saveFile(user, file);

        if (entity != null) {
            entity.setFirstname(user.getFirstname());
            entity.setLastname(user.getLastname());
            entity.setBirthdate(user.getBirthdate());
            entity.setCountryname(user.getCountryname());
            entity.setMaritalStatus(user.getMaritalStatus());
            entity.setRole(user.getRole());
            entity.setSkills(user.getSkills());
            entity.setFileName(user.getFileName());
        }
    }

    public void deleteUserById(Integer id) {
        dao.deleteById(id);
    }

    public List<User> findAllUsers() {
        return dao.findAll();
    }

    public List<User> searchUsers(String filterType, String filterText){
        return dao.searchUsers(filterType, filterText);
    }

    @Autowired
    public void setDao(@Qualifier("userDao") UserDaoImpl dao) {
        this.dao = dao;
    }

    private void saveFile(User user , MultipartFile image) throws IOException{
        if (!image.isEmpty()) {
            String contentType = image.getContentType();
            String[] parts = contentType.split("/");
            String fileName = user.getFirstname() + "-" + user.getLastname() + "-pic." + parts[1];
            FileCopyUtils.copy(image.getBytes(), new File(imagePath + fileName));
            user.setFileName(fileName);
        }
    }
}
