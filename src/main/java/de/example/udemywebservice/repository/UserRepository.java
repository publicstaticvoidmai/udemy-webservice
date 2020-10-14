package de.example.udemywebservice.repository;

import de.example.udemywebservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface UserRepository extends JpaRepository<User, Integer> {


}
