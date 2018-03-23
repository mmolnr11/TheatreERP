package com.project.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    @ManyToOne
    private User user;
    @ManyToOne
    private Event event;
    @Temporal(value= TemporalType.TIMESTAMP)
    private Date entryTime;


    public Comment() {
    }

    public Comment(String description, User user, Event event) {
        this.description = description;
        this.user = user;
        this.event= event;
        this.entryTime = new Date();
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getEntryTime() {
        return entryTime;
    }
}
