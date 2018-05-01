package com.project.repository;

import com.project.model.DatesOfEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface DatesOfEventRepository extends JpaRepository<DatesOfEvent, Long> {
    @Override
    DatesOfEvent findOne(Long aLong);


//    List<Object[]> getNameQuery(@Param("startDate") Date start,
//                              @Param("endDate") Date end);

//    DatesOfEvent getDatesOfEventByStartDateAfterAndEndDateBefore(@Param("roomId") Long id);
}
