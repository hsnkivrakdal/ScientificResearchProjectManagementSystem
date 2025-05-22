package com.example.srpms.controllers;

import com.example.srpms.models.Personneltype;
import com.example.srpms.models.Projecttype;
import com.example.srpms.services.PersonnelTypeService;
import com.example.srpms.services.ProjectTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/project-type")
public class ProjectTypeController {

    @Autowired
    private ProjectTypeService projectTypeService;

    public Model activePage(Model model) {
        return model.addAttribute("activePage","project-type");
    }

    @GetMapping("/list")
    public String list(Model model) {
        activePage(model);
        model.addAttribute("projectType", projectTypeService.getAll());
        return "projecttypes/index";
    }
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        activePage(model);
        model.addAttribute("projectType", new Projecttype());
        return "projecttypes/create";
    }
    @PostMapping("/create")
    public String createActivityAreatype(@ModelAttribute Projecttype projecttype, Model model) {
        activePage(model);
        projectTypeService.add(projecttype);
        return "redirect:/project-type/list";
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(Model model,@PathVariable Integer id) {
        activePage(model);
        Projecttype existing = projectTypeService.getById(id);
        model.addAttribute("projectType", existing);
        return "projecttypes/edit";
    }
    @PostMapping("/edit/{id}")
    public String updateActivityAreaType(@PathVariable Integer id, Projecttype projecttype, Model model) {
        activePage(model);
        projecttype.setId(id);
        projectTypeService.update(projecttype);
        return "redirect:/project-type/list";
    }
    @GetMapping("/delete/{id}")
    public String deleteActivityAreaType(@PathVariable Integer id, Model model) {
        activePage(model);
        projectTypeService.deleteById(id);
        return "redirect:/project-type/list";
    }
}
