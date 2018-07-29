package com.kodgemisi.course.ecommerce.user;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class RegistrationDTOValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(RegistrationDTO.class);
    }

    @Override
    public void validate(Object target, Errors errors) {

        RegistrationDTO registrationDTO = (RegistrationDTO) target;

        //what null
        if(registrationDTO.getEmail() != null && !registrationDTO.getEmail().equals(registrationDTO.getEmailConfirmation())){
            errors.rejectValue("emailConfirmation","user.registratonDto.emailConfirmationNotEqual","mail not match");
        }
    }
}
