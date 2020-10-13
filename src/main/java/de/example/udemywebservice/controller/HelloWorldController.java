package de.example.udemywebservice.controller;

import de.example.udemywebservice.model.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    private Message message;

    @GetMapping("/helloworld")
    public Message printMessage(){
        return new Message("Hello World!");
    }
}
