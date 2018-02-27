package com.project.dao;

//import com.google.api.client.util.DateTime;
import com.project.model.LiveShow;
import com.project.model.ThEvent;
import com.project.model.User;
import com.project.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.awt.*;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventDao {
    @Autowired
    EventRepository eventRepository;


    public void saveUser(LiveShow liveShow){
        eventRepository.save(liveShow);
    }

//    public List<ThEvent> findAll() {
//        return eventRepository.findAll();
//    }
//    public List<ThEvent> eventsPerSameDay(String date){
//        String formattedDate = date.substring(0,9);
////    }
//    public List<LiveShow> findByDate(LocalDateTime start, LocalDateTime end){
//       List<LiveShow> events = eventRepository.findLiveShowByEndDateBetween( start, end);
//       return events;
//    }
}

