package com.scm.smartcontactmanager.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.scm.smartcontactmanager.Entities.Contact;
import com.scm.smartcontactmanager.Entities.User;

@Repository
public interface ContactRepo extends JpaRepository<Contact,String>{
    //custom finder method
    Page<Contact> findByUser(User user, Pageable pageable);

    //custom Query mrthod
    // @Query("Select c from Contact c where c.user.id = :userId")
    // List<Contact> findByUserId(@Param("userId") String userId);

    Page<Contact> findByUserAndNameContaining(User user, String namekeyword, Pageable pageable);

    //Page<Contact> findByUserAndEmailContaining(User user, String emailkeyword, Pageable pageable);

    Page<Contact> findByUserAndPhonenumberContaining(User user, String phonekeyword, Pageable pageable);

}
