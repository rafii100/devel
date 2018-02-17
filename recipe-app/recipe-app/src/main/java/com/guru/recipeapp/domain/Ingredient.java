package com.guru.recipeapp.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Ingredient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	private UnitOfMessure unitOfMessure;
	
	@Lob
	private String description;
	private BigDecimal amount;
	
	@ManyToOne()
	private Recipe recipe;
	
	public Ingredient() {
		
	}

	
	public Ingredient(UnitOfMessure unitOfMessure, String description, BigDecimal amount, Recipe recipe) {
		this.unitOfMessure = unitOfMessure;
		this.description = description;
		this.amount = amount;
		this.recipe = recipe;
	}

}
