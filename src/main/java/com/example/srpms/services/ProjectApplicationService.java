package com.example.srpms.services;

import com.example.srpms.models.Projectapplication;
import com.example.srpms.repository.ProjectApplicationRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectApplicationService extends BusinessServiceImplementation<Projectapplication, Integer>{
    protected ProjectApplicationService(ProjectApplicationRepository repository) {
        super(repository);
    }
}
