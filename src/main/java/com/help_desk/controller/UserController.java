package com.help_desk.controller;

import com.help_desk.entity.User;
import com.help_desk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

@RequestMapping("/user")
@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;


    @GetMapping
    public String view(Model model,@ModelAttribute("userF") User userF) {
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("user",new User());
        userF=new User();
        model.addAttribute("userF",userF);
        return "user/editUsers";
    }
    @PostMapping("/find_user")
    public String findUser(Model model,
                           @ModelAttribute("userF") User userF,
                           @ModelAttribute("users") User users,
                           @ModelAttribute("user") User user){

        if(userRepository.findOne(userF.getId())!=null){
            userF=userRepository.findOne(userF.getId());
            model.addAttribute("userF",userF);
        }
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("user",new User());
        return "user/editUsers";
    }

    @RequestMapping(value = "/edit_user", method = RequestMethod.POST)
    public String editUser(Model model,
                             @ModelAttribute("userF") User userF,
                             @ModelAttribute("users") User users,
                             @ModelAttribute("user") User user){

        if(userRepository.findOne(userF.getId())!=null){
            userRepository.save(userF);
            model.addAttribute("userF",userF);
        }
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("user",new User());
        return "user/editUsers";
    }
    @RequestMapping(value = "/delete_user", method = RequestMethod.POST)
    public String deleteUser(Model model,
                             @ModelAttribute("userF") User userF,
                             @ModelAttribute("users") User users,
                             @ModelAttribute("user") User user){

        if(userRepository.findOne(userF.getId())!=null){
            userF=userRepository.findOne(userF.getId());
            userRepository.delete(userF.getId());
            model.addAttribute("userF",userF);
        }
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("user",new User());
        return "user/editUsers";
    }
    @RequestMapping(value = "/add_user", method = RequestMethod.POST)
    public String addUser(Model model,
                           @ModelAttribute("user") User user,
                           @ModelAttribute("userF") User userF) {

        if (user.getName() != null && user.getName().length() > 0
                && user.getAddress() != null && user.getAddress().length() > 0) {
            userRepository.save(user);
            model.addAttribute("error","Data successfully added");
        }else {
            model.addAttribute("error","Name and Address is required!");
        }
        return "log/log";
    }

}