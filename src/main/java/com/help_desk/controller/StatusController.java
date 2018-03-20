package com.help_desk.controller;

import com.help_desk.entity.Quality;
import com.help_desk.entity.Status;
import com.help_desk.repository.QualityRepository;
import com.help_desk.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/status")
@Controller
public class StatusController {

    @Autowired
    StatusRepository statusRepository;

    @GetMapping
    public String view(Model model, @ModelAttribute("statusF") Status statusF) {
        model.addAttribute("statuss", statusRepository.findAll());
        model.addAttribute("status",new Status());
        model.addAttribute("statusF",statusF);
        return "status/status";
    }
    @PostMapping("/find_status")
    public String findStatus(Model model,
                             @ModelAttribute("statusF") Status statusF,
                             @ModelAttribute("statuss") Status statuss,
                             @ModelAttribute("status") Status status){

        if(statusRepository.findOne(statusF.getId())!=null){
            statusF= statusRepository.findOne(statusF.getId());
            model.addAttribute("statusF",statusF);
        }
        model.addAttribute("statuss", statusRepository.findAll());
        model.addAttribute("status",new Status());
        return "status/status";
    }

    @RequestMapping(value = "/edit_status", method = RequestMethod.POST)
    public String editStatus(Model model,
                             @ModelAttribute("statusF") Status statusF,
                             @ModelAttribute("statuss") Status statuss,
                             @ModelAttribute("status") Status status){

        if(statusRepository.findOne(statusF.getId())!=null){
            statusRepository.save(statusF);
            model.addAttribute("statusF",statusF);
        }
        model.addAttribute("statuss", statusRepository.findAll());
        model.addAttribute("status",new Status());
        return "status/status";
    }
    @RequestMapping(value = "/delete_status", method = RequestMethod.POST)
    public String deleteStatus(Model model,
                               @ModelAttribute("statusF") Status statusF,
                               @ModelAttribute("statuss") Status statuss,
                               @ModelAttribute("status") Status status){

        if(statusRepository.findOne(statusF.getId())!=null){
            statusF= statusRepository.findOne(statusF.getId());
            statusRepository.delete(statusF.getId());
            model.addAttribute("statusF",statusF);
        }
        model.addAttribute("statuss", statusRepository.findAll());
        model.addAttribute("status",new Status());
        return "status/status";
    }
    @RequestMapping(value = "/add_status", method = RequestMethod.POST)
    public String addStatus(Model model,
                            @ModelAttribute("statusF") Status statusF,
                            @ModelAttribute("statuss") Status statuss,
                            @ModelAttribute("status") Status status){

        if (status.getName() != null && status.getName().length() > 0) {
            statusRepository.save(status);
            model.addAttribute("error","Data successfully added");
        }else {
            model.addAttribute("error","Name is required!");
        }
        return "log/log";
    }

}
