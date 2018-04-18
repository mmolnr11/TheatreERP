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
    private Long id;
    private Date startDate, endDate;
    @JsonIgnore
    @ManyToOne
    private Event event;
    @JsonIgnore
    @OneToMany(mappedBy = "datesOfEvent")
    private List<Comment> comments;
    @ElementCollection
    @MapKeyColumn(name="name")
    @Column(name="value")
//    private Map<String, ArrayList<Employee>> numbersOfEmployeeByPosition ;
    private Map<String, EmployeeAssigment> numbersOfEmployeeByPosition ;


    public DatesOfEvent(Event event, Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.event = event;
        this.numbersOfEmployeeByPosition = new HashMap<>();


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

    public Map<String, EmployeeAssigment> getNumbersOfEmployeeByPosition() {
        return numbersOfEmployeeByPosition;
    }

    public void setNumbersOfEmployeeByPosition(Map<String, Integer> map) {

//        for (String key : map.keySet()) {
////            System.out.println("keyset "+key);
//            ArrayList<Employee> employees = new ArrayList<>();
//            Berendezo berendezo = new Berendezo("Berendezo", "ELek",32, "Berendezo");
//            employees.add(berendezo);
//
//            this.numbersOfEmployeeByPosition.put(key, employees);
////        }
//
//        }

    }
}
