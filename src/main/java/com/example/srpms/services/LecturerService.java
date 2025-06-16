package com.example.srpms.services;

import com.example.srpms.models.Lecturer;
import com.example.srpms.repository.LecturerRepository;
import org.springframework.stereotype.Service;

@Service
public class LecturerService extends BusinessServiceImplementation<Lecturer, Integer>{
    protected LecturerService(LecturerRepository repository) {
        super(repository);
    }

    public Lecturer findByMailAddress(String mailAddress) {
        Lecturer lecturer = repository.findAll().stream().filter(x -> x.getMailAddress().equals(mailAddress)).findFirst().orElse(null);
        return lecturer;
    }
}
