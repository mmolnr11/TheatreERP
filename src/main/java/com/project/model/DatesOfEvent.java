package com.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.*;


//@NamedQuery(name = "DatesOfEvent.getNameQuery",
//        query = "SELECT startDate, endDate " +
//                "from DatesOfEvent d WHERE d.startDate >:startDate and d.endDate <:endDate"
//)



@Entity
public class DatesOfEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "date_id")
    private Long id;
    private Date startDate, endDate;
    @JsonIgnore
    @ManyToOne
    private Event event;
    @JsonIgnore
    @OneToMany(mappedBy = "datesOfEvent")
    private List<Comment> comments;


    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST},fetch = FetchType.EAGER)
    @JoinTable(name="EMPLOYEEASSIGNEMENT_TO_DATE", joinColumns={@JoinColumn(name="DATE", referencedColumnName="date_id")},
    inverseJoinColumns={@JoinColumn(name="EMPLOYEE", referencedColumnName="employee_id")})
    private List<Employee> employeesOfDates = new ArrayList<>();



    public DatesOfEvent(Event event, Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.event = event;
    }
    public DatesOfEvent(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public DatesOfEvent() {
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Event getEvent() {
        return event;
    }

    public Long getId() {
        return id;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Employee> getEmployeesOfDates() {
        return employeesOfDates;
    }

    public void setEmployeesOfDates(List<Employee> employeesOfDates) {
        this.employeesOfDates = employeesOfDates;
    }

    public void addEmployee(Employee employee){
        this.employeesOfDates.add(employee);
        employee.getDatesToEmployee().add(this);
    }

    //    public void setNumbersOfEmployeeByPosition(Map<String, Integer> map) {
//
////        for (String key : map.keySet()) {
//////            System.out.println("keyset "+key);
////            ArrayList<Employee> employees = new ArrayList<>();
////            Berendezo berendezo = new Berendezo("Berendezo", "ELek",32, "Berendezo");
////            employees.add(berendezo);
////
////            this.numbersOfEmployeeByPosition.put(key, employees);
//////        }
////
////        }
//
//    }
}
