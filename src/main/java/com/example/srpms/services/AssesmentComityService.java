package com.example.srpms.services;

import com.example.srpms.models.Assesmentcomity;
import com.example.srpms.repository.AssesmentComityRepository;
import org.springframework.stereotype.Service;

@Service
public class AssesmentComityService extends BusinessServiceImplementation<Assesmentcomity, Integer> {
    protected AssesmentComityService(AssesmentComityRepository repository) {
        super(repository);
    }
}
