package com.example.srpms.controllers;

import com.example.srpms.models.Coordinatorposition;
import com.example.srpms.models.Lecturer;
import com.example.srpms.services.CoordinatorPositionService;
import com.example.srpms.services.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/coordinator-positions")
public class CoordinatorPositionController {
    @Autowired
    private CoordinatorPositionService coordinatorPositionService;

    public Model activePage(Model model){
        return model.addAttribute("activePage","coordinator-positions");
    }

    @GetMapping("/list")
    public String list(Model model) {
        activePage(model);
        model.addAttribute("coordinatorPosition", coordinatorPositionService.getAll());
        return "coordinatorpositions/index";
    }
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        activePage(model);
        model.addAttribute("coordinatorPosition", new Coordinatorposition());
        return "coordinatorpositions/create";
    }
    @PostMapping("/create")
    public String createActivityAreatype(@ModelAttribute Coordinatorposition coordinatorposition, Model model) {
        activePage(model);
        coordinatorPositionService.add(coordinatorposition);
        return "redirect:/coordinator-positions/list";
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(Model model,@PathVariable Integer id) {
        activePage(model);
        Coordinatorposition existing = coordinatorPositionService.getById(id);
        model.addAttribute("coordinatorPosition", existing);
        return "coordinatorpositions/edit";
    }
    @PostMapping("/edit/{id}")
    public String updateActivityAreaType(@PathVariable Integer id, Coordinatorposition coordinatorposition, Model model) {
        activePage(model);
        coordinatorposition.setId(id);
        coordinatorPositionService.update(coordinatorposition);
        return "redirect:/coordinator-positions/list";
    }
    @GetMapping("/delete/{id}")
    public String deleteActivityAreaType(@PathVariable Integer id, Model model) {
        activePage(model);
        coordinatorPositionService.deleteById(id);
        return "redirect:/coordinator-positions/list";
    }
}
