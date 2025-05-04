package com.example.srpms.services;

import com.example.srpms.models.Activityareatype;
import com.example.srpms.models.Projectactivityarea;
import com.example.srpms.repository.ProjectActivityAreaRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectActivityAreaService extends BusinessServiceImplementation<Projectactivityarea, Integer> {
    protected ProjectActivityAreaService(ProjectActivityAreaRepository repository) {
        super(repository);
    }
}
