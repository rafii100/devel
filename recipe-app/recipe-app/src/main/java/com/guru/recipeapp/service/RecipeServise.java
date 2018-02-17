package com.guru.recipeapp.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guru.recipeapp.commands.RecipeCommand;
import com.guru.recipeapp.converters.RecipeCommandToRecipe;
import com.guru.recipeapp.converters.RecipeToRecipeCommand;
import com.guru.recipeapp.domain.Recipe;
import com.guru.recipeapp.repositories.RecipeRepository;

import lombok.extern.slf4j.Slf4j;

//simple logging facade for java (dla util.logging, logback, log4j)
@Slf4j
@Service
public class RecipeServise {

	@Autowired
	private RecipeRepository recipeRepository;

	@Autowired
	private RecipeCommandToRecipe recipeCommandToRecipe;
	@Autowired
	private RecipeToRecipeCommand recipeToRecipeCommand;

	public Iterable<Recipe> getRecipes() {
		log.info("GET RECIPES");
		return recipeRepository.findAll();
	}

	public Recipe getRecipeById(Long id) {
		Optional<Recipe> recipe = recipeRepository.findById(id);
		return recipe.get();
	}

	public void removeById(Long id) {
		recipeRepository.deleteById(id);

	}

	@Transactional
	public RecipeCommand saveRecipeCommand(RecipeCommand command) {
		Recipe detachedRecipe = recipeCommandToRecipe.convert(command);

		Recipe savedRecipe = recipeRepository.save(detachedRecipe);
		log.info("Saved RecipeId:" + savedRecipe.getId());
		return recipeToRecipeCommand.convert(savedRecipe);
	}

}
