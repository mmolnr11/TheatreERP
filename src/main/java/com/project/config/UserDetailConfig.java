package com.project.config;

import com.project.model.User;
import com.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.Role;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

@Service
public class UserDetailConfig implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    @Transactional(readOnly = true) // TODO add here a comment what this does
    public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {
       try {
           User user = userRepository.getUserByEmailAddress(emailAddress);
           if(user == null){
               System.out.println("user not found with the provided email");
               return null;
           }
           return new org.springframework.security.core.userdetails.User(user.getEmailAddress(), user.getPassword(), getAuthorities(user));
       }catch (Exception e){
           throw new UsernameNotFoundException("Email not found ");
       }
    }


    private Set<GrantedAuthority> getAuthorities(User user) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getRole());
        authorities.add(grantedAuthority);
        System.out.println("user authorities are " + authorities.toString());
        return authorities;
    }
}
