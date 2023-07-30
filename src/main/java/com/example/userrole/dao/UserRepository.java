package com.example.userrole.dao;

import com.example.userrole.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findFirstByName(String name);
}
