package com.scm.smartcontactmanager.ServiceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.scm.smartcontactmanager.repository.UserRepo;

@Service
public class SecurityUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    // configuration for authentication provider for spring security
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // apne user ko load karana hai

        return userRepo.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException(username));
    }   
}
