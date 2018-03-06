package com.project.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Vilagosito ")
public class Vilagosito extends Employee{
    public Vilagosito(String firstName, String secondName, int age) {
        super(firstName, secondName, age);
        super.setRole("Vilagosito");
    }

    public Vilagosito() {
    }
}
