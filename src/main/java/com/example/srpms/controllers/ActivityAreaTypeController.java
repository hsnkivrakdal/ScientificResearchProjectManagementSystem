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

    public Model activePage(Model model) {
        return model.addAttribute("activePage","activity-area-types");
    }

    @GetMapping("/list")
    public String listActivityAreaTypes(Model model) {
        activePage(model);
        model.addAttribute("activityAreaTypes", activityAreaTypeService.getAll());
        return "activityareatypes/index";
    }
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        activePage(model);
        model.addAttribute("activityAreaTypes", new Activityareatype());
        return "activityareatypes/create";
    }
    @PostMapping("/create")
    public String createActivityAreatype(@ModelAttribute Activityareatype activityareatype, Model model) {
        activePage(model);
        activityAreaTypeService.add(activityareatype);
        return "redirect:/activity-area-types/list";
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(Model model,@PathVariable Integer id) {
        activePage(model);
        Activityareatype existing = activityAreaTypeService.getById(id);
        model.addAttribute("activityAreaTypes", existing);
        return "activityareatypes/edit";
    }
    @PostMapping("/edit/{id}")
    public String updateActivityAreaType(@PathVariable Integer id, Activityareatype activityareatype, Model model) {
        activePage(model);
        activityareatype.setId(id);
        activityAreaTypeService.update(activityareatype);
        return "redirect:/activity-area-types/list";
    }
    @GetMapping("/delete/{id}")
    public String deleteActivityAreaType(@PathVariable Integer id, Model model) {
        activePage(model);
        activityAreaTypeService.deleteById(id);
        return "redirect:/activity-area-types/list";
    }
}
