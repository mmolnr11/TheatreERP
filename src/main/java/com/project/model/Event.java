package com.project.model;




import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String description;
    @Column
    private String title;
    @OneToMany
    DatesOfEvent datesOfEvent;
//    @Column
//    @Temporal(value= TemporalType.TIMESTAMP)
//    private Date startDateTime;
//    @Column
//    @Temporal(value= TemporalType.TIMESTAMP)
//    private Date endDateTime;
    @Column
    private String location;
    @Column
    private String type;
    @JsonIgnore
    @OneToMany(mappedBy = "event")
    private List<Comment> comments;
    @JsonIgnore
    @ManyToMany()
    private List<Employee> employeesToEvent;
    @ElementCollection
    @MapKeyColumn(name="name")
    @Column(name="value")
//    @CollectionTable(name="example_attributes", joinColumns=@JoinColumn(name="example_id"))
    private Map<String, Integer> employeesInNumbersToEvent;

    public Event() {
    }

    public Event(String description, String title, String location, String type) {
        this.description = description;
        this.title = title;
//        this.startDateTime = startDateTime;
//        this.endDateTime = endDateTime;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

//    public Date getStartDateTime() {
//        return startDateTime;
//    }
//
//    public void setStartDateTime(Date startDateTime) {
//        this.startDateTime = startDateTime;
//    }
//
//    public Date getEndDateTime() {
//        return endDateTime;
//    }
//
//    public void setEndDateTime(Date endDateTime) {
//        this.endDateTime = endDateTime;
//    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public Map<String, Integer> getEmployeesInNumbersToEvent() {
        return employeesInNumbersToEvent;
    }

    public void setEmployeesInNumbersToEvent(Map<String, Integer> employeesInNumbersToEvent) {
        this.employeesInNumbersToEvent = employeesInNumbersToEvent;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Employee> getEmployeesToEvent() {
        return employeesToEvent;
    }

    public void setEmployeesToEvent(List<Employee> employeesToEvent) {
        this.employeesToEvent = employeesToEvent;
    }

    public DatesOfEvent getDatesOfEvent() {
        return datesOfEvent;
    }

    public void setDatesOfEvent(DatesOfEvent datesOfEvent) {
        this.datesOfEvent = datesOfEvent;
    }

    public void getDurationOfEvent() {
//        Date startDate = this.getStartDateTime();
//        Date endDate = this.getEndDateTime();
//        long diffInMillies = Math.abs(endDate.getTime() - startDate.getTime());
//        long minutes = TimeUnit.MINUTES.convert(diffInMillies, TimeUnit.MILLISECONDS);
//        return minutes;
    }
}
