package com.example.srpms.controllers;

import com.example.srpms.models.Lecturer;
import com.example.srpms.models.Role;
import com.example.srpms.models.User;
import com.example.srpms.services.LecturerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private LecturerService lecturerService;

    @GetMapping("/index")
    public String index(Model model,
                        HttpSession session) {
        model.addAttribute("activePage", "home");
        User user = (User) session.getAttribute("user");
        session.setAttribute("role", user.getUserinroles().stream().findFirst().map(r -> r.getRole().getRoleTitle()).orElse("GUEST"));

        Set<Role> roles = (Set<Role>) session.getAttribute("roles");
        model.addAttribute("user", user);
        return "home/index";
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
