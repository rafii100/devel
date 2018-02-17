package com.guru.recipeapp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.guru.recipeapp.commands.RecipeCommand;
import com.guru.recipeapp.converters.CategoryToCategoryCommand;
import com.guru.recipeapp.converters.IngredientToIngredientCommand;
import com.guru.recipeapp.converters.NotesToNotesCommand;
import com.guru.recipeapp.converters.RecipeToRecipeCommand;
import com.guru.recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.guru.recipeapp.domain.Recipe;
import com.guru.recipeapp.exceptions.BusinessException;
import com.guru.recipeapp.service.RecipeServise;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class RecipeController {

	@Autowired
	private RecipeServise recipeService;

	@GetMapping("/recipe/show/{id}")
	public String showById(@PathVariable String id, Model model) {

		Recipe recipe = recipeService.getRecipeById(Long.valueOf(id));
        RecipeToRecipeCommand converter = new RecipeToRecipeCommand(new CategoryToCategoryCommand(), new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()), new NotesToNotesCommand());		
		RecipeCommand recipeCommand = 	converter.convert(recipe);
		
		model.addAttribute("recipe", recipeCommand);
		
		return "recipe/show";
	}

	@GetMapping("/recipe/update/{id}")
	public String updateById(@PathVariable String id, Model model) {

		
		try {
			Recipe recipe = recipeService.getRecipeById(Long.valueOf(id));
			model.addAttribute("recipe", recipe);
		} catch (NumberFormatException e) {
			log.info("convert to business exception");
			throw new BusinessException(e.getMessage());
		}

		return "recipe/recipe";
	}

	@GetMapping("/recipe/remove/{id}")
	public String removeById(@PathVariable String id) {

		recipeService.removeById(Long.valueOf(id));

		return "redirect:/";
	}

	@GetMapping("/recipe/add")
	public String add(Model model) {
		model.addAttribute("recipe", new RecipeCommand());
		return "recipe/recipe";
	}

	@PostMapping("/recipe/save")
	public String add(@Valid @ModelAttribute("recipe") RecipeCommand command, BindingResult bindingResult) {
		System.out.println("____SAVE");
		if (bindingResult.hasErrors()) {
			for (ObjectError error : bindingResult.getAllErrors()) {
				System.out.println("_ " + error.toString());
			}
			
			return "/recipe/recipe";
		} else {
			recipeService.saveRecipeCommand(command);
		}
		return "redirect:/";
	}
}
