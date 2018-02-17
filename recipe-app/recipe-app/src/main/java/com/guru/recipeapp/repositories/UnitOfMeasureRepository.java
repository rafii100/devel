package com.guru.recipeapp.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.guru.recipeapp.domain.UnitOfMessure;

@Repository
public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMessure, Long> {

	
	Optional<UnitOfMessure> findByUom(String uom);
}
