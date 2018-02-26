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

    public User createUser(HashMap<String, String> user){
        User newUser = new User(user.get("firstname"),user.get("secondname"),user.get("email"),
                user.get("password"),user.get("role"), user.get("position"));
        return newUser;
    }
    public User updateUser(HashMap<String, String> params, Long id){
        User user = findById(id);
        user.setFirstName(params.get("firstname"));
        user.setSecondName(params.get("secondname"));
        user.setEmailAddress(params.get("email"));
        user.setPassword(params.get("password"));
        user.setRole(params.get("role"));
        user.setPosition(params.get("position"));

        return user;
    }

}
