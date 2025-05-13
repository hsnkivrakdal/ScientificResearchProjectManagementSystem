package com.example.srpms.controllers;

import com.example.srpms.models.Keywordtype;
import com.example.srpms.services.KeywordTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/keyword-types")
public class KeywordTypeController {

    @Autowired
    private KeywordTypeService keywordTypeService;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("activePage","keyword-types");
        model.addAttribute("keywordTypes", keywordTypeService.getAll());
        return "keywordtypes/index";
    }
    @GetMapping("/create")
    public String shoCreateForm(Model model) {
        model.addAttribute("activePage","keyword-types");
        model.addAttribute("keywordTypes", new Keywordtype());
        return "keywordtypes/create";
    }
    @PostMapping("/create")
    public String create(@ModelAttribute Keywordtype keywordtype, Model model) {
        model.addAttribute("activePage","keyword-types");
        keywordTypeService.add(keywordtype);
        return "redirect:/keyword-types/list";
    }
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Integer id,Model model) {
        model.addAttribute("activePage","keyword-types");
        Keywordtype existing = keywordTypeService.getById(id);
        model.addAttribute("keywordTypes", existing);
        return "keywordtypes/edit";
    }
    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, @ModelAttribute Keywordtype keywordtype, Model model) {
        model.addAttribute("activePage","keyword-types");
        keywordtype.setId(id);
        keywordTypeService.update(keywordtype);
        return "redirect:/keyword-types/list";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, Model model) {
        model.addAttribute("activePage","keyword-types");
        keywordTypeService.deleteById(id);
        return "redirect:/keyword-types/list";
    }
}
