package com.pet.sas.stringsandsquare.service;

import com.pet.sas.stringsandsquare.model.SquareModel;
import com.pet.sas.stringsandsquare.repository.SquareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
