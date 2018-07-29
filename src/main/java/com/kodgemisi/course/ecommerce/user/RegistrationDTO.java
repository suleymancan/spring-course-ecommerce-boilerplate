package com.kodgemisi.course.ecommerce.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RegistrationDTO {


    @Size(min = 3 , message = "en az 3")
    @NotBlank
    private String username;

    @Email(message = "Gecersiz e mail")
    @NotBlank
    private String email;

    @Email(message = "Gecersiz e mail")
    @NotBlank
    private String emailConfirmation;

    @Size(min = 6, message = "en az 6")
    @NotBlank
    private String password;

}
