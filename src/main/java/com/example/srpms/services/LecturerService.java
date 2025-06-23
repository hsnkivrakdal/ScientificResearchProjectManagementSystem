package com.example.srpms.services;

import com.example.srpms.models.Lecturer;
import com.example.srpms.models.Lectureraffiliation;
import com.example.srpms.models.Lecturertitle;
import com.example.srpms.models.Lecturertype;
import com.example.srpms.repository.LecturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LecturerService extends BusinessServiceImplementation<Lecturer, Integer>{
    protected LecturerService(LecturerRepository repository) {
        super(repository);
    }

    public Lecturer findByMailAddress(String mailAddress) {
        Lecturer lecturer = repository.findAll().stream().filter(x -> x.getMailAddress().equals(mailAddress)).findFirst().orElse(null);
        return lecturer;
    }

    @Autowired
    private LecturerAffiliationService lecturerAffiliationService;

    public List<Lectureraffiliation> getAllLectureraffiliations() {
        List<Lectureraffiliation> lectureraffiliations = lecturerAffiliationService.getAll();
        return lectureraffiliations;
    }

    @Autowired
    private LecturerTypeService lecturerTypeService;

    public List<Lecturertype> getAllLecturertypes() {
        List<Lecturertype> lecturertypes = lecturerTypeService.getAll();
        return lecturertypes;
    }

    @Autowired
    private LecturerTitleService lecturerTitleService;

    public List<Lecturertitle> getAllLecturertitles() {
        List<Lecturertitle> lecturertitles = lecturerTitleService.getAll();
        return lecturertitles;
    }
}
