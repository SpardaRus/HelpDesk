package com.help_desk.controller;

import com.help_desk.controller.form.UserRegistrationForm;
import com.help_desk.entity.UserSecurity;
import com.help_desk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("/")
@Controller
public class AuthController {
    @GetMapping
    public String index(){
        return "index";
    }
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new UserRegistrationForm());
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String registration(
            @Valid @ModelAttribute("userForm") UserRegistrationForm userRegistrationForm,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "auth/registration";
        }

        UserSecurity user = new UserSecurity();
        user.setUsername(userRegistrationForm.getUsername());
        user.setPassword(userRegistrationForm.getPassword());

        userService.signupUser(user);

        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        return "auth/login";
    }


    @GetMapping("/profile")
    public String profile() {

        return "auth/profile";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String admin() {

        return "auth/admin";
    }
}
