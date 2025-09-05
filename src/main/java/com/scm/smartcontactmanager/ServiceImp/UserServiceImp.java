package com.scm.smartcontactmanager.ServiceImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.scm.smartcontactmanager.Entities.User;
import com.scm.smartcontactmanager.helper.AppConstants;
import com.scm.smartcontactmanager.helper.ResourceNotFoundException;
import com.scm.smartcontactmanager.repository.UserRepo;
import com.scm.smartcontactmanager.services.UserServices;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserServiceImp implements UserServices {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Logger logger = LoggerFactory.getLogger(this.getClass());



    @Override
    public User saveUser(User user) {
        // user id : have to generate
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        // password encode
        // user.setPassword(userId);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // set the user role i.e admin or user
        user.setRoleList(List.of(AppConstants.ROLE_USER));

        logger.info(user.getProvider().toString());

        return userRepo.save(user);

    }

    @Override
    public Optional<User> getUserbyId(String userId) {
        return userRepo.findById(userId);
        }

    @Override
    public Optional<User> updateUser(User user) { 
        //User user2 = userRepo.findById(user.getUserId()).orElseThrow(()-> new ResourceNotFoundException("User not found"));
        // now update 
        return Optional.empty();
    }

    @Override
    public void deleteUser(String userId) {
        User user2 = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found"));
        userRepo.delete(user2);
    }

    @Override
    public boolean isUserExist(String userId) {
        User user2 = userRepo.findById(userId).orElse(null);
        return user2 != null ? true : false;
    }

    @Override
    public boolean isUserExistByEmail(String email) {
        User user2 = userRepo.findByEmail(email).orElse(null);
        return user2 != null ? true : false;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email).orElse(null);
    }


}
