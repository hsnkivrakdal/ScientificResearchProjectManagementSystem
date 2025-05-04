package com.example.srpms.controllers;

import com.example.srpms.services.ActivityAreaTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/activityareatypes")
public class ActivityAreaTypeController {
    @Autowired
    private ActivityAreaTypeService activityAreaTypeService;

    @GetMapping("/list")
    public String listActivityAreaTypes(Model model) {
        model.addAttribute("activityAreaTypes", activityAreaTypeService.getAll());
        return "activityareatypes/get";
    }
}
