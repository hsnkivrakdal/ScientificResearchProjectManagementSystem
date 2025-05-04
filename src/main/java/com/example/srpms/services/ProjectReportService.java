package com.example.srpms.services;

import com.example.srpms.models.Projectreport;
import com.example.srpms.repository.ProjectReportRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectReportService extends BusinessServiceImplementation<Projectreport, Integer>{
    protected ProjectReportService(ProjectReportRepository repository) {
        super(repository);
    }
}
