package com.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@NamedQuery(name = "DatesOfEvent.getNameQuery",
        query = "SELECT startDate, endDate " +
                "from DatesOfEvent d WHERE d.startDate >:startDate and d.endDate <:endDate"
)



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
//    TODO MANYTOMANY WITH EMP

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
}
