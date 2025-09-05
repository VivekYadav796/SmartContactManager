package com.scm.smartcontactmanager.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.scm.smartcontactmanager.Entities.Contact;
import com.scm.smartcontactmanager.Entities.User;

public interface ContactService {
    Contact save(Contact contact);

    Contact update(Contact contact);

    List<Contact> getAll();

    Contact getbyId(String id);

    void delete(String id);

    List<Contact> search(String name, String email, String phonenumber);

    List<Contact> getbyUserId(String id);

    //paging
    Page<Contact> getByUser(User user, int page, int size, String sortField, String sortDirection);

    // search contact
    Page<Contact> searchByName(String nameKeyword, int size, int page, String sortBy, String order, User user);

    //Page<Contact> searchByEmail(String emailKeyword, int size, int page, String sortBy, String order, User user);

    Page<Contact> searchByPhoneNumber(String phoneNumberKeyword, int size, int page, String sortBy, String order,
            User user);
}
