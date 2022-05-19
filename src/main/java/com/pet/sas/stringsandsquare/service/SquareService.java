package com.pet.sas.stringsandsquare.service;

import com.pet.sas.stringsandsquare.model.SquareModel;
import com.pet.sas.stringsandsquare.repository.SquareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SquareService {
    private final SquareRepository squareRepository;

    @Autowired
    public SquareService(SquareRepository squareRepository) {
        this.squareRepository = squareRepository;
    }

    public void saveSquare(SquareModel squareModel) {
        this.squareRepository.save(squareModel);
    }

    public List<SquareModel> getSquaresList() {
        return squareRepository.findAll().stream()
                .sorted(Comparator.comparing(SquareModel::getId).reversed())
                .collect(Collectors.toList());
    }

    public SquareModel getSquareById(Long id) {
        Optional<SquareModel> optional = squareRepository.findById(id);
        SquareModel squareModel = null;
        if (optional.isPresent()) {
            squareModel = optional.get();
        } else {
            throw new RuntimeException("There is no task with id " + id);
        }
        return squareModel;
    }
}