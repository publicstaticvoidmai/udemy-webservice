package de.example.udemywebservice.controller;

import de.example.udemywebservice.exception.UserNotFoundException;
import de.example.udemywebservice.model.Post;
import de.example.udemywebservice.model.User;
import de.example.udemywebservice.repository.PostRepository;
import de.example.udemywebservice.repository.UserRepository;
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
import java.util.Optional;
import java.util.OptionalInt;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
public class UserJPAController {

    private final UserRepository userRepository;

    private final PostRepository postRepository;

    @Autowired
    public UserJPAController(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    /*@Autowired
    private UserDaoService userDaoService;*/


    /*public UserJPAController(UserDaoService userDaoService) {
        this.userDaoService = userDaoService;
    }*/

    @GetMapping("/jpa/users")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> getOneUser(@PathVariable int id){
        Optional<User> user = userRepository.findById(id);

        /*if(user == null){
            throw new UserNotFoundException("id-"+ id );
        }*/
        if(!user.isPresent()){
            throw new UserNotFoundException("id-" + id);
        }

        //HATEOAS
        //instead of just returning data, now data + link
        EntityModel<User> resource = EntityModel.of(user.get());

        //what link: (getAllUsers)
        WebMvcLinkBuilder linkTo =
                linkTo(methodOn(this.getClass()).getAllUsers());

        //how to refer link:
        resource.add(linkTo.withRel("all-users"));



        return resource;
    }


    @PostMapping("/jpa/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
        User savedUser = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> getAllPostsfromUser(@PathVariable int id){
        Optional<User> userOptional = userRepository.findById(id);

        if(!userOptional.isPresent()){
            throw new UserNotFoundException("id-" + id);
        }

        return userOptional.get().getPosts();
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUserbyId(@PathVariable int id){
        userRepository.deleteById(id);
        }



    //TODO: funktioniert nicht
    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPost(@Valid @RequestBody int id,@RequestBody Post post){
        Optional<User> userOptional = userRepository.findById(id);

        if(!userOptional.isPresent()){
            throw new UserNotFoundException("id-" + id);
        }

        User user = userOptional.get();

        post.setUser(user);

        postRepository.save(post);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
    }




