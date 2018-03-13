package com.project.model;




import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String description;
    @Column
    private String title;
    @Column
    @Temporal(value= TemporalType.TIMESTAMP)
    private Date startDateTime;
    @Column
    @Temporal(value= TemporalType.TIMESTAMP)
    private Date endDateTime;
    @Column
    private String location;
    @Column
    private String type;
//    @ManyToOne
//    private List<Employee> employeeList;
    @ElementCollection
    @MapKeyColumn(name="name")
    @Column(name="value")
//    @CollectionTable(name="example_attributes", joinColumns=@JoinColumn(name="example_id"))
    private Map<String, Integer> eventhezDolgozok;

    public Event() {
    }

    public Event(String description, String title, Date startDateTime, Date endDateTime, String location, String type) {
        this.description = description;
        this.title = title;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Map<String, Integer> getEventhezDolgozok() {
        return eventhezDolgozok;
    }

    public void setEventhezDolgozok(HashMap<String, Integer> eventhezDolgozok) {
        this.eventhezDolgozok = eventhezDolgozok;
    }

//    public List<Employee> getEmployeeList() {
//        return employeeList;
//    }
//
//    public void setEmployeeList(List<Employee> employeeList) {
//        this.employeeList = employeeList;
//    }
}
