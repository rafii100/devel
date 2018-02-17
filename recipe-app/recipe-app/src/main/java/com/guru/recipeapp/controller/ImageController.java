package com.guru.recipeapp.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.h2.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.guru.recipeapp.commands.RecipeCommand;
import com.guru.recipeapp.converters.CategoryToCategoryCommand;
import com.guru.recipeapp.converters.IngredientToIngredientCommand;
import com.guru.recipeapp.converters.NotesToNotesCommand;
import com.guru.recipeapp.converters.RecipeToRecipeCommand;
import com.guru.recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.guru.recipeapp.domain.Recipe;
import com.guru.recipeapp.repositories.RecipeRepository;
import com.guru.recipeapp.service.RecipeServise;

@Controller
public class ImageController{

	@Autowired
	RecipeServise recipeService;

	@Autowired
	RecipeRepository recipeRepository;

	@PostMapping("/recipe/image/save/{id}")
	public String handleImagePost(@PathVariable String id, @RequestParam("imagefile") MultipartFile file) {

		Recipe recipe = recipeService.getRecipeById(Long.valueOf(id));
		try {
			Byte[] bytes = new Byte[file.getBytes().length];

			int i = 0;
			for (Byte b : file.getBytes()) {
				bytes[i++] = b;
			}
			recipe.setImage(bytes);

			recipeRepository.save(recipe);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/recipe/show/" + id;
	}

	@GetMapping("/recipe/image/{id}")
	public String showForm(@PathVariable String id, Model model) {

		Recipe recipe = recipeService.getRecipeById(Long.valueOf(id));
		RecipeToRecipeCommand converter = new RecipeToRecipeCommand(new CategoryToCategoryCommand(),
				new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()),
				new NotesToNotesCommand());
		RecipeCommand command = converter.convert(recipe);
		model.addAttribute("recipe", command);
		return "/recipe/imageupload";
	}

	@GetMapping("/recipe/image/ret/{id}")
	public void getImage(@PathVariable String id, HttpServletResponse response) throws IOException {

		Recipe recipe = recipeService.getRecipeById(Long.valueOf(id));
		RecipeToRecipeCommand converter = new RecipeToRecipeCommand(new CategoryToCategoryCommand(),
				new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()),
				new NotesToNotesCommand());
		RecipeCommand command = converter.convert(recipe);

		if (command.getImage() != null) {
			byte[] byteArray = new byte[command.getImage().length];

			int i = 0;
			for (Byte b : command.getImage()) {
				byteArray[i++] = b;
			}

			response.setContentType("image/jpeg");
			InputStream is = new ByteArrayInputStream(byteArray);
			IOUtils.copy(is, response.getOutputStream());
		}

	}
}
