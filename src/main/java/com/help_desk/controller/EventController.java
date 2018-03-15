package com.help_desk.controller;

import com.help_desk.entity.*;
import com.help_desk.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequestMapping("/")
@Controller
public class EventController {

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private QualityRepository qualityRepository;
    @Autowired
    private StatusRepository statusRepository;
    @GetMapping
    public String listEvent(Model model){
        Iterable<Event> events;
        events=eventRepository.findByOrderByIdDesc();
        for(Event e:events){
            if(!e.equals(null)){
                if(e.getAdmin()==null){
                    e.setAdmin(new Admin());
                }
                if(e.getUser()==null){
                    e.setUser(new User());
                }
                if(e.getQuality()==null){
                    e.setQuality(new Quality());
                }
                if(e.getStatus()==null){
                    e.setStatus(new Status());
                }
            }

        }
        model.addAttribute("events", events);
        return "event/listEvent";
    }
    @GetMapping("/addEvent")
    public String addEvent(Model model){
        model.addAttribute("event",new Event());
        model.addAttribute("users",userRepository.findAll());
        model.addAttribute("admins",adminRepository.findAll());
        model.addAttribute("qualitys",qualityRepository.findAll());
        model.addAttribute("statuses",statusRepository.findAll());
        return "event/addEvent";
    }
    @PostMapping("/addEvent")
    public String addEventPost(Model model,@ModelAttribute("event") Event event){
        if(!event.getDescription().equals(null)&&!event.getDescription().equals("")){
            Date date = new Date();
            SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy-MM-dd");
            event.setDate(""+formatForDateNow.format(date));
            eventRepository.save(event);
            return "event/addEventOk";
        }else{
            return "event/addEventError";
        }

    }
    @GetMapping("/editEvent")
    public String editEvent(Model model){
        model.addAttribute("event",new Event());
        model.addAttribute("users",userRepository.findAll());
        model.addAttribute("admins",adminRepository.findAll());
        model.addAttribute("qualitys",qualityRepository.findAll());
        model.addAttribute("statuses",statusRepository.findAll());
        return "event/editEvent";
    }
    @PostMapping("/editEvent")
    public String editEventPost(Model model,@ModelAttribute("event") Event event){


            return "event/addEventOk";


    }

}
