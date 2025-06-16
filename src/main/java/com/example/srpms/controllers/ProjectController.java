package com.example.srpms.controllers;

import com.example.srpms.applicationstep.ApplicationStep;
import com.example.srpms.models.*;
import com.example.srpms.services.ProjectService;
import com.example.srpms.singletonfilemanagement.FileManager;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;


@Controller
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    public Model activePage(Model model) {
        return model.addAttribute("activePage","new-project");
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
                activePage(model);
                model.addAttribute("activeStep","project");
                return "projects/application-description";
            case COORDINATOR:
                activePage(model);
                model.addAttribute("activeStep","coordinator");
                Projectcoordinatorinformation pci = projectService.getProjectcoordinatorInformationByProjectId(projectId);
                pci.setProject(project);
                model.addAttribute("projectCoordinatorInf", pci);
                model.addAttribute("positions", projectService.getCoordinatorPositions());
                return "projects/coordinator-information";
            case PROJECT_INFORMATION:
                activePage(model);
                model.addAttribute("activeStep","project-information");
                Projectinformation pi = projectService.getProjectInformationByProjectId(projectId);
                pi.setProject(project);
                model.addAttribute("projectInformation", pi);
                return "projects/project-information";
            case ACTIVITY_AREAS:
                activePage(model);
                model.addAttribute("activeStep","activity-areas");
                Projectactivityarea paa = new Projectactivityarea();
                paa.setProject(project);
                model.addAttribute("projectActivityArea", paa);
                model.addAttribute("getActivityAreaTypes",projectService.listActivityAreas());
                model.addAttribute("listProjectActivityAreas",projectService.getProjectActivityAreas(projectId));
                return "projects/activity-area";
            case KEYWORDS:
                activePage(model);
                model.addAttribute("activeStep","keywords");
                Projectkeyword pk = new Projectkeyword();
                pk.setProject(project);
                model.addAttribute("projectKeyword", pk);
                model.addAttribute("getKeywordTypes",projectService.listKeywords());
                model.addAttribute("listProjectKeywords",projectService.getProjectKeywords(projectId));
                return "projects/keyword";
            case TECHNOLOGY_LEVEL:
                activePage(model);
                model.addAttribute("activeStep","tech-level");
                Projecttechnologyreadinesslevel ptrl = projectService.getProjectTechnologyReadinessLevelByProjectId(projectId);
                ptrl.setProject(project);
                model.addAttribute("projectTechnologyReadinessLevel", ptrl);
                model.addAttribute("getTechReadLevelType",projectService.listTechnologyReadinessLevelType());
                model.addAttribute("listProjectTechReadLevel", projectService.getProjectTechnologyReadinessLevels(projectId));
                return "projects/technology-level";
            case PERSONNEL:
                activePage(model);
                model.addAttribute("activeStep","personnel");
                Projectpersonnel pp = new Projectpersonnel();
                pp.setProject(project);
                model.addAttribute("projectPersonnel", pp);
                model.addAttribute("getPersonnelTypes",projectService.listPersonnelTypes());
                model.addAttribute("listProjectPersonnel",projectService.getProjectPersonnel(projectId));
                return "projects/personnel";
            case FILES:
                activePage(model);
                model.addAttribute("activeStep","files");
                Projectadditionalfile paf = new Projectadditionalfile();
                paf.setProject(project);
                model.addAttribute("projectAdditionalFile", paf);
                model.addAttribute("listProjectAdditionalFiles",projectService.getProjectAdditionalFiles(projectId));
                return "projects/additional-files";
            case MACHINERY:
                activePage(model);
                model.addAttribute("activeStep","machinery");
                Projectmachinery pm = new Projectmachinery();
                pm.setProject(project);
                model.addAttribute("projectMachinery", pm);
                model.addAttribute("getMachineryTypes", projectService.listProjectMachinery());
                model.addAttribute("listProjectMachinery",projectService.getProjectMachinery(projectId));
                return "projects/machinery";
            case SUBMIT:
                activePage(model);
                model.addAttribute("activeStep","submit");
                return "projects/submit";
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

    @GetMapping("/description/{applicationId}")//application description page for new application
    public String applicationDescriptionWithNewProject(@PathVariable Integer applicationId, Model model, HttpSession session) {
        activePage(model);
        User user = (User) session.getAttribute("user");

        var newProjectWithApplication =  projectService.getProjectApplicationById(applicationId);
        Project project = projectService.createProjectWithApp(newProjectWithApplication,user);
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
        return "redirect:/project/step/COORDINATOR/" + projectId;
    }

    @PostMapping("/step/PROJECT_INFORMATION/{projectId}")
    public String createCoordinatorInf(@PathVariable Integer projectId,
                                       @ModelAttribute Projectinformation pi,
                                       Model model) {
        activePage(model);
        pi.setProject(projectService.getById(projectId));
        projectService.postProjectInformation(pi);
        return "redirect:/project/step/PROJECT_INFORMATION/" + projectId;
    }

    @GetMapping("/project-activity-areas/delete/{id}/{projectId}")
    public String deleteProjectActivityArea(@PathVariable Integer id,
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

    @GetMapping("/project-keyword/delete/{id}/{projectId}")
    public String deleteProjectKeyword(@PathVariable Integer id,
                                       @PathVariable Integer projectId ,
                                       Model model) {
        activePage(model);
        projectService.deleteProjectKeyword(id);
        return "redirect:/project/step/KEYWORDS/" + projectId;
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

    @GetMapping("/project-machinery/delete/{id}/{projectId}")
    public String deleteProjectMachinery(@PathVariable Integer id,
                                         @PathVariable Integer projectId ,
                                         Model model) {
        activePage(model);
        projectService.deleteProjectMachinery(id);
        return "redirect:/project/step/MACHINERY/" + projectId;
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

    @GetMapping("/project-personnel/delete/{id}/{projectId}")
    public String deleteProjectPersonnel(@PathVariable Integer id,
                                         @PathVariable Integer projectId ,
                                         Model model) {
        activePage(model);
        projectService.deleteProjectPersonnel(id);
        return "redirect:/project/step/PERSONNEL/" + projectId;
    }

    @PostMapping("/step/PERSONNEL/{projectId}")
    public String createProjectPersonnel(@PathVariable Integer projectId,
                                         @ModelAttribute Projectpersonnel pp,
                                         Model model) {
        activePage(model);
        pp.setProject(projectService.getById(projectId));
        projectService.postProjectPersonnel(pp);
        return "redirect:/project/step/PERSONNEL/" + projectId;
    }

    @GetMapping("/project-additional-file/delete/{id}/{projectId}")
    public String deleteProjectAdditionalFile(@PathVariable Integer id,
                                              @PathVariable Integer projectId ,
                                              Model model) {
        activePage(model);
        projectService.deleteProjectAdditionalFile(id);
        return "redirect:/project/step/FILES/" + projectId;
    }

    @PostMapping("/step/FILES/{projectId}")
    public String uploadFile(@PathVariable Integer projectId,
                             @ModelAttribute Projectadditionalfile paf,
                             @RequestParam("fileDocument") MultipartFile file,
                             Model model) throws IOException {

        activePage(model);
        Project project = projectService.getById(projectId);

        String path = FileManager.getInstance().saveFile(file,"additionalFiles/" + projectId + "/");

        paf.setProject(project);
        paf.setFileName(file.getOriginalFilename());
        paf.setFilePath(path);
        paf.setFileType(file.getContentType());

        projectService.postProjectAdditionalFile(paf);

        return "redirect:/project/step/FILES/" + projectId;
    }

    @GetMapping("/additional-file/view/{id}")
    public void viewFile(@PathVariable Integer id, HttpServletResponse response, Model model) throws IOException {

        activePage(model);
        Projectadditionalfile fileEntity = projectService.getProjectAdditionalFileById(id);

        FileManager.getInstance().streamFileToResponse(
                fileEntity.getFilePath(),
                fileEntity.getFileName(),
                fileEntity.getFileType(),
                response
        );
    }

    @GetMapping("/project-tech-file/delete/{id}/{projectId}")
    public String deleteTechLevelFile(@PathVariable Integer id,
                                              @PathVariable Integer projectId ,
                                              Model model) {
        activePage(model);
        projectService.deleteProjectTechnologyReadinessLevelFile(id);
        return "redirect:/project/step/TECHNOLOGY_LEVEL/" + projectId;
    }

    @PostMapping("/step/TECHNOLOGY_LEVEL/{projectId}")
    public String saveTechReadiness(
            @PathVariable Integer projectId,
            @ModelAttribute Projecttechnologyreadinesslevel ptrl,
            @RequestParam("techReadLevelFileDoc") MultipartFile file,
            Model model
    ) throws IOException {
        activePage(model);
        Project project = projectService.getById(projectId);

        if (!ptrl.getFieldOfProjectType() && !file.isEmpty()) {

            String path = FileManager.getInstance().saveFile(file,"technologyReadinessLevelDocuments/" + projectId + "/");

            ptrl.setProject(project);
            ptrl.setFileName(file.getOriginalFilename());
            ptrl.setFileType(file.getContentType());
            ptrl.setTechReadLevelFile(path);
        }

        projectService.postProjectTechnologyReadinessLevel(ptrl);
        return "redirect:/project/step/TECHNOLOGY_LEVEL/" + projectId;
    }

    @GetMapping("/techread-file/view/{id}")
    public ResponseEntity<Resource> viewTechReadFile(@PathVariable Integer id, Model model) throws IOException {
        activePage(model);
        Projecttechnologyreadinesslevel trl = projectService.getProjectTechnologyReadinessLevelById(id);
        if (trl == null) {
            return ResponseEntity.notFound().build();
        }

        Resource resource = FileManager.getInstance().getFileAsResource(trl.getTechReadLevelFile());

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(trl.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + trl.getFileName() + "\"")
                .body(resource);
    }

    @PostMapping("/step/SUBMIT/{projectId}")
    public String projectSubmit(@PathVariable Integer projectId,
                                Model model) {
        activePage(model);

        Project project = projectService.getById(projectId);

        if (project.getProjectApplication() == null) {
            Projectapplication app = projectService.getProjectApplicationById(projectId);
            project.setProjectApplication(app);
        }

        project.setIsSubmitted(true);
        project.setIsApproved(false);
        project.setIsEvaluated(false);

        projectService.submitProject(project);

        return "redirect:/project/step/PERSONNEL/" + projectId;
    }
}