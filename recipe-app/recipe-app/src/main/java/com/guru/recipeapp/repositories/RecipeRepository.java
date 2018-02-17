package com.guru.recipeapp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.guru.recipeapp.domain.Recipe;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long>{

}
