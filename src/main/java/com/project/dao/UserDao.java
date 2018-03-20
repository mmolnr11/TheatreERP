package com.project.dao;


import com.project.model.User;
import com.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class UserDao {

    @Autowired
    UserRepository userRepository;

    public void saveUser(User user){
        userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findOne(id);
    }

    public User createUser(User user){
        User newUser = new User();
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmailAddress(user.getEmailAddress());
        newUser.setRole(user.getRole());
        newUser.setPosition(user.getPosition());
        newUser.setPassword(user.getPassword());
        return newUser;
    }
    public User updateUser(HashMap<String, String> params, Long id){
        User user = findById(id);
        user.setFirstName(params.get("firstname"));
        user.setLastName(params.get("secondname"));
        user.setEmailAddress(params.get("email"));
        user.setPassword(params.get("password"));
        user.setRole(params.get("role"));
        user.setPosition(params.get("position"));

        return user;
    }
    public User getUserByEmailAddress(String email){

        return userRepository.getUserByEmailAddress(email);
    };

}
