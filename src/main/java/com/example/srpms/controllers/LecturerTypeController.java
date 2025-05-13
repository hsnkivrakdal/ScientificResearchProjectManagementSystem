package com.example.srpms.controllers;

import com.example.srpms.models.Lecturertitle;
import com.example.srpms.models.Lecturertype;
import com.example.srpms.services.LecturerTitleService;
import com.example.srpms.services.LecturerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/lecturer-type")
public class LecturerTypeController {

    @Autowired
    private LecturerTypeService lecturerTypeService;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("activePage","lecturer-type");
        model.addAttribute("lecturerType", lecturerTypeService.getAll());
        return "lecturertype/index";
    }
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("activePage","lecturer-type");
        model.addAttribute("lecturerType", new Lecturertype());
        return "lecturertype/create";
    }
    @PostMapping("/create")
    public String createActivityAreatype(@ModelAttribute Lecturertype lecturertype, Model model) {
        model.addAttribute("activePage","lecturer-type");
        lecturerTypeService.add(lecturertype);
        return "redirect:/lecturer-type/list";
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(Model model,@PathVariable Integer id) {
        model.addAttribute("activePage","lecturer-type");
        Lecturertype existing = lecturerTypeService.getById(id);
        model.addAttribute("lecturerType", existing);
        return "lecturertype/edit";
    }
    @PostMapping("/edit/{id}")
    public String updateActivityAreaType(@PathVariable Integer id, Lecturertype lecturertype, Model model) {
        model.addAttribute("activePage","lecturer-type");
        lecturertype.setId(id);
        lecturerTypeService.update(lecturertype);
        return "redirect:/lecturer-type/list";
    }
    @GetMapping("/delete/{id}")
    public String deleteActivityAreaType(@PathVariable Integer id,Model model) {
        model.addAttribute("activePage","lecturer-type");
        lecturerTypeService.deleteById(id);
        return "redirect:/lecturer-type/list";
    }

}
