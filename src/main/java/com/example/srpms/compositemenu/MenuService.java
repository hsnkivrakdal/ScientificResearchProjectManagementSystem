package com.example.srpms.compositemenu;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuService {

    public List<MenuComponent> getMenuForRole(String roleTitle) {
        List<MenuComponent> menu = new ArrayList<>();

        if ("admin".equalsIgnoreCase(roleTitle)) {
            menu.addAll(adminMenu());
        } else if ("lecturer".equalsIgnoreCase(roleTitle)) {
            menu.addAll(lecturerMenu());
        } else if ("committee".equalsIgnoreCase(roleTitle)) {
            menu.addAll(committeeMenu());
        }

        return menu;
    }

    private List<MenuComponent> adminMenu() {
        return List.of(
                new MenuItem("Home", "fas fa-home", "/home/index"),
                new MenuItem("Keyword Types", "fas fa-tags", "/keyword-types/list"),
                new MenuItem("Activity Area Type", "fas fa-code-fork", "/activity-area-types/list"),
                new MenuItem("Lecturer Affiliation", "far fa-star", "/lecturer-affiliation/list"),
                new MenuItem("Lecturer Title", "fas fa-id-badge", "/lecturer-title/list"),
                new MenuItem("Lecturer Type", "fas fa-quote-right", "/lecturer-type/list"),
                new MenuItem("Lecturers","fas fa-user", "/lecturers/list"),
                new MenuItem("Machinery Types", "fas fa-cogs", "/machinery-type/list"),
                new MenuItem("Personnel Type", "fas fa-users", "/personnel-type/list"),
                new MenuItem("Coordinator Positions","fas fa-briefcase", "/coordinator-positions/list"),
                new MenuItem("Technology Readiness Level Type", "fas fa-rocket", "/technology-readiness-level-type/list"),
                new MenuItem("Project Applications", "fas fa-folder-open", "/project-application/list"),
                new MenuItem("New Project", "fas fa-plus-circle", "/project/list"),
                new MenuItem("Continue Project Form", "fas fa-caret-square-o-right", "/project-continue/index"),
                new MenuItem("Submitted Projects", "fas fa-edit", "/project-continue/show-submitted-projects"),
                new MenuItem("Approved Projects", "fas fa-check-circle", "/project-continue/approved-projects")
        );
    }

    private List<MenuComponent> lecturerMenu() {
        return List.of(
                new MenuItem("Home", "fas fa-home", "/home/index"),
                new MenuItem("New Project", "fas fa-plus-circle", "/project/list"),
                new MenuItem("Continue Application", "fas fa-caret-square-o-right", "/project-continue/index"),
                new MenuItem("Submitted Projects", "fas fa-edit", "/project-continue/show-submitted-projects"),
                new MenuItem("Approved Projects", "fas fa-check-circle", "/project-continue/approved-projects")
        );
    }

    private List<MenuComponent> committeeMenu() {
        return List.of(
                new MenuItem("Home", "fas fa-home", "/home/index"),
                new MenuItem("Submitted Projects", "fas fa-edit", "/project-continue/show-submitted-projects"),
                new MenuItem("Approved Projects", "fas fa-check-circle", "/project-continue/approved-projects")
        );
    }
}
