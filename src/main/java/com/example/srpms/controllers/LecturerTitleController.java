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

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("activePage","lecturer-title");
        model.addAttribute("lecturerTitle", lecturerTitleService.getAll());
        return "lecturertitle/index";
    }
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("activePage","lecturer-title");
        model.addAttribute("lecturerTitle", new Lecturertitle());
        return "lecturertitle/create";
    }
    @PostMapping("/create")
    public String createActivityAreatype(@ModelAttribute Lecturertitle lecturertitle, Model model) {
        model.addAttribute("activePage","lecturer-title");
        lecturerTitleService.add(lecturertitle);
        return "redirect:/lecturer-title/list";
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(Model model,@PathVariable Integer id) {
        model.addAttribute("activePage","lecturer-title");
        Lecturertitle existing = lecturerTitleService.getById(id);
        model.addAttribute("lecturerTitle", existing);
        return "lecturertitle/edit";
    }
    @PostMapping("/edit/{id}")
    public String updateActivityAreaType(@PathVariable Integer id, Lecturertitle lecturertitle, Model model) {
        model.addAttribute("activePage","lecturer-title");
        lecturertitle.setId(id);
        lecturerTitleService.update(lecturertitle);
        return "redirect:/lecturer-title/list";
    }
    @GetMapping("/delete/{id}")
    public String deleteActivityAreaType(@PathVariable Integer id,Model model) {
        model.addAttribute("activePage","lecturer-title");
        lecturerTitleService.deleteById(id);
        return "redirect:/lecturer-title/list";
    }
}
