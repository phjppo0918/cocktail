package com.cocktail.core.cocktailRecipe.svc;

import com.cocktail.common.InitSvc;
import com.cocktail.core.cocktail.domain.Cocktail;
import com.cocktail.core.cocktailRecipe.domain.CocktailRecipe;

import java.util.List;
import java.util.Map;

public interface CocktailRecipeSvc extends InitSvc<CocktailRecipe, Long> {
    List<CocktailRecipe> createAllByOneCocktail(Cocktail cocktail, Map<Long, Double> ingredients);

    List<CocktailRecipe> getByCocktail(Long cocktailId);
    void makeCocktail(Long id, Integer count);
    void makeCocktails(Map<Long, Integer> order);
}
