package com.example.srpms.controllers;

import com.example.srpms.models.Projectapplication;
import com.example.srpms.services.ProjectApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/project-application")
public class ProjectApplicationController {
    @Autowired
    private ProjectApplicationService projectApplicationService;

    public Model activePage(Model model) {
        return model.addAttribute("activePage","project-application");
    }
    /*@GetMapping("/index")
    public String index(Model model) {
        activePage(model);
        return "newapplicationlayout";
    }
    @GetMapping("/deneme")
    public String index1(Model model) {
        activePage(model);
        return "application-description";
    }*/
    @GetMapping("/list")
    public String list(Model model) {
        activePage(model);
        model.addAttribute("projectApplication", projectApplicationService.getAll());
        return "projectapplications/index";
    }
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        activePage(model);
        model.addAttribute("projectApplication", new Projectapplication());
        return "projectapplications/create";
    }
    @PostMapping("/create")
    public String createActivityAreatype(@ModelAttribute Projectapplication projectapplication, Model model) {
        activePage(model);
        projectApplicationService.add(projectapplication);
        return "redirect:/project-application/list";
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(Model model,@PathVariable Integer id) {
        activePage(model);
        Projectapplication existing = projectApplicationService.getById(id);
        model.addAttribute("projectApplication", existing);
        return "projectapplications/edit";
    }
    @PostMapping("/edit/{id}")
    public String updateActivityAreaType(@PathVariable Integer id, Projectapplication projectapplication, Model model) {
        activePage(model);
        projectapplication.setId(id);
        projectApplicationService.update(projectapplication);
        return "redirect:/project-application/list";
    }
    @GetMapping("/delete/{id}")
    public String deleteActivityAreaType(@PathVariable Integer id, Model model) {
        activePage(model);
        projectApplicationService.deleteById(id);
        return "redirect:/project-application/list";
    }
}
