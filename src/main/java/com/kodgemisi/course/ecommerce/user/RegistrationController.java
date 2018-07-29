package com.kodgemisi.course.ecommerce.user;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationDTOValidator registrationDTOValidator;

    private final RegistrationService registrationService;

    @InitBinder
    void addRegistrationDtoValidator(WebDataBinder webDataBinder){
        webDataBinder.addValidators(this.registrationDTOValidator);
    }

    //GetMapping(nesne de verilebilir)
    @GetMapping
    public String registrationPage(Model model){
        model.addAttribute("registrationDTO", new RegistrationDTO());
        return "user/register";
    }

    @PostMapping
    public String registerUser(@Valid RegistrationDTO registrationDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "user/register";
        }
        registrationService.createUser(registrationDTO);
        return "redirect:/login";
    }
}
