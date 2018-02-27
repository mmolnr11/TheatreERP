package com.project.model;

//import com.google.api.client.util.DateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "Rehersal")
public class Rehersal extends ThEvent {
    @Column
    private boolean proba;

    public Rehersal(String description, String titel, LocalDateTime startDate, LocalDateTime endDate, String location, boolean proba) {
        super(description, titel, startDate, endDate, location);
        this.proba = proba;
    }

}
