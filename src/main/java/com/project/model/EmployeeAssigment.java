package com.project.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class EmployeeAssigment {
    @Id
    @GeneratedValue
    private long id;
    private List<Employee> employeeList;
    @ManyToOne
    private DatesOfEvent datesOfEvent;

    public EmployeeAssigment(DatesOfEvent datesOfEvent, List<Employee> employeeList) {
        this.employeeList = employeeList;
        this.datesOfEvent = datesOfEvent;
    }

    public long getId() {
        return id;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public DatesOfEvent getDatesOfEvent() {
        return datesOfEvent;
    }

    public void setDatesOfEvent(DatesOfEvent datesOfEvent) {
        this.datesOfEvent = datesOfEvent;
    }
}
