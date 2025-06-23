package com.example.srpms.services;


import com.example.srpms.models.Lectureraffiliation;
import com.example.srpms.repository.LecturerAffiliationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LecturerAffiliationService extends BusinessServiceImplementation<Lectureraffiliation, Integer> {
    protected LecturerAffiliationService(LecturerAffiliationRepository repository) {
        super(repository);
    }
}
