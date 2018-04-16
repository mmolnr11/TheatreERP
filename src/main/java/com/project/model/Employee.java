package com.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@NamedQueries(
        {@NamedQuery(name = "Employee.getEmployeePositions",
                query = "SELECT DISTINCT position from Employee"
        ),@NamedQuery(name = "Employee.getAllEmployee",
                query = "SELECT firstName, secondName from Employee")
        })
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "second_name", nullable = false)
    private String secondName;
    @Column(nullable = false)
    private int age;
//    TODO event dont have date
    @JsonIgnore
    @ManyToMany(mappedBy = "employeesToEvent")
    private List<Event> eventsToEmployee;

    private String position;

    private float workingHours;

    public float getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(float workingHours) {
        float hours = getWorkingHours();
        hours += workingHours;
        this.workingHours = hours;
    }
    public void decreaseWorkingHours(float workingHours) {
        float hours = getWorkingHours();
        hours -= workingHours;
        this.workingHours = hours;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Employee() {
    }

    public Employee(String firstName, String secondName, int age, String position) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
        this.position = position;
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

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName(){
        return getFirstName()+ " " + getSecondName();
    }

    public List<Event> getEventsToEmployee() {
        return eventsToEmployee;
    }

    public void setEventsToEmployee(List<Event> eventsToEmployee) {
        this.eventsToEmployee = eventsToEmployee;
    }
}
