package com.help_desk.controller;

import com.help_desk.entity.Quality;
import com.help_desk.repository.QualityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Controller to work with quality
 */
@RequestMapping("/quality")
@Controller
public class QualityController {

    @Autowired
    QualityRepository qualityRepository;

    /**
     * Return all quality on view
     * @param model
     * @param qualityF
     * @return
     */
    @GetMapping
    public String view(Model model, @ModelAttribute("qualityF") Quality qualityF) {
        model.addAttribute("qualitys", qualityRepository.findAll());
        model.addAttribute("quality",new Quality());
        model.addAttribute("qualityF",qualityF);
        return "quality/quality";
    }

    /**
     * Find quality
     * @param model
     * @param qualityF
     * @param bindingResult
     * @return
     */
    @PostMapping("/find_quality")
    public String findQuality(Model model,
                              @Valid
                              @ModelAttribute("qualityF") Quality qualityF,
                            BindingResult bindingResult){
            if (bindingResult.hasErrors()) {
                return "redirect:  ";
            }
        model.addAttribute("qualitys", qualityRepository.findAll());
        model.addAttribute("quality",new Quality());
        if(qualityRepository.findOne(qualityF.getId())!=null){
            qualityF= qualityRepository.findOne(qualityF.getId());
            model.addAttribute("qualityF",qualityF);
        }
        return "quality/quality";
    }

    /**
     * Edit quality
     * @param model
     * @param qualityF
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/edit_quality", method = RequestMethod.POST)
    public String editQuality(Model model,
                              @Valid
                              @ModelAttribute("qualityF") Quality qualityF,
                              BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "redirect:  ";
        }
        if(qualityRepository.findOne(qualityF.getId())!=null){
            qualityRepository.save(qualityF);
            model.addAttribute("qualityF",qualityF);
        }
        model.addAttribute("qualitys", qualityRepository.findAll());
        model.addAttribute("quality",new Quality());
        return "quality/quality";
    }

    /**
     * Delete quality
     * @param model
     * @param qualityF
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/delete_quality", method = RequestMethod.POST)
    public String deleteQuality(Model model,
                                @Valid
                                @ModelAttribute("qualityF") Quality qualityF,
                                BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "redirect:  ";
        }
        if(qualityRepository.findOne(qualityF.getId())!=null) {
            qualityF = qualityRepository.findOne(qualityF.getId());
            try {
                qualityRepository.delete(qualityF.getId());
            } catch (Exception e) {
                model.addAttribute("error","The last line is not deleted");
                return "log/log";
            }
            model.addAttribute("statusF",qualityF);
        }
        model.addAttribute("qualitys", qualityRepository.findAll());
        model.addAttribute("quality",new Quality());
        return "quality/quality";
    }

    /**
     * Adding quality
     * @param model
     * @param quality
     * @return
     */
    @RequestMapping(value = "/add_quality", method = RequestMethod.POST)
    public String addQuality(Model model,
                           @ModelAttribute("quality") Quality quality){

        if (quality.getName() != null && quality.getName().length() > 0) {
            qualityRepository.save(quality);
            model.addAttribute("error","Data successfully added");
        }else {
            model.addAttribute("error","Name is required!");
        }
        return "log/log";
    }

}
