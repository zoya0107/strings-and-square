package com.pet.sas.stringsandsquare.repository;

import com.pet.sas.stringsandsquare.model.SquareModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SquareRepository extends JpaRepository<SquareModel, Long> {
}
