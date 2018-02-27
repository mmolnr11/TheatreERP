package com.project.repository;

import com.project.model.LiveShow;
import org.springframework.data.jpa.repository.JpaRepository;


import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<LiveShow, Long> {
    @Override
    LiveShow findOne(Long aLong);

    List<LiveShow> findLiveShowByEndDateTimeBetween(LocalDateTime endate1, LocalDateTime endate2);


}
