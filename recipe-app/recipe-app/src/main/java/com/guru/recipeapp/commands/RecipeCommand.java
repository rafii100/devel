package com.guru.recipeapp.commands;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

import com.guru.recipeapp.domain.Difficulty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {

	private Long id;
	@Size(min=3, max=20)
	private String description;
	private Integer prepTime;
	private Integer cookTime;
	private Integer servings;
	private String source;
	@URL
	@NotNull
	private String url;
	private String directions;
	private List<CategoryCommand> categories = new ArrayList<>();
	private Difficulty difficulty;
	private List<IngredientCommand> ingredient = new ArrayList<>();
	private NotesCommand notes;
	private Byte[] image;
}
