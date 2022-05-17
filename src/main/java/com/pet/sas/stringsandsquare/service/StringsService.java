package com.pet.sas.stringsandsquare.service;

import com.pet.sas.stringsandsquare.repository.StringsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StringsService {
    private final StringsRepository stringsRepository;

    @Autowired
    public StringsService(StringsRepository stringsRepository) {
        this.stringsRepository = stringsRepository;
    }

}
