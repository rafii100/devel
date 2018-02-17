package com.guru.recipeapp.converters;

import static org.junit.Assert.assertEquals;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.guru.recipeapp.commands.RecipeCommand;
import com.guru.recipeapp.domain.Recipe;
import com.guru.recipeapp.repositories.RecipeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeConverterTest {

	
	@Autowired
	RecipeRepository repository;
	
	@Test
	@Transactional
	public void test() {
		Recipe recipeFromRepository =repository.findById(1L).get();
		RecipeToRecipeCommand converter = new RecipeToRecipeCommand(new CategoryToCategoryCommand(), new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()), new NotesToNotesCommand());
		
		RecipeCommand recipeCommand = 	converter.convert(recipeFromRepository);
		
		assertEquals(recipeFromRepository.getId(), recipeCommand.getId());
		assertEquals(recipeFromRepository.getDescription(), recipeCommand.getDescription());
	}

}
