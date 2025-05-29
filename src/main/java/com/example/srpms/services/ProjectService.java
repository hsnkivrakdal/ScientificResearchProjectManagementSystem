package com.example.srpms.services;

import com.example.srpms.models.*;
import com.example.srpms.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.Year;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService extends BusinessServiceImplementation<Project, Integer>{
    protected ProjectService(ProjectRepository repository) {
        super(repository);
    }
    @Autowired
    public ProjectApplicationService projectApplicationService;

    private static final String PREFIX = "BAP";

    public String generateProjectCode(Integer id) {
        int currentYear = Year.now().getValue();
        return String.format("%d-%s-%03d", currentYear, PREFIX, id);
    }

    public List<Projectapplication> getProjectApplications(Projectapplication projectapplication) {
        List<Projectapplication> projectApplications = projectApplicationService.getAll().stream().filter(Projectapplication::getIsActive).collect(Collectors.toList());
        return projectApplications;
    }

    public Projectapplication getProjectApplicationById(Integer id) {
        return projectApplicationService.getById(id);
    }

    public Project createProjectWithApp(Projectapplication projectapplication) {
        Project project = new Project();
        project.setProjectApplication(projectapplication);
        project.setIsApproved(false);
        repository.save(project);
        project.setProjectCode(generateProjectCode(project.getId()));
        return repository.save(project);
    }

    @Autowired
    public ProjectCoordinatorInformationService projectCoordinatorInformationService;

    public void postProjectCoordinator(Projectcoordinatorinformation projectcoordinatorinformation) {
        Projectcoordinatorinformation pci = new Projectcoordinatorinformation();
        pci.setFirstName(projectcoordinatorinformation.getFirstName());
        pci.setLastName(projectcoordinatorinformation.getLastName());
        pci.setMailAddress(projectcoordinatorinformation.getMailAddress());
        pci.setPhoneNumber(projectcoordinatorinformation.getPhoneNumber());
        pci.setDoctorateDegree(projectcoordinatorinformation.getDoctorateDegree());
        pci.setCoordinatorResponsibility(projectcoordinatorinformation.getCoordinatorResponsibility());
        pci.setCoordinatorPosition(projectcoordinatorinformation.getCoordinatorPosition());
        pci.setProject(projectcoordinatorinformation.getProject());
        projectCoordinatorInformationService.add(pci);
    }

    @Autowired
    public CoordinatorPositionService coordinatorPositionService;

    public List<Coordinatorposition> getCoordinatorPositions(){
        List<Coordinatorposition> coordinatorPositions = coordinatorPositionService.getAll();
        return coordinatorPositions;
    }

    @Autowired
    public ProjectInformationService projectInformationService;

    public void postProjectInformation(Projectinformation projectinformation) {
        projectInformationService.add(projectinformation);
    }

    @Autowired
    public ProjectActivityAreaService projectActivityAreaService;

    @Autowired
    public ActivityAreaTypeService activityAreaTypeService;

    public void postProjectActivityArea(Projectactivityarea projectactivityarea) {
        projectActivityAreaService.add(projectactivityarea);
    }

    public List<Activityareatype> listActivityAreas(){
        List<Activityareatype> activityAreas = activityAreaTypeService.getAll();
        return activityAreas;
    }

    public List<Projectactivityarea> getProjectActivityAreas(Integer projectId){
        List<Projectactivityarea> projectActivityAreas = projectActivityAreaService.getAll().stream().filter(projectactivityarea -> projectactivityarea.getProject().getId().equals(projectId)).toList();
        return projectActivityAreas;
    }

    public void deleteProjectActivityArea(Integer id){
        projectActivityAreaService.deleteById(id);
    }

    @Autowired
    public ProjectKeywordService projectKeywordService;

    @Autowired
    public KeywordTypeService keywordTypeService;

    public void postProjectKeyword(Projectkeyword projectkeyword) {
        projectKeywordService.add(projectkeyword);
    }

    public List<Keywordtype> listKeywords(){
        List<Keywordtype> keywords = keywordTypeService.getAll();
        return keywords;
    }

    public List<Projectkeyword> getProjectKeywords(Integer projectId){
        List<Projectkeyword> projectKeywords = projectKeywordService.getAll().stream().filter(projectkeyword ->projectkeyword.getProject().getId().equals(projectId) ).toList();
        return projectKeywords;
    }

    @Autowired
    public ProjectMachineryService projectMachineryService;

    @Autowired
    public MachineryTypeService machineryTypeService;

    public void postProjectMachinery(Projectmachinery projectmachinery) {
        projectmachinery.setMachineryTitle(projectmachinery.getMachineryTitle());
        projectMachineryService.add(projectmachinery);
    }

    public List<Machinerytype> listProjectMachinery(){
        List<Machinerytype> machinery = machineryTypeService.getAll();
        return machinery;
    }

    public List<Projectmachinery> getProjectMachinery(Integer projectId){
        List<Projectmachinery> projectMachinery = projectMachineryService.getAll().stream().filter(projectmachinery -> projectmachinery.getProject().getId().equals(projectId)).toList();
        return projectMachinery;
    }

}
