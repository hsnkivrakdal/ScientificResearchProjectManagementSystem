package com.example.srpms.services;

import com.example.srpms.models.Projecttechnologyreadinesslevel;
import com.example.srpms.repository.ProjectTechnologyReadinessLevelRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectTechnologyReadinessLevelService extends BusinessServiceImplementation<Projecttechnologyreadinesslevel, Integer>{
    protected ProjectTechnologyReadinessLevelService(ProjectTechnologyReadinessLevelRepository repository) {
        super(repository);
    }
}
