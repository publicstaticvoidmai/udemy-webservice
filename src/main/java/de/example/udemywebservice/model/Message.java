package de.example.udemywebservice.model;

import org.springframework.stereotype.Component;


public class Message {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Message(String message) {
        this.message = message;
    }
}
