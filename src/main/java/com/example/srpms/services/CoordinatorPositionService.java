package com.example.srpms.services;

import com.example.srpms.models.Coordinatorposition;
import com.example.srpms.repository.CoordinatorPositionRepository;
import org.springframework.stereotype.Service;

@Service
public class CoordinatorPositionService extends BusinessServiceImplementation<Coordinatorposition, Integer>{
    protected CoordinatorPositionService(CoordinatorPositionRepository repository) {
        super(repository);
    }
}
