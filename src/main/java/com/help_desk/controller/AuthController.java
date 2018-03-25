package com.help_desk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for login and error
 */
@RequestMapping("/")
@Controller
public class AuthController {

    /**
     * Redirect to the page login
     * @return
     */
    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    /**
     * Handling error 403
     * @param model
     * @return
     */
    @GetMapping("/403")
    public String error403(Model model) {
        model.addAttribute("error","Access Denied");
        return "log/log";
    }


}
