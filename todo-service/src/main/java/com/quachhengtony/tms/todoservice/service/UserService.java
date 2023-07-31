package com.quachhengtony.tms.todoservice.service;

import com.quachhengtony.tms.todoservice.model.User;
import com.quachhengtony.tms.todoservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public User getByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }
}
