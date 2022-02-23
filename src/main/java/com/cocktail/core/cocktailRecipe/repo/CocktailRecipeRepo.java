package com.cocktail.core.cocktailRecipe.repo;

import com.cocktail.core.cocktailRecipe.domain.CocktailRecipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CocktailRecipeRepo extends JpaRepository<CocktailRecipe, Long> {

    List<CocktailRecipe> findAllByCocktailId(Long CocktailId);
}
