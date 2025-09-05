package com.scm.smartcontactmanager.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ContactForm {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email needed")
    @Email(message = "Invalid email address")
    private String email;

    @NotBlank(message = "Required")
    @Pattern(regexp = "^[0-9]{10}$",message = "Invalid Phone Number")
    private String phonenumber;
    private String address;
    private String description;
    private boolean favorite;

    @Pattern(
    regexp =  "^(https://www\\.instagram\\.com/[A-Za-z0-9_.]+/?|^)$",    //The |^$ allows an empty string ("") to pass through without triggering the validation.
    message = "Instagram URL must follow the format: https://www.instagram.com/your_username/")
    private String websiteLink;

    
    @Pattern(
    regexp = "^https://www\\.linkedin\\.com/in/[A-Za-z0-9_-]+/?|^$",
    message = "LinkedIn URL must follow the format: https://www.linkedin.com/in/yourname/")
    private String linkdinLink;
    //private MultipartFile profileImage;
}
