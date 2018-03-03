package com.help_desk.controller;

import com.help_desk.entity.Admin;
import com.help_desk.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/admin")
@Controller
public class AdminController {

    @Autowired
    AdminRepository adminRepository;


    @GetMapping("/view_admin")
    public String view(Model model, @ModelAttribute("adminF") Admin adminF) {
        model.addAttribute("admins", adminRepository.findAll());
        model.addAttribute("admin",new Admin());
        model.addAttribute("adminF",adminF);
        return "admin/editAdmins";
    }
    @PostMapping("/find_admin")
    public String findAdmin(Model model,
                           @ModelAttribute("adminF") Admin adminF,
                           @ModelAttribute("admins") Admin admins,
                           @ModelAttribute("admin") Admin admin){
        model.addAttribute("admins", adminRepository.findAll());
        model.addAttribute("admin",new Admin());
        if(adminRepository.findById(adminF.getId())!=null){
            adminF= adminRepository.findById(adminF.getId());
            model.addAttribute("adminF",adminF);
        }
        return "admin/editAdmins";
    }

    @RequestMapping(value = "/edit_admin", method = RequestMethod.POST)
    public String editAdmin(Model model,
                           @ModelAttribute("adminF") Admin adminF,
                           @ModelAttribute("admins") Admin admins,
                           @ModelAttribute("admin") Admin admin){

        if(adminRepository.findById(adminF.getId())!=null){
            adminRepository.setAdmin(adminF.getId(),adminF.getName());
            model.addAttribute("adminF",adminF);
        }
        model.addAttribute("admins", adminRepository.findAll());
        model.addAttribute("admin",new Admin());
        return "admin/editAdmins";
    }
    @RequestMapping(value = "/delete_admin", method = RequestMethod.POST)
    public String deleteAdmin(Model model,
                              @ModelAttribute("adminF") Admin adminF,
                              @ModelAttribute("admins") Admin admins,
                              @ModelAttribute("admin") Admin admin){

        if(adminRepository.findById(adminF.getId())!=null){
            adminF= adminRepository.findById(adminF.getId());
            adminRepository.delete(adminF.getId());
            model.addAttribute("adminF",adminF);
        }
        model.addAttribute("admins", adminRepository.findAll());
        model.addAttribute("admin",new Admin());
        return "admin/editAdmins";
    }
    @RequestMapping(value = "/add_admin", method = RequestMethod.POST)
    public String addAdmin(Model model,
                            @ModelAttribute("adminF") Admin adminF,
                            @ModelAttribute("admins") Admin admins,
                            @ModelAttribute("admin") Admin admin) {

        if (admin.getName() != null && admin.getName().length() > 0) {
            adminRepository.save(admin);
            return "admin/addAdminOk";
        }else {
            return "admin/addAdminError";
        }
    }

}