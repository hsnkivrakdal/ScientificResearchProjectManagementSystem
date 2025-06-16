package com.example.srpms.controllers;

import com.example.srpms.models.Project;
import com.example.srpms.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/project-continue")
public class ProjectContinueController {
    @Autowired
    public ProjectService projectService;

    public Model activePage(Model model) {
        return model.addAttribute("activePage","projectContinue");
    }

    @GetMapping("/index")// list all projects to continue application
    public String listAll(Model model) {
        activePage(model);
        model.addAttribute("project", projectService.getAll());
        return "projects/index";
    }

    @GetMapping("/continue/{projectId}")//continue button direction to application which is started
    public String continueapplicationDescription(@PathVariable Integer projectId, Model model) {
        activePage(model);
        Project project = projectService.getById(projectId);
        if (project == null) {
            throw new IllegalArgumentException("Project not found for ID: " + projectId);
        }
        model.addAttribute("project", project);
        return "redirect:/project/step/DESCRIPTION/" + projectId;
    }

    @GetMapping("/delete/{projectId}")
    public String deleteProjectApplication(@PathVariable Integer projectId, Model model) {
        activePage(model);
        projectService.deleteProjectApplication(projectId);
        return "redirect:/project-continue/index";
    }


    @GetMapping("/show-submitted-projects")
    public String showSubmittedProject(Model model) {
        model.addAttribute("activePage","showSubmittedProject");
        model.addAttribute("project", projectService.getSubmittedProjects());
        return "submittedprojects/index";
    }

    @PostMapping("/approve/{projectId}")
    public String approveProject(@PathVariable Integer projectId) {
        Project project = projectService.getById(projectId);
        if (project == null || !Boolean.TRUE.equals(project.getIsSubmitted())) {
            throw new IllegalArgumentException("Project not found or not submitted yet.");
        }
        project.setIsEvaluated(true);
        project.setIsApproved(true);
        projectService.add(project);
        return "redirect:/project-continue/approved-projects";
    }

    @PostMapping("/approve-not/{projectId}")
    public String approveNotProject(@PathVariable Integer projectId) {
        Project project = projectService.getById(projectId);
        if (project == null || !Boolean.TRUE.equals(project.getIsSubmitted())) {
            throw new IllegalArgumentException("Project not found or not submitted yet.");
        }
        project.setIsEvaluated(true);
        project.setIsApproved(false);
        projectService.add(project);
        return "redirect:/project-continue/show-submitted-projects";
    }

    @GetMapping("/approved-projects")
    public String showApprovedProject(Model model) {
        model.addAttribute("activePage","approvedProjects");
        model.addAttribute("project", projectService.getApprovedProjects());
        return "approvedproject/index";
    }

}
