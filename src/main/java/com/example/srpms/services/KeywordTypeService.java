package com.example.srpms.services;

import com.example.srpms.models.Keywordtype;
import com.example.srpms.repository.KeywordTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class KeywordTypeService extends BusinessServiceImplementation<Keywordtype, Integer>{
    protected KeywordTypeService(KeywordTypeRepository repository) {
        super(repository);
    }
}
