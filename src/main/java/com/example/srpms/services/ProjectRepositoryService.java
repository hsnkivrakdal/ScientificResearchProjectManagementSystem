package com.example.srpms.services;

import com.example.srpms.models.Projectkeyword;
import com.example.srpms.repository.ProjectKeywordRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectRepositoryService extends BusinessServiceImplementation<Projectkeyword, Integer>{
    protected ProjectRepositoryService(ProjectKeywordRepository repository) {
        super(repository);
    }
}
