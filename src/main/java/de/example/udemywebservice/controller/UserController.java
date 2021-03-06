package de.example.udemywebservice.controller;

import de.example.udemywebservice.exception.UserNotFoundException;
import de.example.udemywebservice.model.Post;
import de.example.udemywebservice.model.User;
import de.example.udemywebservice.service.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
public class UserController {


    private UserDaoService userDaoService;

    @Autowired
    public UserController(UserDaoService userDaoService) {
        this.userDaoService = userDaoService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userDaoService.findAll();
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> getOneUser(@PathVariable int id){
        User user = userDaoService.findOne(id);

        //HATEOAS
        //instead of just returning data, now data + link
        EntityModel<User> resource = EntityModel.of(user);

        //what link: (getAllUsers)
        WebMvcLinkBuilder linkTo =
                linkTo(methodOn(this.getClass()).getAllUsers());

        //how to refer link:
        resource.add(linkTo.withRel("all-users"));

        if(user == null){
            throw new UserNotFoundException("id-"+ id );
        }

        return resource;
    }


    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
        User savedUser = userDaoService.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

   /* @GetMapping("/users/{id}/posts")
    public List<Post> getAllPostsfromUser(@PathVariable int id){
        if(userDaoService.findOne(id)!= null){
            return userDaoService.findOne(id).getPosts();
        }
        throw new UserNotFoundException("id-"+ id);
    }*/

    @DeleteMapping("/users/{id}")
    public void deleteUserbyId(@PathVariable int id){
        if(userDaoService.deleteById(id) == null){
            throw new UserNotFoundException("id-"+ id);
        }
    }



}
