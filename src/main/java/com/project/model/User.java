package com.project.model;

import javax.management.relation.Role;
import javax.persistence.*;

@Entity
@Table(name = "TheaterUser")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email_address",unique = true ,nullable = false)
    private String emailAddress;
    @Column(nullable = false)
    private String password;
    @Column (nullable = false)
    private String role;
    @Column()
    private String position;


    public User(String firstName, String lastName, String emailAddress, String password, String role, String position) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.password = password;
        if(role == "admin"){

            this.role = "ROLE_ADMIN";
        }else {
            this.role = "ROLE_USER";
        }
        this.position = position;
    }

    public User(String firstName, String lastName, String emailAddress, String password, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.password = password;
        if(role == "admin"){

            this.role = "ROLE_ADMIN";
        }else {
            this.role = "ROLE_USER";
        }
    }

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setRole(String role) {
        if (role == "admin"){
            this.role = "ROLE_ADMIN";
        }
        this.role = "ROLE_USER";
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
