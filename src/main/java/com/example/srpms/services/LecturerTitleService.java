package com.example.srpms.services;

import com.example.srpms.models.Lecturertitle;
import com.example.srpms.repository.LecturerTitleRepository;
import org.springframework.stereotype.Service;

@Service
public class LecturerTitleService extends BusinessServiceImplementation<Lecturertitle, Integer>{
    protected LecturerTitleService(LecturerTitleRepository repository) {
        super(repository);
    }
}
