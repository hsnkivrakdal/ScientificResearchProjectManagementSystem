package com.example.srpms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
    @GetMapping("/index")
    public String index() {
        return "layout";
    }
    @GetMapping("/admin")
    public String admin() {
        return "adminlayout";
    }
    @GetMapping("/lecturer")
    public String lecturer() {
        return "lecturerlayout";
    }
    @GetMapping("/applicationlayout")
    public String applicationlayout() {
        return "newapplicationlayout";
    }
}
