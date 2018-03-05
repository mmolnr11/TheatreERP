package com.project.dao;

import com.project.model.Event;
import com.project.model.Role;
import com.project.model.User;
import com.project.repository.RoleRepository;
import com.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleDao {
    @Autowired
    RoleRepository roleRepository;

    public Role findOne(Long id){
        Role event = roleRepository.findOne(id);
        return event;
    }
    public List<Role> findAllRole(){return roleRepository.findAll();}
    public void saveRole(Role role){
        roleRepository.save(role);
    }
}
