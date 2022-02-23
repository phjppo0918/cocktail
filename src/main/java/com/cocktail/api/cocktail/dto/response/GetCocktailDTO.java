package com.cocktail.api.cocktail.dto.response;


import com.cocktail.core.cocktail.domain.Cocktail;
import com.cocktail.core.cocktailRecipe.domain.CocktailRecipe;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GetCocktailDTO {
    private Long id;
    private String name;
    private Integer price;
    private Double alcoholLevel;
    private List<RecipeIngredientDTO> cocktailRecipes;

    public static GetCocktailDTO of(Cocktail cocktail) {
        return new GetCocktailDTO(cocktail);
    }

    public static GetCocktailDTO of(Cocktail cocktail, List<CocktailRecipe> recipes) {
        return new GetCocktailDTO(cocktail, recipes);
    }

    public static List<GetCocktailDTO> listOf(List<Cocktail> cocktails) {
        return cocktails.stream().map(GetCocktailDTO::new)
                .collect(Collectors.toList());
    }
    public static List<GetCocktailDTO> listOf(Map<Cocktail, List<CocktailRecipe>> cocktails) {
       return cocktails.keySet().stream()
               .map(m -> new GetCocktailDTO(m, cocktails.get(m)))
               .collect(Collectors.toList());

    }


    private GetCocktailDTO(Cocktail cocktail, List<CocktailRecipe> recipes) {
        this(cocktail);
        this.cocktailRecipes = RecipeIngredientDTO.listOf(recipes);
    }

    private GetCocktailDTO(Cocktail cocktail) {
        this.id = cocktail.getId();
        this.name = cocktail.getName();
        this.price = cocktail.getPrice();
        this.alcoholLevel = cocktail.getAlcoholLevel();
    }


}
