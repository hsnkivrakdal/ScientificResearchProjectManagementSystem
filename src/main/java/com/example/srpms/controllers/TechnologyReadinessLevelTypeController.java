package com.example.srpms.controllers;

import com.example.srpms.models.Projecttype;
import com.example.srpms.models.Technologyreadinessleveltype;
import com.example.srpms.services.ProjectTypeService;
import com.example.srpms.services.TechnologyReadinessLevelTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/technology-readiness-level-type")
public class TechnologyReadinessLevelTypeController {
    @Autowired
    private TechnologyReadinessLevelTypeService technologyReadinessLevelTypeService;

    public Model activePage(Model model) {
        return model.addAttribute("activePage","technology-readiness-level-type");
    }

    @GetMapping("/list")
    public String list(Model model) {
        activePage(model);
        model.addAttribute("technologyReadinessLevelType", technologyReadinessLevelTypeService.getAll());
        return "technologyreadinessleveltypes/index";
    }
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        activePage(model);
        model.addAttribute("technologyReadinessLevelType", new Technologyreadinessleveltype());
        return "technologyreadinessleveltypes/create";
    }
    @PostMapping("/create")
    public String createActivityAreatype(@ModelAttribute Technologyreadinessleveltype technologyreadinessleveltype, Model model) {
        activePage(model);
        technologyReadinessLevelTypeService.add(technologyreadinessleveltype);
        return "redirect:/technology-readiness-level-type/list";
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(Model model,@PathVariable Integer id) {
        activePage(model);
        Technologyreadinessleveltype existing = technologyReadinessLevelTypeService.getById(id);
        model.addAttribute("technologyReadinessLevelType", existing);
        return "technologyreadinessleveltypes/edit";
    }
    @PostMapping("/edit/{id}")
    public String updateActivityAreaType(@PathVariable Integer id, Technologyreadinessleveltype technologyreadinessleveltype, Model model) {
        activePage(model);
        technologyreadinessleveltype.setId(id);
        technologyReadinessLevelTypeService.update(technologyreadinessleveltype);
        return "redirect:/technology-readiness-level-type/list";
    }
    @GetMapping("/delete/{id}")
    public String deleteActivityAreaType(@PathVariable Integer id, Model model) {
        activePage(model);
        technologyReadinessLevelTypeService.deleteById(id);
        return "redirect:/technology-readiness-level-type/list";
    }
}
