package com.cocktail.core.cocktailRecipe.svc.impl;

import com.cocktail.common.exceptions.businessError.EntityNotFoundException;
import com.cocktail.core.cocktail.domain.Cocktail;
import com.cocktail.core.cocktailRecipe.domain.CocktailRecipe;
import com.cocktail.core.cocktailRecipe.repo.CocktailRecipeRepo;
import com.cocktail.core.cocktailRecipe.svc.CocktailRecipeSvc;
import com.cocktail.core.ingredient.svc.IngredientSvc;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CocktailRecipeSvcImpl implements CocktailRecipeSvc {

    private final CocktailRecipeRepo cocktailRecipeRepo;

    private final IngredientSvc ingredientSvc;

    @Override
    public CocktailRecipe create(CocktailRecipe cocktailRecipe) {
        return cocktailRecipeRepo.save(cocktailRecipe);
    }

    @Override
    public List<CocktailRecipe> createAllByOneCocktail(Cocktail cocktail, Map<Long, Double> ingredients) {

        return cocktailRecipeRepo.saveAll(ingredients.keySet().stream().map(i ->
                CocktailRecipe.builder().cocktail(cocktail)
                        .ingredient(ingredientSvc.getById(i))
                        .ingredientAmount(ingredients.get(i))
                        .build()
        ).collect(Collectors.toList()));
    }

    @Override
    public List<CocktailRecipe> createAll(List<CocktailRecipe> cocktailRecipes) {
        return cocktailRecipeRepo.saveAll(cocktailRecipes);
    }

    @Override
    public List<CocktailRecipe> getAll() {
        return cocktailRecipeRepo.findAll();
    }

    @Override
    public CocktailRecipe getById(Long id) {
        return cocktailRecipeRepo.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public CocktailRecipe updateById(Long id, CocktailRecipe cocktailRecipe) {
        CocktailRecipe target = getById(id);
        Optional.ofNullable(cocktailRecipe.getCocktail()).ifPresent(c -> target.setCocktail(c));
        Optional.ofNullable(cocktailRecipe.getIngredient()).ifPresent(i -> target.setIngredient(i));
        Optional.ofNullable(cocktailRecipe.getIngredientAmount()).ifPresent(ic -> target.setIngredientAmount(ic));

        return cocktailRecipeRepo.save(target);
    }

    @Override
    public CocktailRecipe deleteById(Long id) {
        CocktailRecipe target = getById(id);
        cocktailRecipeRepo.deleteById(id);
        return target;
    }

    @Override
    public List<CocktailRecipe> getByCocktail(Long cocktailId) {
        return cocktailRecipeRepo.findAllByCocktailId(cocktailId);
    }

    @Override
    public void makeCocktail(Long cocktailId, Integer count) {
        List<CocktailRecipe> recipe = getByCocktail(cocktailId);
        bringIngredient(count, recipe);
    }

    @Override
    public void makeCocktails(Map<Long, Integer> order) {
        order.keySet().stream().peek(m ->
                bringIngredient(order.get(m), getByCocktail(m)));
    }

    private void bringIngredient(Integer count, List<CocktailRecipe> recipe) {
        recipe.stream().peek(m ->
                ingredientSvc.use(m.getIngredient().getId(),
                        m.getIngredientAmount() * count));

    }
}
