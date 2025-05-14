package com.example.srpms.controllers;

import com.example.srpms.models.Lecturertype;
import com.example.srpms.models.Machinerytype;
import com.example.srpms.services.LecturerTypeService;
import com.example.srpms.services.MachineryTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/machinery-type")
public class MachineryTypeController {
    @Autowired
    private MachineryTypeService machineryTypeService;

    public Model activePage(Model model) {
        return model.addAttribute("activePage","machinery-type");
    }

    @GetMapping("/list")
    public String list(Model model) {
        activePage(model);
        model.addAttribute("machineryType", machineryTypeService.getAll());
        return "machinerytypes/index";
    }
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("machineryType", new Machinerytype());
        return "machinerytypes/create";
    }
    @PostMapping("/create")
    public String createActivityAreatype(@ModelAttribute Machinerytype machinerytype, Model model) {
        activePage(model);
        machineryTypeService.add(machinerytype);
        return "redirect:/machinery-type/list";
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        activePage(model);
        Machinerytype existing = machineryTypeService.getById(id);
        model.addAttribute("machineryType", existing);
        return "machinerytypes/edit";
    }
    @PostMapping("/edit/{id}")
    public String updateActivityAreaType(@PathVariable Integer id, Machinerytype machinerytype, Model model) {
        activePage(model);
        machinerytype.setId(id);
        machineryTypeService.update(machinerytype);
        return "redirect:/machinery-type/list";
    }
    @GetMapping("/delete/{id}")
    public String deleteActivityAreaType(@PathVariable Integer id, Model model) {
        activePage(model);
        machineryTypeService.deleteById(id);
        return "redirect:/machinery-type/list";
    }
}
