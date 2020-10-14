package de.example.udemywebservice.versioning;

import de.example.udemywebservice.model.Name;

public class Person2 {

    private Name name;

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Person2(Name name) {
        this.name = name;
    }
}
