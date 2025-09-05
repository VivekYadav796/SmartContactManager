package com.scm.smartcontactmanager.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.scm.smartcontactmanager.Entities.User;
import com.scm.smartcontactmanager.helper.Helper;
import com.scm.smartcontactmanager.services.UserServices;

@ControllerAdvice
public class RootController {
    
    @Autowired
    private UserServices userService;

    @ModelAttribute      // ye /user k hr page p chalega
    public void addLoggedInUser(Model model,Authentication authentication){
        if (authentication == null) {
            return;
        }
        String username = Helper.getEmailOfLoggedInUser(authentication);

        User user = userService.getUserByEmail(username);

        model.addAttribute("loggedInUser",user);
        
    }
}
