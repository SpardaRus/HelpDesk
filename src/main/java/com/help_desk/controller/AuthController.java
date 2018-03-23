package com.help_desk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/")
@Controller
public class AuthController {


    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/403")
    public String error403(Model model) {
        model.addAttribute("error","Access Denied");
        return "error/error";
    }


}
