package com.project.service;

import com.project.dao.EventDao;
import com.project.dao.UserDao;
import com.project.model.Event;
import com.project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventValidation {
    @Autowired
    EventDao eventDao;

    public List<String> validateEvent(Event event) {
        List<String> errorMessages = new ArrayList<>();

        if (event.getTitle().length() < 2 ) {
            errorMessages.add("Cim legyen több mint 2 karakter hosszú.");
        }
        if(event.getTitle().length() >0 && dataNotStartsWithUpperCaseLetter(event.getTitle())) {
            errorMessages.add("Cím kezdődjön nagy betűvel.");
        }


        return errorMessages;
    }

    private boolean dataNotStartsWithUpperCaseLetter(String word) {
        return Character.isLowerCase(word.charAt(0));
    }

    ;
}
