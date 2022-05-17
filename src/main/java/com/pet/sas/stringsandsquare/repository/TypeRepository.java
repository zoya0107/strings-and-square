package com.pet.sas.stringsandsquare.repository;

import com.pet.sas.stringsandsquare.model.TypeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<TypeModel, Long> {
}
