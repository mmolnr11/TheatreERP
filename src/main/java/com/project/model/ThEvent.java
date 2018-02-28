package com.project.model;




import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@MappedSuperclass
public class ThEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
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
//    private EventType eventType;

    public ThEvent() {
    }

    public ThEvent(String description, String titel, Date startDateTime, Date endDateTime, String location) {
        this.description = description;
        this.titel = titel;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.location = location;
//        this.eventType = eventType;
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


//    public EventType getEventType() {
//        return eventType;
//    }
//
//    public void setEventType(EventType eventType) {
//        this.eventType = eventType;
//    }
}
