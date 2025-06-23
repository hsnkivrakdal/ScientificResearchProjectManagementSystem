package com.example.srpms.controllers;

import com.example.srpms.models.Lecturer;
import com.example.srpms.models.Lectureraffiliation;
import com.example.srpms.services.LecturerAffiliationService;
import com.example.srpms.services.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("lecturers")
public class LecturerController {
    @Autowired
    private LecturerService lecturerService;

    public Model activePage(Model model){
        return model.addAttribute("activePage","lecturers");
    }

    @GetMapping("/list")
    public String list(Model model) {
        activePage(model);
        model.addAttribute("lecturer", lecturerService.getAll());
        return "lecturers/index";
    }
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        activePage(model);
        model.addAttribute("lecturer", new Lecturer());
        model.addAttribute("lecturerAffiliation", lecturerService.getAllLectureraffiliations());
        model.addAttribute("lecturerTypes", lecturerService.getAllLecturertypes());
        model.addAttribute("lecturerTitles", lecturerService.getAllLecturertitles());
        return "lecturers/create";
    }
    @PostMapping("/create")
    public String createActivityAreatype(@ModelAttribute Lecturer lecturer, Model model) {
        activePage(model);
        lecturerService.add(lecturer);
        return "redirect:/lecturers/list";
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(Model model,@PathVariable Integer id) {
        activePage(model);
        Lecturer existing = lecturerService.getById(id);
        model.addAttribute("lecturer", existing);
        model.addAttribute("lecturerAffiliation", lecturerService.getAllLectureraffiliations());
        model.addAttribute("lecturerTypes", lecturerService.getAllLecturertypes());
        model.addAttribute("lecturerTitles", lecturerService.getAllLecturertitles());
        return "lecturers/edit";
    }
    @PostMapping("/edit/{id}")
    public String updateActivityAreaType(@PathVariable Integer id, Lecturer lecturer, Model model) {
        activePage(model);
        lecturer.setId(id);
        lecturerService.update(lecturer);
        return "redirect:/lecturers/list";
    }
    @GetMapping("/delete/{id}")
    public String deleteActivityAreaType(@PathVariable Integer id, Model model) {
        activePage(model);
        lecturerService.deleteById(id);
        return "redirect:/lecturers/list";
    }
}
