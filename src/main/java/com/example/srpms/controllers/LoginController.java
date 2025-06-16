package com.example.srpms.controllers;

import com.example.srpms.models.Lecturer;
import com.example.srpms.models.Role;
import com.example.srpms.models.User;
import com.example.srpms.models.Userinrole;
import com.example.srpms.services.LecturerService;
import com.example.srpms.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/authentication")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private LecturerService lecturerService;

    @GetMapping("/login")
    public String loginPage() {
        return "login/index";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {

        User user = userService.findByEmailAndPassword(email, password);

        if (user == null) {
            model.addAttribute("error", "Invalid email or password.");
            return "login/index";
        }

        session.setAttribute("user", user);

        session.setAttribute("role", user.getUserinroles().stream().findFirst().map(r -> r.getRole().getRoleTitle()).orElse("GUEST"));

        Lecturer lecturer = lecturerService.findByMailAddress(user.getUserEmail());
        if (lecturer != null) {
            session.setAttribute("lecturer", lecturer);
        }

        return "redirect:/home/index";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
