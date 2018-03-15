package com.help_desk.controller;

import com.help_desk.entity.Event;
import com.help_desk.entity.User;
import com.help_desk.repository.EventRepository;
import com.help_desk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
public class EventController {

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String listEvent(Model model){
        model.addAttribute("users",userRepository.findAll());

        model.addAttribute("events", eventRepository.findAll());
        return "event/listEvent";
    }
    @GetMapping("/addEvent")
    public String addEvent(Model model){
        model.addAttribute("event",new Event());
        model.addAttribute("users",userRepository.findAll());
        return "event/addEvent";
    }
    @PostMapping("/addEvent")
    public String addEventPost(Model model,@ModelAttribute("event") Event event){
        event.setDate("2018-03-11");
        eventRepository.save(event);
        return "event/listEvent";
    }

}
