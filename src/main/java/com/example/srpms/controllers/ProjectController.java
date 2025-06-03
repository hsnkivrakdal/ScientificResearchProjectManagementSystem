package com.example.srpms.controllers;

import com.example.srpms.applicationstep.ApplicationStep;
import com.example.srpms.models.*;
import com.example.srpms.services.ProjectService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Year;
import java.util.UUID;

@Controller
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    public Model activePage(Model model) {
        return model.addAttribute("activeStep","projectContinue");
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
            case TECHNOLOGY_LEVEL:
                Projecttechnologyreadinesslevel ptrl = new Projecttechnologyreadinesslevel();
                ptrl.setProject(project);
                model.addAttribute("projectTechnologyReadinessLevel", ptrl);
                model.addAttribute("getTechReadLevelType",projectService.listTechnologyReadinessLevelType());
                model.addAttribute("listProjectTechReadLevel", projectService.getProjectTechnologyReadinessLevels(projectId));
                return "projects/technology-level";
            case PERSONNEL:
                Projectpersonnel pp = new Projectpersonnel();
                pp.setProject(project);
                model.addAttribute("projectPersonnel", pp);
                model.addAttribute("getPersonnelTypes",projectService.listPersonnelTypes());
                model.addAttribute("listProjectPersonnel",projectService.getProjectPersonnel(projectId));
                return "projects/personnel";
            case FILES:
                Projectadditionalfile paf = new Projectadditionalfile();
                paf.setProject(project);
                model.addAttribute("projectAdditionalFile", paf);
                model.addAttribute("listProjectAdditionalFiles",projectService.getProjectAdditionalFiles(projectId));
                return "projects/additional-files";
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

        String uploadDir = "additionalFiles/" + projectId + "/";
        File folder = new File(uploadDir);
        if (!folder.exists()) folder.mkdirs();

        String originalName = file.getOriginalFilename();
        String uniqueName = UUID.randomUUID() + "_" + originalName;

        Path filePath = Paths.get(uploadDir + uniqueName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        paf.setProject(project);
        paf.setFileName(originalName);
        paf.setFilePath(uploadDir + uniqueName);
        paf.setFileType(file.getContentType());

        projectService.postProjectAdditionalFile(paf);

        return "redirect:/project/step/FILES/" + projectId;
    }

    @GetMapping("/additional-file/view/{id}")
    public void viewFile(@PathVariable Integer id, HttpServletResponse response) throws IOException {
        Projectadditionalfile fileEntity = projectService.getProjectAdditionalFileById(id);

        File file = new File(fileEntity.getFilePath());
        if (!file.exists()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        response.setContentType(fileEntity.getFileType());
        response.setHeader("Content-Disposition", "inline; filename=\"" + fileEntity.getFileName() + "\"");
        Files.copy(file.toPath(), response.getOutputStream());
        response.flushBuffer();
    }

    @PostMapping("/step/TECHNOLOGY_LEVEL/{projectId}")
    public String saveTechReadiness(
            @PathVariable Integer projectId,
            @ModelAttribute Projecttechnologyreadinesslevel ptrl,
            @RequestParam("techReadLevelFileDoc") MultipartFile file,
            Model model
    ) throws IOException {
        Project project = projectService.getById(projectId);

        if (!ptrl.getFieldOfProjectType() && !file.isEmpty()) {

            String uploadDir = "technologyReadinessLevelDocuments/" + projectId +"/";

            File folder = new File(uploadDir);
            if (!folder.exists()) folder.mkdirs();

            String originalFileName = file.getOriginalFilename();
            String uniqueName = UUID.randomUUID() + "_" + originalFileName;


            Path filePath = Paths.get(uploadDir + uniqueName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            ptrl.setProject(project);
            ptrl.setFileName(originalFileName);
            ptrl.setFileType(file.getContentType());
            ptrl.setTechReadLevelFile(filePath.toString());
        }

        projectService.postProjectTechnologyReadinessLevel(ptrl);
        return "redirect:/project/step/TECHNOLOGY_LEVEL/" + projectId;
    }

    @GetMapping("/techread-file/view/{id}")
    public ResponseEntity<Resource> viewTechReadFile(@PathVariable Integer id) throws IOException {
        Projecttechnologyreadinesslevel trl = projectService.getProjectTechnologyReadinessLevelById(id);
        if (trl == null) {
            return ResponseEntity.notFound().build();
        }

        Path path = Paths.get(trl.getTechReadLevelFile());
        Resource resource = new UrlResource(path.toUri());

        if (!resource.exists() || !resource.isReadable()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(trl.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + trl.getFileName() + "\"")
                .body(resource);
    }

}