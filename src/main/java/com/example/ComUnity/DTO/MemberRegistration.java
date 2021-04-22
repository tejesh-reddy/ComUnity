package com.example.ComUnity.DTO;


import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class MemberRegistration {

    @NotBlank(message = "Required")
    @Size(min=5, message = "Must be atleast 5 characters long")
    private String username;

    @NotBlank(message = "Required")
    @Size(min=5, message = "Must be atleast 5 characters long")
    private String password;

    @NotNull(message = "Required")
    @Email(message = "Please provide a valid Email")
    private String email;

}
