package com.example.srpms.services;

import com.example.srpms.models.Project;
import com.example.srpms.repository.ProjectRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectService extends BusinessServiceImplementation<Project, Integer>{
    protected ProjectService(ProjectRepository repository) {
        super(repository);
    }
}
