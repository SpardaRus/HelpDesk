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
    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", required=false,
            defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }
    @RequestMapping("/add/{name},{address}")
    @ResponseBody
    @Transactional
    public String add(@PathVariable String name,@PathVariable  String address) {
        userRepository.save(new User(name,address));
        return "You add in db user: "+name+", "+address;
    }

    @RequestMapping("/view")
    @ResponseBody
    @Transactional
    public String view() {
        String view=null;
        for(User u:userRepository.findAll()){
            if(view==null){
                view=u+"\n";
            }else{
                view=view+u+"\n";
            }
        }
        return ""+view;
    }


}
