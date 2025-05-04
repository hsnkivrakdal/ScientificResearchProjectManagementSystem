package com.example.srpms.services;

import com.example.srpms.models.Machinerytype;
import com.example.srpms.repository.MachineryTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class MachineryTypeService extends BusinessServiceImplementation<Machinerytype, Integer>{
    protected MachineryTypeService(MachineryTypeRepository repository) {
        super(repository);
    }
}
