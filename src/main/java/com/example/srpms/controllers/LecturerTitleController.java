package com.example.srpms.controllers;

import com.example.srpms.models.Lectureraffiliation;
import com.example.srpms.models.Lecturertitle;
import com.example.srpms.services.LecturerAffiliationService;
import com.example.srpms.services.LecturerTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/lecturer-title")
public class LecturerTitleController {

    @Autowired
    private LecturerTitleService lecturerTitleService;

    public Model activePage(Model model) {
        return model.addAttribute("activePage","lecturer-type");
    }

    @GetMapping("/list")
    public String list(Model model) {
        activePage(model);
        model.addAttribute("lecturerTitle", lecturerTitleService.getAll());
        return "lecturertitle/index";
    }
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        activePage(model);
        model.addAttribute("lecturerTitle", new Lecturertitle());
        return "lecturertitle/create";
    }
    @PostMapping("/create")
    public String createActivityAreatype(@ModelAttribute Lecturertitle lecturertitle, Model model) {
        activePage(model);
        lecturerTitleService.add(lecturertitle);
        return "redirect:/lecturer-title/list";
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(Model model,@PathVariable Integer id) {
        activePage(model);
        Lecturertitle existing = lecturerTitleService.getById(id);
        model.addAttribute("lecturerTitle", existing);
        return "lecturertitle/edit";
    }
    @PostMapping("/edit/{id}")
    public String updateActivityAreaType(@PathVariable Integer id, Lecturertitle lecturertitle, Model model) {
        activePage(model);
        lecturertitle.setId(id);
        lecturerTitleService.update(lecturertitle);
        return "redirect:/lecturer-title/list";
    }
    @GetMapping("/delete/{id}")
    public String deleteActivityAreaType(@PathVariable Integer id,Model model) {
        activePage(model);
        lecturerTitleService.deleteById(id);
        return "redirect:/lecturer-title/list";
    }
}
