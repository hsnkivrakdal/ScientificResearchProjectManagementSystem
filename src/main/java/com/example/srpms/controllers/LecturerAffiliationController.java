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

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("activePage","lecturer-affiliation");
        model.addAttribute("lecturerAffiliation", lecturerAffiliationService.getAll());
        return "lectureraffiliation/index";
    }
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("activePage","lecturer-affiliation");
        model.addAttribute("lecturerAffiliation", new Lectureraffiliation());
        return "lectureraffiliation/create";
    }
    @PostMapping("/create")
    public String createActivityAreatype(@ModelAttribute Lectureraffiliation lectureraffiliation, Model model) {
        model.addAttribute("activePage","lecturer-affiliation");
        lecturerAffiliationService.add(lectureraffiliation);
        return "redirect:/lecturer-affiliation/list";
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(Model model,@PathVariable Integer id) {
        model.addAttribute("activePage","lecturer-affiliation");
        Lectureraffiliation existing = lecturerAffiliationService.getById(id);
        model.addAttribute("lecturerAffiliation", existing);
        return "lectureraffiliation/edit";
    }
    @PostMapping("/edit/{id}")
    public String updateActivityAreaType(@PathVariable Integer id, Lectureraffiliation lectureraffiliation, Model model) {
        model.addAttribute("activePage","lecturer-affiliation");
        lectureraffiliation.setId(id);
        lecturerAffiliationService.update(lectureraffiliation);
        return "redirect:/lecturer-affiliation/list";
    }
    @GetMapping("/delete/{id}")
    public String deleteActivityAreaType(@PathVariable Integer id,Model model) {
        model.addAttribute("activePage","lecturer-affiliation");
        lecturerAffiliationService.deleteById(id);
        return "redirect:/lecturer-affiliation/list";
    }
}
