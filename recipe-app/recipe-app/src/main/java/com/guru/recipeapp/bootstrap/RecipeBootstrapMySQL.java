package com.guru.recipeapp.bootstrap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.guru.recipeapp.domain.Category;
import com.guru.recipeapp.domain.Difficulty;
import com.guru.recipeapp.domain.Ingredient;
import com.guru.recipeapp.domain.Notes;
import com.guru.recipeapp.domain.Recipe;
import com.guru.recipeapp.repositories.CategoryRepository;
import com.guru.recipeapp.repositories.RecipeRepository;
import com.guru.recipeapp.repositories.UnitOfMeasureRepository;

@Component
@Profile({ "prod", "dev" })
public class RecipeBootstrapMySQL {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private UnitOfMeasureRepository uomRepository;

	@Autowired
	private RecipeRepository recipeRepository;

	@PostConstruct
	public void init() {
		if (recipeRepository.count() == 0) {
			System.out.println("BOOTSTRAP RECIPE MYSQL");
			List<Recipe> recipes = getRecipes();
			recipeRepository.saveAll(recipes);
		} else {
			System.out.println("NOT UPDATING DATABASE MYSQL");
		}

	}

	@Transactional
	public List<Recipe> getRecipes() {

		List<Recipe> recipes = new ArrayList<Recipe>();
		Recipe r = new Recipe();

		Category c1 = categoryRepository.findById(1L).get();
		Category c2 = categoryRepository.findById(2L).get();
		Category c3 = categoryRepository.findById(3L).get();

		r.getCategories().add(c1);
		r.getCategories().add(c2);

		Ingredient i1 = new Ingredient(uomRepository.findById(1L).get(), "Description ingredient 1",
				BigDecimal.valueOf(1), r);
		Ingredient i2 = new Ingredient(uomRepository.findById(2L).get(), "Description ingredient 2",
				BigDecimal.valueOf(2), r);

		r.setCookTime(2);
		r.setDescription("Description for recipe 1");
		r.setDifficulty(Difficulty.EASY);
		r.setDirections("Directions for recipe 1");
		r.getIngredient().add(i1);
		r.getIngredient().add(i2);
		r.setNotes(new Notes("Notes for recipe1"));
		r.setPrepTime(1);
		r.setServings(1);
		r.setSource("Source for recipe 1");
		r.setUrl("http://test1.pl");

		recipes.add(r);

		Recipe r2 = new Recipe();

		Ingredient i3 = new Ingredient(uomRepository.findById(3L).get(), "Description ingredient 3",
				BigDecimal.valueOf(3), r2);

		r2.getCategories().add(c2);
		r2.getCategories().add(c3);

		r2.setCookTime(2);
		r2.setDescription("Description for recipe2");
		r2.setDifficulty(Difficulty.HARD);
		r2.setDirections("Directions for recipe 2");
		r2.getIngredient().add(i3);
		r2.setNotes(new Notes("Notes for recipe2"));
		r2.setPrepTime(1);
		r2.setServings(1);
		r2.setSource("Source for recipe 2");
		r2.setUrl("http://test2.pl");

		recipes.add(r2);

		return recipes;
	}
}
