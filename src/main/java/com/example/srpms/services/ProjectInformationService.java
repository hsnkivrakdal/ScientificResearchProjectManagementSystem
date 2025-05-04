package com.example.srpms.services;

import com.example.srpms.models.Projectinformation;
import com.example.srpms.repository.ProjectInformationRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectInformationService extends BusinessServiceImplementation<Projectinformation, Integer>{
    protected ProjectInformationService(ProjectInformationRepository repository) {
        super(repository);
    }
}
