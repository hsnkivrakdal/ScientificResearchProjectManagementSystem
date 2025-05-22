package com.example.srpms.controllers;

import com.example.srpms.applicationstep.ApplicationStep;
import com.example.srpms.models.Project;
import com.example.srpms.models.Projectapplication;
import com.example.srpms.models.Projectcoordinatorinformation;
import com.example.srpms.services.ProjectCoordinatorInformationService;
import com.example.srpms.services.ProjectService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.Year;

@Controller
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    public Model activePage(Model model) {
        return model.addAttribute("activeStep","project");
    }

    @GetMapping("/step/{step}/{projectId}")
    public String step(Model model,
                       @PathVariable("step") String step,
                       @PathVariable("projectId") Integer projectId) {
        ApplicationStep currentStep = ApplicationStep.valueOf(step.toUpperCase());
        Project project = projectService.getById(projectId);
        if (project == null) {
            throw new RuntimeException("Project not found with Id: " + projectId);
        }
        model.addAttribute("currentStep", currentStep);
        model.addAttribute("project", project);


        switch (currentStep) {
            case DESCRIPTION:
                return "projects/application-description";

            case COORDINATOR:
                Projectcoordinatorinformation pci = new Projectcoordinatorinformation();
                pci.setProject(project);
                model.addAttribute("projectCoordinatorInf", pci);
                model.addAttribute("positions", projectService.getCoordinatorPositions());
                return "projects/coordinator-information";
            default:
                throw new IllegalStateException("Unknown step: " + step);
        }

    }
    @GetMapping("/list")//list project applications
    public String list(Model model, Projectapplication projectapplication) {
        activePage(model);
        model.addAttribute("projectApplication", projectService.getProjectApplications(projectapplication));
        return "projects/applying-page";
    }

    @GetMapping("/index")// list all projects to continue application
    public String listAll(Model model) {
        activePage(model);
        model.addAttribute("project", projectService.getAll());
        return "projects/index";
    }

    @GetMapping("/continue/{projectId}")//continue button direction to application which is started
    public String continueapplicationDescription(@PathVariable Integer projectId,Model model) {
        activePage(model);
        Project project = projectService.getById(projectId);
        if (project == null) {
            throw new IllegalArgumentException("Project not found for ID: " + projectId);
        }
        model.addAttribute("project", project);
        return "redirect:/project/step/DESCRIPTION/" + projectId;
    }

    @GetMapping("/description/{applicationId}")//application description page for new application
    public String applicationDescriptionWithNewProject(@PathVariable Integer applicationId,Model model) {
        activePage(model);
        var newProjectWithApplication =  projectService.getProjectApplicationById(applicationId);
        Project project = projectService.createProjectWithApp(newProjectWithApplication);
        model.addAttribute("project", project);
        return "redirect:/project/step/DESCRIPTION/" + project.getId();
    }

    @PostMapping("/step/COORDINATOR/{projectId}")
    public String createCoordinatorInf(@PathVariable Integer projectId,
                                       @ModelAttribute Projectcoordinatorinformation pci,
                                       Model model) {
        activePage(model);
        pci.setProject(projectService.getById(projectId));
        projectService.postProjectCoordinator(pci);
        return "redirect:/project/step/PROJECT_INFORMATION/" + projectId;
    }
}