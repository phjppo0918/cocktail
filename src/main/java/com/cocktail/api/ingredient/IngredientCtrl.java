package com.cocktail.api.ingredient;

import com.cocktail.api.ingredient.dto.request.CreateIngredientDTO;
import com.cocktail.api.ingredient.dto.request.UpdateIngredientDTO;
import com.cocktail.core.ingredient.domain.Ingredient;
import com.cocktail.core.ingredient.svc.IngredientSvc;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("ingredient")
public class IngredientCtrl {

    private final IngredientSvc ingredientSvc;

    @GetMapping
    public List<Ingredient> gets() {
        return ingredientSvc.getAll();
    }

    @GetMapping("{id}")
    public Ingredient get(@PathVariable Long id) {
        return ingredientSvc.getById(id);
    }

    @PostMapping
    public void create(@RequestBody CreateIngredientDTO dto) {
        ingredientSvc.create(dto.toEntity());
    }

    @PostMapping("{id}/count")
    public void addCount(@PathVariable Long id, @RequestBody Map<String, Double> req) {
        ingredientSvc.addCount(id, req.get("count"));
    }

    @PutMapping("{id}")
    public void edit(@PathVariable Long id, @RequestBody UpdateIngredientDTO dto) {
        ingredientSvc.updateById(id, dto.toEntity());
    }

    @DeleteMapping("{id}")
    public void remove(@PathVariable Long id) {
        ingredientSvc.deleteById(id);
    }

}
