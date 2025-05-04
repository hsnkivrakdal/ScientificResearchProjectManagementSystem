package com.example.srpms.services;

import com.example.srpms.models.Projectadditionalfile;
import com.example.srpms.repository.ProjectAdditionalFilesRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectAdditionalFilesService extends BusinessServiceImplementation<Projectadditionalfile, Integer>{
    protected ProjectAdditionalFilesService(ProjectAdditionalFilesRepository repository) {
        super(repository);
    }
}
