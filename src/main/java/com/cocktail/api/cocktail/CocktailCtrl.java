package com.cocktail.api.cocktail;

import com.cocktail.api.cocktail.dto.request.CreateCocktailDTO;
import com.cocktail.api.cocktail.dto.response.GetCocktailDTO;
import com.cocktail.core.cocktail.domain.Cocktail;
import com.cocktail.core.cocktail.svc.CocktailSvc;
import com.cocktail.core.cocktailRecipe.domain.CocktailRecipe;
import com.cocktail.core.cocktailRecipe.svc.CocktailRecipeSvc;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("cocktail")
public class CocktailCtrl {

    private final CocktailSvc cocktailSvc;
    private final CocktailRecipeSvc cocktailRecipeSvc;

    @GetMapping
    public List<Cocktail> gets() {
        return cocktailSvc.getAll();
    }

    @GetMapping("{id}")
    public GetCocktailDTO get(@PathVariable Long id) {
        Cocktail cocktailRes = cocktailSvc.getById(id);
        List<CocktailRecipe> recipeRes = cocktailRecipeSvc.getByCocktail(id);
        return GetCocktailDTO.of(cocktailRes, recipeRes);
    }

    @PostMapping
    public void create(@RequestBody CreateCocktailDTO createCocktailDTO) {
        Cocktail cocktail = cocktailSvc.create(createCocktailDTO.toEntity());
        Optional.ofNullable(createCocktailDTO.getIngredients())
                .ifPresent(i -> cocktailRecipeSvc.createAllByOneCocktail(cocktail, i));
    }

}
