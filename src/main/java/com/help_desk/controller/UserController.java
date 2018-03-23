package com.help_desk.controller;

import com.help_desk.controller.form.UserRegistrationForm;
import com.help_desk.entity.Role;
import com.help_desk.entity.User;
import com.help_desk.entity.UserSecurity;
import com.help_desk.repository.RoleRepository;
import com.help_desk.repository.UserRepository;
import com.help_desk.repository.UserSecurityRepository;
import com.help_desk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import java.util.HashSet;
import java.util.Set;

@RequestMapping("/user")
@Controller
public class UserController {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserSecurityRepository userSecurityRepository;
    @Autowired
    private UserService userService;
    @Autowired
    UserRepository userRepository;


    @GetMapping
    public String view(Model model,@ModelAttribute("userF") User userF) {
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("userForm",new UserRegistrationForm());
        model.addAttribute("userF",new User());
        return "user/editUsers";
    }
    @PostMapping("/find_user")
    public String findUser(Model model,
                           @ModelAttribute("userF") User userF){

        if(userRepository.findOne(userF.getId())!=null){
            userF=userRepository.findOne(userF.getId());
            model.addAttribute("userF",userF);
        }
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("userForm",new UserRegistrationForm());
        return "user/editUsers";
    }

    @RequestMapping(value = "/edit_user", method = RequestMethod.POST)
    public String editUser(Model model,
                             @ModelAttribute("userF") User userF){

        if(userRepository.findOne(userF.getId())!=null){
            userRepository.save(userF);
            model.addAttribute("userF",userF);
        }
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("userForm",new UserRegistrationForm());
        return "user/editUsers";
    }
    @RequestMapping(value = "/delete_user", method = RequestMethod.POST)
    public String deleteUser(Model model,
                             @ModelAttribute("userF") User userF){

        if(userRepository.findOne(userF.getId())!=null) {
            userF = userRepository.findOne(userF.getId());


            try {
                userRepository.delete(userF.getId());
            } catch (Exception e) {
                model.addAttribute("error","The last line is not deleted");
                return "log/log";
            }
            model.addAttribute("userF",userF);
        }

        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("userForm",new UserRegistrationForm());
        return "user/editUsers";
    }
    @RequestMapping(value = "/add_user", method = RequestMethod.POST)
    public String addUser(Model model,
                           @ModelAttribute("userForm") UserRegistrationForm userForm) {

        if(     userForm==null||
                userForm.getUsername()==null||
                userForm.getPassword()==null||
                userForm.getConfirmPassword()==null||
                userForm.getPassword().equals("")||
                userForm.getConfirmPassword().equals("")||
                !userForm.getPassword().equals(userForm.getConfirmPassword())){
            model.addAttribute("error","Incorrect data");
            return "log/log";
        }
        UserSecurity userUP = new UserSecurity();
        userUP.setUsername(userForm.getUsername());
        userUP.setPassword(userForm.getPassword());

        userService.signupUser(userUP);


        User user=new User(userForm.getUser().getName(),userForm.getUser().getAddress(),
                userSecurityRepository.findByUsername(userForm.getUsername()).getId());
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