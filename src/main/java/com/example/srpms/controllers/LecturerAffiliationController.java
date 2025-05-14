package com.example.srpms.controllers;

import com.example.srpms.models.Activityareatype;
import com.example.srpms.models.Lectureraffiliation;
import com.example.srpms.services.LecturerAffiliationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/lecturer-affiliation")
public class LecturerAffiliationController {

    @Autowired
    private LecturerAffiliationService lecturerAffiliationService;

    public Model activePage(Model model){
        return model.addAttribute("activePage","lecturer-affiliation");
    }

    @GetMapping("/list")
    public String list(Model model) {
        activePage(model);
        model.addAttribute("lecturerAffiliation", lecturerAffiliationService.getAll());
        return "lectureraffiliation/index";
    }
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        activePage(model);
        model.addAttribute("lecturerAffiliation", new Lectureraffiliation());
        return "lectureraffiliation/create";
    }
    @PostMapping("/create")
    public String createActivityAreatype(@ModelAttribute Lectureraffiliation lectureraffiliation, Model model) {
        activePage(model);
        lecturerAffiliationService.add(lectureraffiliation);
        return "redirect:/lecturer-affiliation/list";
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(Model model,@PathVariable Integer id) {
        activePage(model);
        Lectureraffiliation existing = lecturerAffiliationService.getById(id);
        model.addAttribute("lecturerAffiliation", existing);
        return "lectureraffiliation/edit";
    }
    @PostMapping("/edit/{id}")
    public String updateActivityAreaType(@PathVariable Integer id, Lectureraffiliation lectureraffiliation, Model model) {
        activePage(model);
        lectureraffiliation.setId(id);
        lecturerAffiliationService.update(lectureraffiliation);
        return "redirect:/lecturer-affiliation/list";
    }
    @GetMapping("/delete/{id}")
    public String deleteActivityAreaType(@PathVariable Integer id, Model model) {
        activePage(model);
        lecturerAffiliationService.deleteById(id);
        return "redirect:/lecturer-affiliation/list";
    }
}
