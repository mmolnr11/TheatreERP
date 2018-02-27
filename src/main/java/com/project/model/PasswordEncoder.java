package com.project.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoder {
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public PasswordEncoder() {
        bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    public BCryptPasswordEncoder getbCryptPasswordEncoder() {
        return bCryptPasswordEncoder;
    }
}
