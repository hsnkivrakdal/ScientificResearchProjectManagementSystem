package com.example.srpms.controllers;

import com.example.srpms.models.Machinerytype;
import com.example.srpms.models.Personneltype;
import com.example.srpms.services.MachineryTypeService;
import com.example.srpms.services.PersonnelTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/personnel-type")
public class PersonnelTypeController {

    @Autowired
    private PersonnelTypeService personnelTypeService;

    public Model activePage(Model model) {
        return model.addAttribute("activePage","personnel-type");
    }

    @GetMapping("/list")
    public String list(Model model) {
        activePage(model);
        model.addAttribute("personnelType", personnelTypeService.getAll());
        return "personneltypes/index";
    }
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        activePage(model);
        model.addAttribute("personnelType", new Personneltype());
        return "personneltypes/create";
    }
    @PostMapping("/create")
    public String createActivityAreatype(@ModelAttribute Personneltype personneltype, Model model) {
        activePage(model);
        personnelTypeService.add(personneltype);
        return "redirect:/personnel-type/list";
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(Model model,@PathVariable Integer id) {
        activePage(model);
        Personneltype existing = personnelTypeService.getById(id);
        model.addAttribute("personnelType", existing);
        return "personneltypes/edit";
    }
    @PostMapping("/edit/{id}")
    public String updateActivityAreaType(@PathVariable Integer id, Personneltype personneltype, Model model) {
        activePage(model);
        personneltype.setId(id);
        personnelTypeService.update(personneltype);
        return "redirect:/personnel-type/list";
    }
    @GetMapping("/delete/{id}")
    public String deleteActivityAreaType(@PathVariable Integer id, Model model) {
        activePage(model);
        personnelTypeService.deleteById(id);
        return "redirect:/personnel-type/list";
    }
}
