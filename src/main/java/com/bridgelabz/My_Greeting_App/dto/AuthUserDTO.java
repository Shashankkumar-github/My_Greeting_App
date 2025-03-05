package com.bridgelabz.My_Greeting_App.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import jakarta.validation.constraints.Pattern;


@Data
public class AuthUserDTO {

    @NotBlank
    @Pattern(regexp = "^[A-Z][a-z]*$", message = "First name should start with uppercase")
    private String firstName;

    @NotBlank
    @Pattern(regexp = "^[A-Z][a-z]*$", message = "Last name should start with uppercase")
    private String lastName;

    @Email(message = "Invalid email format")
    @NotBlank
    private String email;

    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,}$",
            message = "Password must contain 1 uppercase, 1 number, 1 special character & min 8 chars")
    private String password;
}

