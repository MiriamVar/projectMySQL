package it.sovy.validateDocument;

import java.util.Date;

public class Person {
    private String name;
    private String surname;
    private Date dateOfBirth;
    private String pin;

    public Person(String name, String surname, Date dateOfBirth, String pin) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.pin = pin;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPin() {
        return pin;
    }
}
