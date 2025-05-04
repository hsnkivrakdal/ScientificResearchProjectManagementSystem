package com.example.srpms.services;

import com.example.srpms.models.Activityareatype;
import com.example.srpms.repository.ActivityAreaTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class ActivityAreaTypeService extends BusinessServiceImplementation<Activityareatype, Integer>{
    public ActivityAreaTypeService(ActivityAreaTypeRepository repository) {
        super(repository);
    }
}
