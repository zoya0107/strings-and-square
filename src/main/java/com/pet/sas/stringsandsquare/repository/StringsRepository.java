package com.pet.sas.stringsandsquare.repository;

import com.pet.sas.stringsandsquare.model.StringsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StringsRepository extends JpaRepository<StringsModel, Long> {
}
