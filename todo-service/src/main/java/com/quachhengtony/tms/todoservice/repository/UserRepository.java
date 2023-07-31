package com.quachhengtony.tms.todoservice.repository;

import com.quachhengtony.tms.todoservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String username);
}
