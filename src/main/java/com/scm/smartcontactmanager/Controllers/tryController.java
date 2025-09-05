package com.scm.smartcontactmanager.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.scm.smartcontactmanager.Entities.User;
import com.scm.smartcontactmanager.forms.UserForm;
import com.scm.smartcontactmanager.services.UserServices;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@Controller
public class tryController {
    @Autowired
    private UserServices userServices;

    @GetMapping("/")
    public String index() {
        return "redirect:/home";
    }
    
    @GetMapping("/try")
    public String tryy() {
        return "tailwind";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/services")
    public String services() {
        return "services";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/login")
    public String login() {
        return new String("login");
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);

        return "signup";
    }
    // ---------------------------------------------------------------------------
    // ==========================================================================

    @PostMapping("/do-register")
    public String processsignup(@Valid @ModelAttribute UserForm userForm,BindingResult rBindingResult ,HttpSession session) {
        // modelatrribute took all the data from the form and it has userform object
        // also it put them to its respective field

        //validate the form

        if (rBindingResult.hasErrors()) {
            return "signup";
        }




        User user = new User();

        // Set fields using setters
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhoneNo(userForm.getPhoneNo());

        User savedUser = userServices.saveUser(user);

        // after successful show msg
        // Message message = Message.builder().content("Registration
        // Successful").type(MessageType.green).build();

        // session.setAttribute("message", message);

        session.setAttribute("message", "Registration Successful");

        return "redirect:/msgbox";
    }

    @GetMapping("/msgbox")
    public String showMsgBox() {
        return "msgbox"; // will load msgbox.html from templates/
    }

}
