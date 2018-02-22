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
public class Controller {
    @Autowired
    UserRepository userRepository;

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/view")
    public String view(Model model) {
            String view=null;
            for(User u:userRepository.findAll()){
                if(view==null){
                    view="<tr>"+
                            "<td>"+u.getId()+"</td>"+
                            "<td>"+u.getName()+"</td>"+
                            "<td>"+u.getAddress()+"</td>"+
                            "</tr>";
                }else{
                    view=view+"<tr>"+
                            "<td>"+u.getId()+"</td>"+
                            "<td>"+u.getName()+"</td>"+
                            "<td>"+u.getAddress()+"</td>"+
                            "</tr>";
                }
            }
        model.addAttribute("users", view);
        model.addAttribute("user",new User());
        return "viewAllUsers";
    }

    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public String addUser2(Model model, //
                         @ModelAttribute("user") User user) {
        if (user.getName() != null && user.getName().length() > 0
                && user.getAddress() != null && user.getAddress().length() > 0) {
            userRepository.save(user);
            return "addUserOk";
        }else {
            return "addUserError";
        }
    }
    @RequestMapping(value = "/addUserError")
    public String addUserError(Model model) {
        return "addUserError";
    }

}
