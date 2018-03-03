package com.project.repository;

import com.project.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    @Override
    Event findOne(Long aLong);
    List<Event> findEventByEndDateTimeBetween(Date start, Date end);


}
