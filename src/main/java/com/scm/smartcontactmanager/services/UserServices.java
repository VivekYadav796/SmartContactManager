package com.scm.smartcontactmanager.services;

import java.util.List;
import java.util.Optional;

import com.scm.smartcontactmanager.Entities.User;

public interface UserServices {
    User saveUser(User user);
    Optional<User> getUserbyId(String userId); 
    Optional<User> updateUser(User user);
    void deleteUser(String userId);
    boolean isUserExist(String userId);
    boolean isUserExistByEmail(String email);
    List<User> getAllUsers();
    User getUserByEmail(String email);
}
