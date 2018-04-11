package com.project.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class DatesOfEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date startDate, endDate;
    @ManyToOne
    private Event event;

    public DatesOfEvent(Event event, Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.event = event;
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
}
