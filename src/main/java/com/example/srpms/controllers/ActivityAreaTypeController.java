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
        model.addAttribute("activePage","activity-area-types");
        model.addAttribute("activityAreaTypes", activityAreaTypeService.getAll());
        return "activityareatypes/index";
    }
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("activePage","activity-area-types");
        model.addAttribute("activityAreaTypes", new Activityareatype());
        return "activityareatypes/create";
    }
    @PostMapping("/create")
    public String createActivityAreatype(@ModelAttribute Activityareatype activityareatype,Model model) {
        model.addAttribute("activePage","activity-area-types");
        activityAreaTypeService.add(activityareatype);
        return "redirect:/activity-area-types/list";
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(Model model,@PathVariable Integer id) {
        model.addAttribute("activePage","activity-area-types");
        Activityareatype existing = activityAreaTypeService.getById(id);
        model.addAttribute("activityAreaTypes", existing);
        return "activityareatypes/edit";
    }
    @PostMapping("/edit/{id}")
    public String updateActivityAreaType(@PathVariable Integer id, Activityareatype activityareatype,Model model) {
        model.addAttribute("activePage","activity-area-types");
        activityareatype.setId(id);
        activityAreaTypeService.update(activityareatype);
        return "redirect:/activity-area-types/list";
    }
    @GetMapping("/delete/{id}")
    public String deleteActivityAreaType(@PathVariable Integer id,Model model) {
        model.addAttribute("activePage","activity-area-types");
        activityAreaTypeService.deleteById(id);
        return "redirect:/activity-area-types/list";
    }
}
