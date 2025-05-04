package com.example.srpms.services;

import com.example.srpms.models.Projecttype;
import com.example.srpms.repository.ProjectTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectTypeService extends BusinessServiceImplementation<Projecttype, Integer>{
    protected ProjectTypeService(ProjectTypeRepository repository) {
        super(repository);
    }
}
