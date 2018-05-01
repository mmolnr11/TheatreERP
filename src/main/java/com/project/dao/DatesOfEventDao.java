package com.project.dao;

import com.project.model.DatesOfEvent;
import com.project.repository.DatesOfEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DatesOfEventDao {
    @Autowired
    DatesOfEventRepository datesOfEventRepository;

    public DatesOfEvent findDate(Long id){
        DatesOfEvent dates = datesOfEventRepository.findOne(id);
        return dates;
    }
//    public List<Object[]> datesBetween (Date start, Date end){
//        return datesOfEventRepository.getNameQuery(start,end);
//    };

    public void saveDate(DatesOfEvent datesOfEvent){datesOfEventRepository.save(datesOfEvent);}
}
