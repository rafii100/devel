package com.guru.recipeapp.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String description;
	private Integer prepTime;
	private Integer cookTime;
	private Integer servings;
	private String source;
	private String url;
	private String directions;
	
	@ManyToMany
	@JoinTable(name="recipe_category", 
	joinColumns=@JoinColumn(name="recipe_id"), 
	inverseJoinColumns=@JoinColumn(name="category_id"))
	private List<Category> categories = new ArrayList<Category>();

	@Enumerated(value = EnumType.STRING)
	private Difficulty difficulty;

	@OneToMany(mappedBy="recipe", cascade = CascadeType.ALL)
	private List<Ingredient> ingredient = new ArrayList<Ingredient>();

	@Lob
	private Byte[] image;

	@OneToOne(cascade = CascadeType.ALL)
	private Notes notes;


}
