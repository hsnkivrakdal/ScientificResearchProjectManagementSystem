package com.example.srpms.controllers;

import com.example.srpms.applicationstep.ApplicationStep;
import com.example.srpms.models.*;
import com.example.srpms.services.ProjectService;
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
            case PROJECT_INFORMATION:
                Projectinformation pi = new Projectinformation();
                pi.setProject(project);
                model.addAttribute("projectInformation", pi);
                return "projects/project-information";
            case ACTIVITY_AREAS:
                Projectactivityarea paa = new Projectactivityarea();
                paa.setProject(project);
                model.addAttribute("projectActivityArea", paa);
                model.addAttribute("getActivityAreaTypes",projectService.listActivityAreas());
                model.addAttribute("listProjectActivityAreas",projectService.getProjectActivityAreas(projectId));
                return "projects/activity-area";
            case KEYWORDS:
                Projectkeyword pk = new Projectkeyword();
                pk.setProject(project);
                model.addAttribute("projectKeyword", pk);
                model.addAttribute("getKeywordTypes",projectService.listKeywords());
                model.addAttribute("listProjectKeywords",projectService.getProjectKeywords(projectId));
                return "projects/keyword";
            case MACHINERY:
                Projectmachinery pm = new Projectmachinery();
                pm.setProject(project);
                model.addAttribute("projectMachinery", pm);
                model.addAttribute("getMachineryTypes", projectService.listProjectMachinery());
                model.addAttribute("listProjectMachinery",projectService.getProjectMachinery(projectId));
                return "projects/machinery";
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

    @PostMapping("/step/PROJECT_INFORMATION/{projectId}")
    public String createCoordinatorInf(@PathVariable Integer projectId,
                                       @ModelAttribute Projectinformation pi,
                                       Model model) {
        activePage(model);
        pi.setProject(projectService.getById(projectId));
        projectService.postProjectInformation(pi);
        return "redirect:/project/step/ACTIVITY_AREAS/" + projectId;
    }

    @GetMapping("/project-activity-areas/delete/{id}/{projectId}")
    public String deleteActivityAreaType(@PathVariable Integer id,
                                         @PathVariable Integer projectId ,
                                         Model model) {
        activePage(model);
        projectService.deleteProjectActivityArea(id);
        return "redirect:/project/step/ACTIVITY_AREAS/" + projectId;
    }

    @PostMapping("/step/ACTIVITY_AREAS/{projectId}")
    public String createProjectActivityArea(@PathVariable Integer projectId,
                                            @ModelAttribute Projectactivityarea paa,
                                            Model model) {
        activePage(model);
        paa.setProject(projectService.getById(projectId));
        projectService.postProjectActivityArea(paa);
        return "redirect:/project/step/ACTIVITY_AREAS/" + projectId;
    }
    @PostMapping("/step/KEYWORDS/{projectId}")
    public String createProjectKeyword(@PathVariable Integer projectId,
                                            @ModelAttribute Projectkeyword pk,
                                            Model model) {
        activePage(model);
        pk.setProject(projectService.getById(projectId));
        projectService.postProjectKeyword(pk);
        return "redirect:/project/step/KEYWORDS/" + projectId;
    }

    @PostMapping("/step/MACHINERY/{projectId}")
    public String createProjectMachinery(@PathVariable Integer projectId,
                                       @ModelAttribute Projectmachinery pm,
                                       Model model) {
        activePage(model);
        pm.setProject(projectService.getById(projectId));
        projectService.postProjectMachinery(pm);
        return "redirect:/project/step/MACHINERY/" + projectId;
    }
}