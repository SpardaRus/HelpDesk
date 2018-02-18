package com.help_desk.controller;

import com.help_desk.entity.User;
import com.help_desk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
        return "viewAllUsers";
    }
    @RequestMapping("/add/{name},{address}")
    @ResponseBody
    @Transactional
    public String add(@PathVariable String name,@PathVariable  String address) {
        userRepository.save(new User(name,address));
        return "You add in db user: "+name+", "+address;
    }



}
