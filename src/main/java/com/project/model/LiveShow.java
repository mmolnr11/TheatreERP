package com.project.model;



import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Live")
public class LiveShow extends ThEvent{
    private boolean elo;

    public LiveShow(String description, String titel, LocalDateTime startDate, LocalDateTime endDate, String location, boolean elo) {
        super(description, titel, startDate, endDate, location);
        this.elo = elo;
    }

    public LiveShow() {
    }
}
