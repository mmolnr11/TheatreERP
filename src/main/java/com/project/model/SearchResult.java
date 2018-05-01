package com.project.model;


public class SearchResult {
    private Event event;
    private DatesOfEvent datesOfEvent;

    public SearchResult(Event event, DatesOfEvent datesOfEvent) {
        this.event = event;
        this.datesOfEvent = datesOfEvent;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public DatesOfEvent getDatesOfEvent() {
        return datesOfEvent;
    }

    public void setDatesOfEvent(DatesOfEvent datesOfEvent) {
        this.datesOfEvent = datesOfEvent;
    }
}
