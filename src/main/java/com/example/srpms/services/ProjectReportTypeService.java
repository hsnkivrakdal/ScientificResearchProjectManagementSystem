package com.example.srpms.services;

import com.example.srpms.models.Projectreporttype;
import com.example.srpms.repository.ProjectReportTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectReportTypeService extends BusinessServiceImplementation<Projectreporttype, Integer>{
    protected ProjectReportTypeService(ProjectReportTypeRepository repository) {
        super(repository);
    }
}
