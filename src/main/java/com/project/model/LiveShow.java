package com.project.model;


import javax.persistence.*;

@Entity
@Table(name = "Events")
public class LiveShow extends ThEvent{
    @Id@GeneratedValue
    private int id;
    @Column
    private String description;
    @Column
    private String titel;
    @Column
    private String startDate;
    @Column
    private String endDate;
    @Column
    private String location;


    public LiveShow(String description, String titel, String startDate, String endDate, String location, String description1, String titel1, String startDate1, String endDate1, String location1) {
        super(description, titel, startDate, endDate, location);
        this.description = description1;
        this.titel = titel1;
        this.startDate = startDate1;
        this.endDate = endDate1;
        this.location = location1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getTitel() {
        return titel;
    }

    @Override
    public void setTitel(String titel) {
        this.titel = titel;
    }

    @Override
    public String getStartDate() {
        return startDate;
    }

    @Override
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @Override
    public String getEndDate() {
        return endDate;
    }

    @Override
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public void setLocation(String location) {
        this.location = location;
    }
}
