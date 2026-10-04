package com.myapp.system.service;


import com.myapp.system.model.User;

import com.myapp.system.repository.UserRepository;

public class UserService {
    private UserRepository userRepo = new UserRepository();
    
    
    public UserStatus createUser(String user, String name, String lastName, String email, String password) {
        try {
            User newUser = new User(name, lastName, email, user, password);
            userRepo.create(newUser);
            return UserStatus.USER_CREATED;
        } catch (Exception e) {
            return UserStatus.ERROR_USER_CREATE;
        }
    }
    
    
}
