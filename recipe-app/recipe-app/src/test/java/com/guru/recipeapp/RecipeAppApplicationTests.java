package com.guru.recipeapp;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.guru.recipeapp.domain.UnitOfMessure;
import com.guru.recipeapp.repositories.UnitOfMeasureRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RecipeAppApplicationTests {

	
	@Autowired
	UnitOfMeasureRepository repository;
	
	@Test
	public void test() {
		Optional<UnitOfMessure> uom = repository.findByUom("Teaspoon");
		assertEquals("Teaspoon", uom.get().getUom());
	}

}
