package de.example.udemywebservice.repository;

import de.example.udemywebservice.model.Post;
import de.example.udemywebservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface PostRepository extends JpaRepository<Post, Integer> {


}
