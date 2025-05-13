package com.example.srpms.controllers;

import com.example.srpms.services.ApplicationDescriptiponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("application-description")
public class ApplicationDescriptionController {
    @Autowired
    private ApplicationDescriptiponService applicationDescriptiponService;
}
