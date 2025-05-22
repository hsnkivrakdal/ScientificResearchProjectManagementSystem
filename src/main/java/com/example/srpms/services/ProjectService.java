package com.example.srpms.services;

import com.example.srpms.models.Coordinatorposition;
import com.example.srpms.models.Project;
import com.example.srpms.models.Projectapplication;
import com.example.srpms.models.Projectcoordinatorinformation;
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
    @Autowired
    public ProjectCoordinatorInformationService projectCoordinatorInformationService;
    @Autowired
    public CoordinatorPositionService coordinatorPositionService;

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

    public void postProjectCoordinator(Projectcoordinatorinformation projectcoordinatorinformation) {
         projectCoordinatorInformationService.add(projectcoordinatorinformation);
    }
    public List<Coordinatorposition> getCoordinatorPositions(){
        List<Coordinatorposition> coordinatorpositions = coordinatorPositionService.getAll();
        return coordinatorpositions;
    }
}
