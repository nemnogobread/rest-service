package com.example.rest_service.repository;

import com.example.rest_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT u from User u WHERE u.lastName = ?1")
    Optional<User> findByLastName(String lastname);
}
