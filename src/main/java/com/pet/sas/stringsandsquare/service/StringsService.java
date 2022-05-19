package com.pet.sas.stringsandsquare.service;

import com.pet.sas.stringsandsquare.model.StringsModel;
import com.pet.sas.stringsandsquare.repository.StringsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StringsService {
    private final StringsRepository stringsRepository;

    @Autowired
    public StringsService(StringsRepository stringsRepository) {
        this.stringsRepository = stringsRepository;
    }

    public void saveStrings(StringsModel stringsModel) {
        this.stringsRepository.save(stringsModel);
    }

    public List<StringsModel> getStringsList() {
        return stringsRepository.findAll().stream()
                .sorted(Comparator.comparing(StringsModel::getId).reversed())
                .collect(Collectors.toList());
    }

    public StringsModel getStringsById(Long id) {
        Optional<StringsModel> optional = stringsRepository.findById(id);
        StringsModel stringsModel = null;
        if (optional.isPresent()) {
            stringsModel = optional.get();
        } else {
            throw new RuntimeException("There is no task with id " + id);
        }
        return stringsModel;
    }
}
