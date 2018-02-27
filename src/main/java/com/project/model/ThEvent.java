package com.project.model;




import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

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
//    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    private LocalDateTime startDateTime;
    @Column
//    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    private LocalDateTime endDateTime;
    @Column
    private String location;
//    private EventType eventType;

    public ThEvent() {
    }

    public ThEvent(String description, String titel, LocalDateTime startDateTime, LocalDateTime endDateTime, String location) {
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

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
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
