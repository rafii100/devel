package com.guru.recipeapp.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IngredientCommand {

	private Long id;
	private UnitOfMeasureCommand unitOfMessure;
	private String description;
	private Float amount;
}
