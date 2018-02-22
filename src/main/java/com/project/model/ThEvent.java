package com.project.model;

import javax.persistence.*;


public class ThEvent {

    private int id;
    private String description;
    private String titel;
    private String startDate;
    private String endDate;
    private String location;
//    private EventType eventType;

    public ThEvent() {
    }

    public ThEvent(String description, String titel, String startDate, String endDate, String location) {
        this.description = description;
        this.titel = titel;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
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
