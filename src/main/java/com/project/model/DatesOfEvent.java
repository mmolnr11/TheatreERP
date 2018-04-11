package com.project.model;

import javax.persistence.ManyToOne;
import java.util.Date;

public class DatesOfEvent {
    private Date startDate, endDate;
    @ManyToOne
    private Event event;

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
}
