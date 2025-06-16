package com.example.srpms.services;

import com.example.srpms.models.*;
import com.example.srpms.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.Year;
import java.util.List;
import java.util.Optional;
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

    public Project createProjectWithApp(Projectapplication projectapplication,User user) {
        Project project = new Project();
        project.setProjectApplication(projectapplication);
        project.setIsApproved(false);
        project.setUser(user);
        project.setIsEvaluated(false);
        project.setIsSubmitted(false);
        repository.save(project);
        project.setProjectCode(generateProjectCode(project.getId()));
        return repository.save(project);
    }

    public void deleteProjectApplication(Integer projectId) {
        repository.deleteById(projectId);
    }

    public List<Project> getSubmittedProjects() {
        List<Project> project = repository.findAll().stream().filter(project1 -> Boolean.TRUE.equals(project1.getIsSubmitted())).collect(Collectors.toList());
        return project;
    }

    public List<Project> getApprovedProjects() {
        List<Project> project = repository.findAll().stream().filter(project1 -> Boolean.TRUE.equals(project1.getIsApproved())).collect(Collectors.toList());
        return project;
    }

    @Autowired
    public ProjectCoordinatorInformationService projectCoordinatorInformationService;

    @Autowired
    public CoordinatorPositionService coordinatorPositionService;

    public void postProjectCoordinator(Projectcoordinatorinformation projectcoordinatorinformation) {
        projectCoordinatorInformationService.add(projectcoordinatorinformation);
    }

    public List<Coordinatorposition> getCoordinatorPositions(){
        List<Coordinatorposition> coordinatorPositions = coordinatorPositionService.getAll();
        return coordinatorPositions;
    }

    public Projectcoordinatorinformation getProjectcoordinatorInformationByProjectId(Integer projectId) {
        Projectcoordinatorinformation projectcoordinatorinformation = projectCoordinatorInformationService.getAll().stream().filter(projectcoordinatorinformation1 -> projectcoordinatorinformation1.getProject().getId().equals(projectId)).findFirst().orElse(new Projectcoordinatorinformation());
        return projectcoordinatorinformation;
    }

    @Autowired
    public ProjectInformationService projectInformationService;

    public void postProjectInformation(Projectinformation projectinformation) {
        projectInformationService.add(projectinformation);
    }

    public Projectinformation getProjectInformationByProjectId(Integer projectId) {
        Projectinformation projectinformation = projectInformationService.getAll().stream().filter(projectinformation1 -> projectinformation1.getProject().getId().equals(projectId)).findFirst().orElse(new Projectinformation());
        return projectinformation;
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

    public void deleteProjectKeyword(Integer id){
        projectKeywordService.deleteById(id);
    }

    @Autowired
    public ProjectTechnologyReadinessLevelService projectTechnologyReadinessLevelService;

    @Autowired
    public TechnologyReadinessLevelTypeService technologyReadinessLevelTypeService;

    public void postProjectTechnologyReadinessLevel(Projecttechnologyreadinesslevel projecttechnologyreadinesslevel) {
        projectTechnologyReadinessLevelService.add(projecttechnologyreadinesslevel);
    }

    public List<Technologyreadinessleveltype> listTechnologyReadinessLevelType(){
        List<Technologyreadinessleveltype> technologyreadinessleveltypes = technologyReadinessLevelTypeService.getAll();
        return technologyreadinessleveltypes;
    }

    public List<Projecttechnologyreadinesslevel> getProjectTechnologyReadinessLevels(Integer projectId){
        List<Projecttechnologyreadinesslevel> projectTechnologyreadinessLevels = projectTechnologyReadinessLevelService.getAll().stream().filter(projecttechnologyreadinesslevel -> projecttechnologyreadinesslevel.getProject().getId().equals(projectId)).toList();
        return projectTechnologyreadinessLevels;
    }

    public Projecttechnologyreadinesslevel getProjectTechnologyReadinessLevelById(Integer id){
        Projecttechnologyreadinesslevel projecttechnologyreadinesslevel = projectTechnologyReadinessLevelService.getById(id);
        return projecttechnologyreadinesslevel;
    }
    public Projecttechnologyreadinesslevel getProjectTechnologyReadinessLevelByProjectId(Integer projectId){
        Projecttechnologyreadinesslevel projecttechnologyreadinesslevel = projectTechnologyReadinessLevelService.getAll().stream().filter(projecttechnologyreadinesslevel1 -> projecttechnologyreadinesslevel1.getProject().getId().equals(projectId)).findFirst().orElse(new Projecttechnologyreadinesslevel());
        return projecttechnologyreadinesslevel;
    }

    public void deleteProjectTechnologyReadinessLevelFile(Integer id){
        projectTechnologyReadinessLevelService.deleteById(id);
    }


    @Autowired
    public ProjectPersonnelService projectPersonnelService;

    @Autowired
    public PersonnelTypeService personnelTypeService;

    public List<Projectpersonnel> getProjectPersonnel(Integer projectId){
        List<Projectpersonnel> personnel = projectPersonnelService.getAll().stream().filter(projectpersonnel -> projectpersonnel.getProject().getId().equals(projectId)).toList();
        return personnel;
    }

    public List<Personneltype> listPersonnelTypes(){
        List<Personneltype> personnelTypes = personnelTypeService.getAll();
        return personnelTypes;
    }

    public void postProjectPersonnel(Projectpersonnel projectpersonnel) {
        projectpersonnel.setIdentityNumber(projectpersonnel.getIdentityNumber());
        projectpersonnel.setFirstName(projectpersonnel.getFirstName());
        projectpersonnel.setLastName(projectpersonnel.getLastName());
        projectpersonnel.setTitle(projectpersonnel.getTitle());
        projectpersonnel.setAffiliation(projectpersonnel.getAffiliation());
        projectPersonnelService.add(projectpersonnel);
    }

    public void deleteProjectPersonnel(Integer id){
        projectPersonnelService.deleteById(id);
    }

    @Autowired
    public ProjectAdditionalFilesService projectAdditionalFilesService;

    public List<Projectadditionalfile> getProjectAdditionalFiles(Integer projectId){
        List<Projectadditionalfile> projectAdditionalFiles = projectAdditionalFilesService.getAll().stream().filter(projectadditionalfile -> projectadditionalfile.getProject().getId().equals(projectId)).toList();
        return projectAdditionalFiles;
    }

    public void postProjectAdditionalFile(Projectadditionalfile file) {
        projectAdditionalFilesService.add(file);
    }

    public Projectadditionalfile getProjectAdditionalFileById(Integer id){
        Projectadditionalfile projectadditionalfile = projectAdditionalFilesService.getById(id);
        return projectadditionalfile;
    }

    public void deleteProjectAdditionalFile(Integer id){
        projectAdditionalFilesService.deleteById(id);
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

    public void deleteProjectMachinery(Integer id){
        projectMachineryService.deleteById(id);
    }


    public void submitProject(Project project) {
        Projectinformation pi = getProjectInformationByProjectId(project.getId());
        project.setProjectApplicationDate(pi.getProjectApplicationDate());
        project.setProjectTitle(pi.getProjectTitle());
        repository.save(project);
    }

}
