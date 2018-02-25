package com.help_desk.controller;

import com.help_desk.entity.User;
import com.help_desk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

@org.springframework.stereotype.Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/view_user")
    public String view(Model model,@ModelAttribute("userF") User userF) {
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("user",new User());
        model.addAttribute("userF",userF);
        return "editUsers";
    }
    @RequestMapping(value = "/find_user", method = RequestMethod.POST)
    public String findUser(Model model,
                           @ModelAttribute("userF") User userF,
                           @ModelAttribute("users") User users,
                           @ModelAttribute("user") User user){
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("user",new User());
        if(userRepository.findById(userF.getId())!=null){
            userF=userRepository.findById(userF.getId());
            model.addAttribute("userF",userF);
        }
        return "editUsers";
    }

    @RequestMapping(value = "/edit_user", method = RequestMethod.POST)
    public String editUser(Model model,
                             @ModelAttribute("userF") User userF,
                             @ModelAttribute("users") User users,
                             @ModelAttribute("user") User user){

        if(userRepository.findById(userF.getId())!=null){
            userRepository.setUser(userF.getId(),userF.getName(),userF.getAddress());
            model.addAttribute("userF",userF);
        }
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("user",new User());
        return "editUsers";
    }
    @RequestMapping(value = "/delete_user", method = RequestMethod.POST)
    public String deleteUser(Model model,
                             @ModelAttribute("userF") User userF,
                             @ModelAttribute("users") User users,
                             @ModelAttribute("user") User user){

        if(userRepository.findById(userF.getId())!=null){
            userF=userRepository.findById(userF.getId());
            userRepository.delete(userF.getId());

            model.addAttribute("userF",userF);
        }
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("user",new User());
        return "editUsers";
    }
    @RequestMapping(value = "/add_user", method = RequestMethod.POST)
    public String addUser2(Model model,
                           @ModelAttribute("user") User user,
                           @ModelAttribute("userF") User userF) {

        if (user.getName() != null && user.getName().length() > 0
                && user.getAddress() != null && user.getAddress().length() > 0) {
            userRepository.save(user);
            return "addUserOk";
        }else {
            return "addUserError";
        }
    }

}
