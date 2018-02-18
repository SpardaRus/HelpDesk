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

@org.springframework.stereotype.Controller
public class Controller {
    @Value("${error.message}")
    private String errorMessage;

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
        return "viewAllUsers";
    }
    @RequestMapping(value = "/addUserError")
    public String addUserError(Model model) {
        return "addUserError";
    }

    @RequestMapping(value = "/add")
    public String addUser(Model model) {
        model.addAttribute("user",new User());
        return "addUser";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUser2(Model model, //
                                @ModelAttribute("user") User user) {

        String name = user.getName();
        String address = user.getAddress();

        if (name != null && name.length() > 0
                && address != null && address.length() > 0) {
            userRepository.save(new User(name, address));
            return "addUser";
        }
        String error = "Name & Address is required!";
        model.addAttribute("addUserError", error);
        return "addUserError";
    }


}
