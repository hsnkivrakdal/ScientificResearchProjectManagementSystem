package com.example.srpms.services;

import com.example.srpms.models.Personneltype;
import com.example.srpms.repository.PersonnelTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonnelTypeService extends BusinessServiceImplementation<Personneltype, Integer>{
    protected PersonnelTypeService(PersonnelTypeRepository repository) {
        super(repository);
    }
}
