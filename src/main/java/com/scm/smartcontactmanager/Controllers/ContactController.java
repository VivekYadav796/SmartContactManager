package com.scm.smartcontactmanager.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.scm.smartcontactmanager.Entities.Contact;
import com.scm.smartcontactmanager.Entities.User;
import com.scm.smartcontactmanager.forms.ContactForm;
import com.scm.smartcontactmanager.forms.ContactSearchForm;
import com.scm.smartcontactmanager.helper.Helper;
import com.scm.smartcontactmanager.services.ContactService;
import com.scm.smartcontactmanager.services.UserServices;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/user/contacts")
public class ContactController {
    @Autowired
    private ContactService contactService;

    @Autowired
    private UserServices userService;

    @GetMapping("/add")
    public String addContact(Model model) {
        ContactForm contactForm = new ContactForm();
        model.addAttribute("contactForm", contactForm);
        return "user/add_contact";
    }

    @PostMapping("/adding")
    public String saveContact(@Valid @ModelAttribute ContactForm contactForm, BindingResult result,
            Authentication authentication, HttpSession session, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            // session.setAttribute("message", Message.builder()
            // .content("Please correct the following errors")
            // .type(MessageType.red)
            // .build());
            return "user/add_contact";
        }
        String username = Helper.getEmailOfLoggedInUser(authentication);
        User user = userService.getUserByEmail(username);
        Contact contact = new Contact();

        contact.setName(contactForm.getName());
        contact.setEmail(contactForm.getEmail());
        contact.setDescription(contactForm.getDescription());
        contact.setAddress(contactForm.getAddress());
        contact.setFavorite(contactForm.isFavorite());
        contact.setPhonenumber(contactForm.getPhonenumber());
        contact.setLinkdinLink(contactForm.getLinkdinLink());
        contact.setWebsiteLink(contactForm.getWebsiteLink());

        contact.setUser(user);
        contactService.save(contact);

        // session.setAttribute("message", Message.builder()
        // .content("New Contact Added Successfully")
        // .type(MessageType.green)
        // .build());

        redirectAttributes.addFlashAttribute("successMessage", "Contact saved successfully!");
        return "redirect:/user/contacts/add";

        // dought we have to autowired these two kyuki hmne bakiyo ko to import wgera
        // kiya hai inhe kyu nhi kr skte
    }

    @GetMapping("/view")
    public String viewContacts(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "7") int size,
            @RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(value = "direction", defaultValue = "asc") String direction, Model model,
            Authentication authentication) {

        // for view starts
        String username = Helper.getEmailOfLoggedInUser(authentication);
        User user = userService.getUserByEmail(username);
        Page<Contact> pageContact = contactService.getByUser(user, page, size, sortBy, direction);
        // view ends here
        model.addAttribute("pageContact", pageContact);
        model.addAttribute("pageSize", 7);
        model.addAttribute("contactSearchForm", new ContactSearchForm());
        return "user/view_contact";
    }

    @RequestMapping("/search")
    public String searchHandler(

            @ModelAttribute ContactSearchForm contactSearchForm,
            @RequestParam(value = "size", defaultValue = "7") int size,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(value = "direction", defaultValue = "asc") String direction,
            Model model,
            Authentication authentication) {

        if (contactSearchForm.getField() == null || contactSearchForm.getField().isEmpty()
                || contactSearchForm.getValue() == null || contactSearchForm.getValue().isEmpty()) {

            // Return empty state or redirect with error message
            model.addAttribute("pageContact", Page.empty()); // Avoid null
            model.addAttribute("error", "Please select a field and enter a value");
            return "user/search";
        }

        var user = userService.getUserByEmail(Helper.getEmailOfLoggedInUser(authentication));

        Page<Contact> pageContact = null;
        if (contactSearchForm.getField().equalsIgnoreCase("name")) {
            pageContact = contactService.searchByName(contactSearchForm.getValue(), size, page, sortBy, direction,
                    user);
            // else if (contactSearchForm.getField().equalsIgnoreCase("email")) {
            // pageContact = contactService.searchByEmail(contactSearchForm.getValue(),
            // size, page, sortBy, direction,
            // user);
        } else if (contactSearchForm.getField().equalsIgnoreCase("phone")) {
            pageContact = contactService.searchByPhoneNumber(contactSearchForm.getValue(), size, page, sortBy,
                    direction, user);
        }
        model.addAttribute("contactSearchForm", contactSearchForm);

        model.addAttribute("pageContact", pageContact);

        model.addAttribute("pageSize", 7);

        return "user/search";

    }

    @RequestMapping("/delete/{contactId}")
    public String delcontact(@PathVariable("contactId") String contactId, RedirectAttributes redirectAttributes) {
        contactService.delete(contactId);

        redirectAttributes.addFlashAttribute("successMessage", "Contact Deleted !!");
        return "redirect:/user/contacts/view";
    }

    @GetMapping("/update/{contactId}")
    public String updateContact(@PathVariable("contactId") String contactId, Model model) {
        var contact = contactService.getbyId(contactId);

        ContactForm contactForm = new ContactForm();
        contactForm.setName(contact.getName());
        contactForm.setEmail(contact.getEmail());
        contactForm.setPhonenumber(contact.getPhonenumber());
        contactForm.setAddress(contact.getAddress());
        contactForm.setDescription(contact.getDescription());
        contactForm.setFavorite(contact.isFavorite());
        contactForm.setWebsiteLink(contact.getWebsiteLink());
        contactForm.setLinkdinLink(contact.getLinkdinLink());

        model.addAttribute("contactForm", contactForm);
        model.addAttribute("contactId", contactId);

        return "user/update_contact_view";
    }

    @PostMapping("/updating/{contactId}")
    public String updatingCont(@PathVariable("contactId") String contactId, Model model,
            @Valid @ModelAttribute ContactForm contactForm,
            BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "user/update_contact_view";
        }

        var con = contactService.getbyId(contactId);
        con.setId(contactId);
        con.setName(contactForm.getName());
        con.setEmail(contactForm.getEmail());
        con.setPhonenumber(contactForm.getPhonenumber());
        con.setAddress(contactForm.getAddress());
        con.setDescription(contactForm.getDescription());
        con.setFavorite(contactForm.isFavorite());
        con.setWebsiteLink(contactForm.getWebsiteLink());
        con.setLinkdinLink(contactForm.getLinkdinLink());

        var updateCon = contactService.update(con);

        redirectAttributes.addFlashAttribute("suckessMessage", "Contact Updated !!");

        return "redirect:/user/contacts/view";
    }
}
