package com.example.userrole.dao;

import com.example.userrole.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    //User findFirstByName(String name);
    Optional<User> findByUsername(String name);
}
