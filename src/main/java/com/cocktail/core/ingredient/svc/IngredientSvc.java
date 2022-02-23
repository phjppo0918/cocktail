package com.cocktail.core.ingredient.svc;

import com.cocktail.common.InitSvc;
import com.cocktail.core.ingredient.domain.Ingredient;

public interface IngredientSvc extends InitSvc<Ingredient, Long> {

    Ingredient addCount(Long id, Double count);
    Ingredient use(Long id, Double amount);
}
