package com.project.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Berendezo ")
public class Berendezo extends Employee {
    public Berendezo(String firstName, String secondName, int age, String position) {
        super(firstName, secondName, age, position);
    }
}

