package com.example.srpms.services;

import com.example.srpms.models.Applicationdescription;
import com.example.srpms.repository.ApplicationDescriptionRepository;
import org.springframework.stereotype.Service;

@Service
public class ApplicationDescriptiponService extends BusinessServiceImplementation<Applicationdescription, Integer> {
    protected ApplicationDescriptiponService(ApplicationDescriptionRepository repository) {
        super(repository);
    }
}
