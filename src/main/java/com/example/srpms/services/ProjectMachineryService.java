package com.example.srpms.services;

import com.example.srpms.models.Projectmachinery;
import com.example.srpms.repository.ProjectMachineryRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectMachineryService extends BusinessServiceImplementation<Projectmachinery, Integer>{
    protected ProjectMachineryService(ProjectMachineryRepository repository) {
        super(repository);
    }
}
