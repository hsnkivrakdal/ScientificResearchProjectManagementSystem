package com.example.srpms.services;

import com.example.srpms.models.Projectpersonnel;
import com.example.srpms.repository.ProjectPersonnelRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectPersonnelService extends BusinessServiceImplementation<Projectpersonnel, Integer>{
    protected ProjectPersonnelService(ProjectPersonnelRepository repository) {
        super(repository);
    }
}
