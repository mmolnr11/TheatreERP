package com.project.dao;

//import com.google.api.client.util.DateTime;
import com.project.model.Event;
import com.project.model.User;
import com.project.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.awt.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class EventDao {
    @Autowired
    EventRepository eventRepository;

    public Event findOne(Long id){
        Event liveShow = eventRepository.findOne(id);
        return liveShow;
    }
    public void deleteOne(Long id){
        eventRepository.delete(id);
    }

    public void saveLiveshow(Event event){
        eventRepository.save(event);
    }

//    public List<ThEvent> findAll() {
//        return eventRepository.findAll();
//    }
//    public List<ThEvent> eventsPerSameDay(String date){
//        String formattedDate = date.substring(0,9);
////    }
    public List<Event> findByDate(Date start, Date end){
        List<Event> events = eventRepository.findEventByEndDateTimeBetween(start, end);
        return events;
    }
}

