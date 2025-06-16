package com.example.srpms.services;

import com.example.srpms.models.Projectcoordinatorinformation;
import com.example.srpms.repository.ProjectCoordinatorInformationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectCoordinatorInformationService extends BusinessServiceImplementation<Projectcoordinatorinformation, Integer>{
    protected ProjectCoordinatorInformationService(ProjectCoordinatorInformationRepository repository) {
        super(repository);
    }
}
