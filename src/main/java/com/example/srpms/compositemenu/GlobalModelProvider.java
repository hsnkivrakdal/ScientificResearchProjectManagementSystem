package com.example.srpms.compositemenu;

import com.example.srpms.models.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Component
@ControllerAdvice
public class GlobalModelProvider {

    @Autowired
    private MenuService menuService;

    @ModelAttribute
    public void addCommonAttributes(Model model, HttpSession session, HttpServletRequest request) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            String roleTitle = session.getAttribute("role").toString();
            List<MenuComponent> sidebarMenu = menuService.getMenuForRole(roleTitle);

            model.addAttribute("sidebarMenu", sidebarMenu);
            model.addAttribute("currentUser", user);
        }

        String path = request.getRequestURI();
        String activePage = extractActivePage(path);
        model.addAttribute("activePage", activePage);
    }

    private String extractActivePage(String uri) {
        if (uri == null || uri.isEmpty()) return "home";
        String[] segments = uri.split("/");
        return segments.length > 1 ? segments[1] : "home";
    }
}
