package com.example.srpms.services;

import com.example.srpms.models.Projectkeyword;
import com.example.srpms.repository.ProjectKeywordRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectKeywordService extends BusinessServiceImplementation<Projectkeyword, Integer> {
    protected ProjectKeywordService(ProjectKeywordRepository repository) {
        super(repository);
    }
}
