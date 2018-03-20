package com.help_desk.controller;

import com.help_desk.controller.form.UserRegistrationForm;
import com.help_desk.entity.Event;
import com.help_desk.entity.User;
import com.help_desk.entity.UserSecurity;
import com.help_desk.repository.EventRepository;
import com.help_desk.repository.UserRepository;
import com.help_desk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;

@RequestMapping("/")
@Controller
public class AuthController {


    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new UserRegistrationForm());
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String registration(Model model,
            @Valid @ModelAttribute("userForm") UserRegistrationForm userRegistrationForm,
            BindingResult bindingResult) {
        if(     userRegistrationForm==null||
                userRegistrationForm.getPassword()==null||
                userRegistrationForm.getConfirmPassword()==null||
                userRegistrationForm.getPassword().equals("")||
                userRegistrationForm.getConfirmPassword().equals("")||
                !userRegistrationForm.getPassword().equals(userRegistrationForm.getConfirmPassword())){
            model.addAttribute("error","Incorrect data");
            return "log/log";
        }
        if (bindingResult.hasErrors()) {
            return "auth/registration";
        }

        UserSecurity user = new UserSecurity();
        user.setUsername(userRegistrationForm.getUsername());
        user.setPassword(userRegistrationForm.getPassword());

        userService.signupUser(user);
        model.addAttribute("error","User successfully added");
        return "log/log";
    }

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }


}
