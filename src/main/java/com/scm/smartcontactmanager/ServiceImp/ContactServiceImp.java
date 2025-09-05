package com.scm.smartcontactmanager.ServiceImp;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.scm.smartcontactmanager.Entities.Contact;
import com.scm.smartcontactmanager.Entities.User;
import com.scm.smartcontactmanager.helper.ResourceNotFoundException;
import com.scm.smartcontactmanager.repository.ContactRepo;
import com.scm.smartcontactmanager.services.ContactService;

@Service
public class ContactServiceImp implements ContactService{
    @Autowired
    private ContactRepo contactRepo;

    @Override
    public Contact save(Contact contact) {
        String contactId = UUID.randomUUID().toString();
        contact.setId(contactId);
        return contactRepo.save(contact);
    }

    @Override
    public Contact update(Contact contact) {

        var oldContact = contactRepo.findById(contact.getId()).orElseThrow(()-> new ResourceNotFoundException("Contact not found"));
        oldContact.setName(contact.getName());
        oldContact.setEmail(contact.getEmail());
        oldContact.setAddress(contact.getAddress());
        oldContact.setDescription(contact.getDescription());
        oldContact.setPhonenumber(contact.getPhonenumber());
        oldContact.setFavorite(contact.isFavorite());
        oldContact.setLinkdinLink(contact.getLinkdinLink());
        oldContact.setWebsiteLink(contact.getWebsiteLink());

        return contactRepo.save(oldContact);
        
    }

    @Override
    public List<Contact> getAll() {
        return contactRepo.findAll();
    }

    @Override
    public Contact getbyId(String id) {
        return contactRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException());
    }

    @Override
    public void delete(String id) {
        var contact = contactRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException());
        contactRepo.delete(contact);
    }

    @Override
    public List<Contact> search(String name, String email, String phonenumber) {
        return null;
        
       }

    @Override
    public List<Contact> getbyUserId(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getbyUserId'");
    }

     @Override
    public Page<Contact> getByUser(User user, int page, int size, String sortBy, String direction) {
        // Sort by name in ascending order if direction is not match desc
        Sort sort = direction.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

        var pageable = PageRequest.of(page, size, sort);

        return contactRepo.findByUser(user, pageable);

    }

    // @Override
    // public List<Contact> getbyUserId(String userId) {
    //     return contactRepo.findByUserId(userId);
    // }

    @Override
    public Page<Contact> searchByName(String nameKeyword, int size, int page, String sortBy, String order, User user) {

        Sort sort = order.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        var pageable = PageRequest.of(page, size, sort);
        return contactRepo.findByUserAndNameContaining(user, nameKeyword, pageable);
    }

    @Override
    public Page<Contact> searchByPhoneNumber(String phoneNumberKeyword, int size, int page, String sortBy,
            String order, User user) {

        Sort sort = order.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        var pageable = PageRequest.of(page, size, sort);
        return contactRepo.findByUserAndPhonenumberContaining(user, phoneNumberKeyword, pageable);
    }

}
