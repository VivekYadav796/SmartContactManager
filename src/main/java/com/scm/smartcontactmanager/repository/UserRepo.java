package com.scm.smartcontactmanager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scm.smartcontactmanager.Entities.User;

@Repository
public interface UserRepo extends JpaRepository<User,String>{
  // user - which entity is using and type of userid
  Optional<User> findByEmail(String email);

  Optional<User> findByEmailAndPassword(String email, String password);
}
