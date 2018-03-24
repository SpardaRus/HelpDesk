package com.help_desk.controller;

import com.help_desk.entity.*;
import com.help_desk.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
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
    @Autowired
    private RoleRepository roleRepository;
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
        model.addAttribute("eventF", new Event());
        return "event/listEvent";
    }
    @GetMapping("/personala")
    public String listEventPersonal(Model model){
        Iterable<Event> events;
        events=eventRepository.findByOrderByIdDesc();
        ArrayList<Event> eventArrayList=new ArrayList<>();
        UserSecurity userSecurity = (UserSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        for(Event e:events){
            if(e.getAdmin()!=null&&
                            e.getAdmin().getId_auth()==userSecurity.getId())


                {
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
                eventArrayList.add(e);
            }


        }
        model.addAttribute("events", eventArrayList);
        model.addAttribute("eventF", new Event());
        return "event/listEvent";
    }
    @GetMapping("/personalu")
    public String listEventPersonalu(Model model){
        Iterable<Event> events;
        events=eventRepository.findByOrderByIdDesc();
        ArrayList<Event> eventArrayList=new ArrayList<>();
        UserSecurity userSecurity = (UserSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        for(Event e:events){
            if(e.getUser()!=null&&
                    e.getUser().getId_auth()==userSecurity.getId())


            {
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
                eventArrayList.add(e);
            }


        }
        model.addAttribute("events", eventArrayList);
        model.addAttribute("eventF", new Event());
        return "event/listEvent";
    }
    @GetMapping("/addEvent")
    public String addEvent(Model model){
        model.addAttribute("event",new Event());
        return "event/addEvent";
    }
    @PostMapping("/addEvent")
    public String addEventPost(Model model,@ModelAttribute("event") Event event){
        if(!event.getDescription().equals(null)&&!event.getDescription().equals("")){
            Date date = new Date();
            SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy-MM-dd");
            event.setDate(""+formatForDateNow.format(date));
            UserSecurity userSecurity = (UserSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            event.setUser(userRepository.findById_auth(userSecurity.getId()));
            eventRepository.save(event);
            model.addAttribute("error","Successfully added");
        }else{
            model.addAttribute("error","The description should be filling");
        }
        return "log/log";
    }
    @GetMapping("/editEvent")
    public String editEvent(Model model,
                            @Valid
                            @ModelAttribute("eventF") Event event,
                            BindingResult bindingResult){
        if (bindingResult.hasErrors()||event.getId()==null) {
            return "redirect: ";
        }

        if(eventRepository.findOne(event.getId())==null){
            model.addAttribute("error","Incorrect data");
            return "log/log";
        }else{
            event=eventRepository.findOne(event.getId());
            UserSecurity userSecurity = (UserSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            String c="";
            for(Object o:userSecurity.getAuthorities()){
                c=""+o;
            }

            if((!userSecurity.getUsername().equals("superadmin"))&&
                    ((c.equals("user")&&((event.getUser()==null||event.getUser().getId_auth()!=userSecurity.getId()))))||
                    ((c.equals("admin")&&((event.getAdmin()==null||event.getAdmin().getId_auth()!=userSecurity.getId()))))){
                model.addAttribute("error","This event is not yours");
                return "log/log";
            }
            model.addAttribute("event",event);
            model.addAttribute("users",userRepository.findAll());
            model.addAttribute("admins",adminRepository.findAll());
            model.addAttribute("qualitys",qualityRepository.findAll());
            model.addAttribute("statuses",statusRepository.findAll());
            return "event/editEvent";
        }

    }
    @PostMapping("/editEvent")
    public String editEventPost(Model model,@ModelAttribute("event") Event event){
            eventRepository.save(event);

            model.addAttribute("error","Data successfully edited");
            return "log/log";
    }
    @PostMapping("/deleteEvent")
    public String deleteEvent(Model model,
                              @Valid
                              @ModelAttribute("eventF") Event event,
                              BindingResult bindingResult){
        if (bindingResult.hasErrors()||event.getId()==null) {
            return "redirect: ";
        }
        if(eventRepository.findOne(event.getId())==null){
            model.addAttribute("error","Incorrect data");
            return "log/log";
        }else{
            eventRepository.delete(event.getId());
            return "redirect:";
        }


    }
}
