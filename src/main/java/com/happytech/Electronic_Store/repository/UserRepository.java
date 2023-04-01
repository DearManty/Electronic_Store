package com.happytech.Electronic_Store.repository;

import com.happytech.Electronic_Store.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByEmail(String userEmail);

    Optional<User> findByEmailAndPassword(String userEmail, String userPassword);

    List<User> findByName(String keywords);
}

