package com.scm.smartcontactmanager.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class UserForm{

    @NotBlank(message = "It should not be blank")
    @Size(min = 3,message = "Min 3 character is required")
    private String name;

    @Email(message = "Invalid Email")
    @NotBlank(message = "Email is required")
    private String email;
    private String about;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = ("Password should be atleast of 6 character"))
    private String password;

    @NotBlank(message = "Required")
    @Size(min = 10,max = 12, message = "Invalid Phone Number")
    private String phoneNo; 
}
