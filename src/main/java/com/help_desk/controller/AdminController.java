package com.help_desk.controller;

import com.help_desk.controller.form.AdminRegistrationForm;
import com.help_desk.controller.form.UserRegistrationForm;
import com.help_desk.entity.Admin;
import com.help_desk.entity.Role;
import com.help_desk.entity.User;
import com.help_desk.entity.UserSecurity;
import com.help_desk.repository.AdminRepository;
import com.help_desk.repository.RoleRepository;
import com.help_desk.repository.UserSecurityRepository;
import com.help_desk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

/**
 * Controller to work with admin
 */
@RequestMapping("/admin")
@Controller
public class AdminController {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserSecurityRepository userSecurityRepository;
    @Autowired
    private UserService userService;
    @Autowired
    AdminRepository adminRepository;

    /**
     * Return all admin on view
     * @param model
     * @param adminF
     * @return
     */
    @GetMapping
    public String view(Model model, @ModelAttribute("adminF") Admin adminF) {
        model.addAttribute("admins", adminRepository.findAll());
        model.addAttribute("adminForm",new AdminRegistrationForm());
        model.addAttribute("adminF",adminF);
        return "admin/editAdmins";
    }

    /**
     * Search administrators
     * @param model
     * @param adminF
     * @param bindingResult
     * @return
     */
    @PostMapping("/find_admin")
    public String findAdmin(Model model,
                            @Valid
                            @ModelAttribute("adminF") Admin adminF,
                            BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "redirect: ";
        }

        if(adminRepository.findOne(adminF.getId())!=null){
            adminF= adminRepository.findOne(adminF.getId());
            model.addAttribute("adminF",adminF);
        }
        model.addAttribute("admins", adminRepository.findAll());
        model.addAttribute("adminForm",new AdminRegistrationForm());
        return "admin/editAdmins";
    }

    /**
     * Edit administrators
     * @param model
     * @param adminF
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/edit_admin", method = RequestMethod.POST)
    public String editAdmin(Model model,
                            @Valid
                            @ModelAttribute("adminF") Admin adminF,
                            BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "redirect: ";
        }
        if(adminRepository.findOne(adminF.getId())!=null){
            adminRepository.save(adminF);
            model.addAttribute("adminF",adminF);
        }
        model.addAttribute("admins", adminRepository.findAll());
        model.addAttribute("adminForm",new AdminRegistrationForm());
        return "admin/editAdmins";
    }

    /**
     * Delete administrators
     * @param model
     * @param adminF
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/delete_admin", method = RequestMethod.POST)
    public String deleteAdmin(Model model,
                              @Valid
                              @ModelAttribute("adminF") Admin adminF,
                              BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "redirect: ";
        }
        if(adminRepository.findOne(adminF.getId())!=null){
            adminF= adminRepository.findOne(adminF.getId());
            try {
                adminRepository.delete(adminF.getId());
            } catch (Exception e) {
                model.addAttribute("error","The last line is not deleted");
                return "log/log";
            }
            model.addAttribute("adminF",adminF);
        }
        model.addAttribute("admins", adminRepository.findAll());
        model.addAttribute("adminForm",new AdminRegistrationForm());
        return "admin/editAdmins";
    }

    /**
     * Adding administrators
     * @param model
     * @param adminForm
     * @return
     */
    @RequestMapping(value = "/add_admin", method = RequestMethod.POST)
    public String addAdmin(Model model,
                           @ModelAttribute("adminForm") AdminRegistrationForm adminForm) {
        if(     adminForm==null||
                adminForm.getUsername()==null||
                adminForm.getPassword()==null||
                adminForm.getConfirmPassword()==null||
                adminForm.getPassword().equals("")||
                adminForm.getConfirmPassword().equals("")||
                !adminForm.getPassword().equals(adminForm.getConfirmPassword())){
            model.addAttribute("error","Incorrect data");
            return "log/log";
        }
        UserSecurity userUP = new UserSecurity();
        userUP.setUsername(adminForm.getUsername());
        userUP.setPassword(adminForm.getPassword());
        try{
            userService.signupUser(userUP,2L);
        }catch(Exception e){
            model.addAttribute("error","The Login must be unique");
            return "log/log";
        }

        Admin admin=new Admin(adminForm.getAdmin().getName(),
        userSecurityRepository.findByUsername(adminForm.getUsername()).getId());
        if (admin.getName() != null && admin.getName().length() > 0) {
            adminRepository.save(admin);
            model.addAttribute("error","Data successfully added");
        }else {
            userSecurityRepository.delete(userUP);
            model.addAttribute("error","Name is required!");
        }
        return "log/log";
    }

}