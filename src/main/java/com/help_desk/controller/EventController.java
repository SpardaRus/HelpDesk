package com.help_desk.controller;

import com.help_desk.entity.Event;
import com.help_desk.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @GetMapping
    public String index(Model model){
        model.addAttribute("events", eventRepository.findAll());
        return "index";
    }
}
