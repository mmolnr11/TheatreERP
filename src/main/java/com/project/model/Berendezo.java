package com.project.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

// This class is only needed if it will have its own fields/methods in the future
@Entity
@DiscriminatorValue("Berendezo")
public class Berendezo extends Employee { // TODO always name variables in english


    public Berendezo() {
    }

    public Berendezo(String firstName, String secondName, int age, String position) {
        super(firstName, secondName, age, position);
        super.setPosition("Berendezo");
    }

}

