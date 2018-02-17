package com.guru.recipeapp.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.guru.recipeapp.domain.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

	//to sie nazywa: Query Method
	//part of Spring Data JPA
	Optional<Category> findByDescription(String description);
}
