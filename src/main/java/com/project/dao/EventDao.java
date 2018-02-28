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
import java.util.Date;
import java.util.List;

@Service
public class EventDao {
    @Autowired
    EventRepository eventRepository;

    public LiveShow findOne(Long id){
        LiveShow liveShow = eventRepository.findOne(id);
        return liveShow;
    }

    public void saveLiveshow(LiveShow liveShow){
        eventRepository.save(liveShow);
    }

//    public List<ThEvent> findAll() {
//        return eventRepository.findAll();
//    }
//    public List<ThEvent> eventsPerSameDay(String date){
//        String formattedDate = date.substring(0,9);
////    }
    public List<LiveShow> findByDate(Date start, Date end){
        List<LiveShow> events = eventRepository.findLiveShowByEndDateTimeBetween(start, end);
        return events;
    }
}

