package com.example.srpms.controllers;

import com.example.srpms.models.Activityareatype;
import com.example.srpms.services.ActivityAreaTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/activity-area-types")
public class ActivityAreaTypeController {
    @Autowired
    private ActivityAreaTypeService activityAreaTypeService;

    @GetMapping("/list")
    public String listActivityAreaTypes(Model model) {
        model.addAttribute("activityAreaTypes", activityAreaTypeService.getAll());
        return "activityareatypes/get";
    }
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("activityareatype", new Activityareatype());
        return "activityareatypes/post";
    }
    @PostMapping("/create")
    public String createActivityAreatype(@ModelAttribute Activityareatype activityareatype) {
        activityAreaTypeService.add(activityareatype);
        return "redirect:/activity-area-types/list";
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(Model model,@PathVariable Integer id) {
        Activityareatype existing = activityAreaTypeService.getById(id);
        model.addAttribute("activityareatype", existing);
        return "activityareatypes/edit";
    }
    @PostMapping("/edit/{id}")
    public String updateActivityAreaType(@PathVariable Integer id, Activityareatype activityareatype) {
        activityareatype.setId(id);
        activityAreaTypeService.update(activityareatype);
        return "redirect:/activity-area-types/list";
    }
    @GetMapping("/delete/{id}")
    public String deleteActivityAreaType(@PathVariable Integer id) {
        activityAreaTypeService.deleteById(id);
        return "redirect:/activity-area-types/list";
    }
}
