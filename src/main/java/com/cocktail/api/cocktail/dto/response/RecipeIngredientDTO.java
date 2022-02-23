package com.cocktail.api.cocktail.dto.response;

import com.cocktail.core.cocktailRecipe.domain.CocktailRecipe;

import java.util.List;
import java.util.stream.Collectors;

public class RecipeIngredientDTO {
    private Long ingredientId;
    private String name;
    private Double ingredientAmount;

    public static List<RecipeIngredientDTO> listOf(List<CocktailRecipe> cocktailRecipes) {
        return cocktailRecipes.stream().map(RecipeIngredientDTO::new)
                .collect(Collectors.toList());
    }

    private RecipeIngredientDTO(CocktailRecipe cocktailRecipe) {
        this.ingredientId = cocktailRecipe.getIngredient().getId();
        this.name = cocktailRecipe.getIngredient().getName();
        this.ingredientAmount = cocktailRecipe.getIngredientAmount();
    }
}
