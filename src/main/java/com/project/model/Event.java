package com.project.model;




import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String description;
    @Column
    private String titel;
    @Column
    @Temporal(value= TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "EEE, d MMM yyyy HH:mm")
    private Date startDateTime;
    @Column
    @Temporal(value= TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "EEE, d MMM yyyy HH:mm")
    private Date endDateTime;
    @Column
    private String location;
    @Column
    private String type;
//    @Transient
    @ManyToMany
    private List<Employee> eventhezDolgozok;

    public Event() {
    }

    public Event(String description, String titel, Date startDateTime, Date endDateTime, String location, String type) {
        this.description = description;
        this.titel = titel;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.location = location;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public List<Employee> getEventhezDolgozok() {
        return eventhezDolgozok;
    }

    public void setEventhezDolgozok(List<Employee> eventhezDolgozok) {
        this.eventhezDolgozok = eventhezDolgozok;
    }
}
