package com.example.srpms.services;

import com.example.srpms.models.Lecturertype;
import com.example.srpms.repository.LecturerTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class LecturerTypeService extends BusinessServiceImplementation<Lecturertype, Integer>{
    protected LecturerTypeService(LecturerTypeRepository repository) {
        super(repository);
    }
}
