package de.example.udemywebservice.versioning;

import de.example.udemywebservice.model.Name;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {

    //URI VERSIONING
    @GetMapping("/v1/person")
    public Person1 getPersonV1(){
        return new Person1("Minko Ginko");
    }

    @GetMapping("/v2/person")
    public Person2 getPersonV2(){
        return new Person2(new Name("Minko", "Ginko"));
    }

    //REQUEST PARAM VERSIONING
    //http://localhost:8080/person/param?version=1
    @GetMapping(value = "/person/param", params = "version=1")
    public Person1 getParamV1(){
        return new Person1("Minko Ginko");
    }

    @GetMapping(value = "/person/param", params = "version=2")
    public Person2 getParamV2(){
        return new Person2(new Name("Minko", "Ginko"));
    }

    //HEADER VERSIONING
    //http://localhost:8080/person/header
    //Header: Key = X-API-VERSION, Value = 1
    @GetMapping(value = "/person/header", headers = "X-API-VERSION=1")
    public Person1 getHeaderV1(){
        return new Person1("Minko Ginko");
    }

    @GetMapping(value = "/person/header", headers = "X-API-VERSION=2")
    public Person2 getHeaderV2(){
        return new Person2(new Name("Minko", "Ginko"));
    }

    //ACCEPT HEADER VERSIONING / MIMI TYPE VERSIONING / MEDIA TYPE VERSIONING
    //also header versioning but sending request is different
    //http://localhost:8080/person/produces
    //Header: Key = Accept, Value = application/vnd.company.app-v1+json
    @GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v1+json")
    public Person1 producesV1(){
        return new Person1("Minko Ginko");
    }

    @GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v2+json")
    public Person2 producesV2(){
        return new Person2(new Name("Minko", "Ginko"));
    }


}

//
