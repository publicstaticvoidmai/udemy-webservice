package de.example.udemywebservice.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonFilter("SomeBeanFilter")
//@JsonIgnoreProperties(value = {"password", "field2"} ) //static filtering
public class SomeBean {

    private String field1;

    private String field2;

    //@JsonIgnore
    private String password;

    public SomeBean(String field1, String field2, String password) {
        this.field1 = field1;
        this.field2 = field2;
        this.password = password;
    }
}
