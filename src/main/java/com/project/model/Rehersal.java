package com.project.model;

//import com.google.api.client.util.DateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "Rehersal")
public class Rehersal extends ThEvent {
    @Column
    private boolean proba;

    public Rehersal(String description, String titel, Date startDate, Date endDate, String location, boolean proba) {
        super(description, titel, startDate, endDate, location);
        this.proba = proba;
    }

}