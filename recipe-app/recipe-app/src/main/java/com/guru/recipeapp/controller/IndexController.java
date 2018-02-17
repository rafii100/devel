package com.guru.recipeapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.guru.recipeapp.service.RecipeServise;

@Controller
public class IndexController{
	
	@Autowired
	private RecipeServise recipeService;
	

	
	
	@RequestMapping({"", "/", "/index"})
	public String getIndexPage(Model model) {
		
		
		model.addAttribute("recipes", recipeService.getRecipes());
		return "index";
	}

}
