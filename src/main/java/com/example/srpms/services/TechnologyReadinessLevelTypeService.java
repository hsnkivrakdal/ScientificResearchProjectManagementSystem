package com.example.srpms.services;

import com.example.srpms.models.Technologyreadinessleveltype;
import com.example.srpms.repository.TechnologyReadinessLevelTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class TechnologyReadinessLevelTypeService extends BusinessServiceImplementation<Technologyreadinessleveltype, Integer>{
    protected TechnologyReadinessLevelTypeService(TechnologyReadinessLevelTypeRepository repository) {
        super(repository);
    }
}
