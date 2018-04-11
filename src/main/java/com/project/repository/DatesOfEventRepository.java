package com.project.repository;

import com.project.model.DatesOfEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatesOfEventRepository extends JpaRepository<DatesOfEvent, Long> {
    @Override
    DatesOfEvent findOne(Long aLong);
}
