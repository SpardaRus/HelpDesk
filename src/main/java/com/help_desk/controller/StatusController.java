package com.help_desk.controller;

import com.help_desk.entity.Quality;
import com.help_desk.entity.Status;
import com.help_desk.repository.QualityRepository;
import com.help_desk.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Controller to work with status
 */
@RequestMapping("/status")
@Controller
public class StatusController {

    @Autowired
    StatusRepository statusRepository;

    /**
     * Return all statuses on view
     * @param model
     * @param statusF
     * @return
     */
    @GetMapping
    public String view(Model model, @ModelAttribute("statusF") Status statusF) {
        model.addAttribute("statuss", statusRepository.findAll());
        model.addAttribute("status",new Status());
        model.addAttribute("statusF",statusF);
        return "status/status";
    }

    /**
     * Find status
     * @param model
     * @param statusF
     * @param bindingResult
     * @return
     */
    @PostMapping("/find_status")
    public String findStatus(Model model,
                             @Valid
                             @ModelAttribute("statusF") Status statusF,
                             BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "redirect:  ";
        }
        if(statusRepository.findOne(statusF.getId())!=null){
            statusF= statusRepository.findOne(statusF.getId());
            model.addAttribute("statusF",statusF);
        }
        model.addAttribute("statuss", statusRepository.findAll());
        model.addAttribute("status",new Status());
        return "status/status";
    }

    /**
     * Edit status
     * @param model
     * @param statusF
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/edit_status", method = RequestMethod.POST)
    public String editStatus(Model model,
                             @Valid
                             @ModelAttribute("statusF") Status statusF,
                             BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "redirect:  ";
        }
        if(statusRepository.findOne(statusF.getId())!=null){
            statusRepository.save(statusF);
            model.addAttribute("statusF",statusF);
        }
        model.addAttribute("statuss", statusRepository.findAll());
        model.addAttribute("status",new Status());
        return "status/status";
    }

    /**
     * Delete status
     * @param model
     * @param statusF
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/delete_status", method = RequestMethod.POST)
    public String deleteStatus(Model model,
                               @Valid
                               @ModelAttribute("statusF") Status statusF,
                               BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "redirect:  ";
        }
        if(statusRepository.findOne(statusF.getId())!=null) {
            statusF = statusRepository.findOne(statusF.getId());


            try {
                statusRepository.delete(statusF.getId());
            } catch (Exception e) {
                model.addAttribute("error","The last line is not deleted");
                return "log/log";
            }
            model.addAttribute("statusF",statusF);
        }
        model.addAttribute("statuss", statusRepository.findAll());
        model.addAttribute("status",new Status());
        return "status/status";
    }

    /**
     * Adding status
     * @param model
     * @param statusF
     * @param statuss
     * @param status
     * @return
     */
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
