package com.cocktail.core.ingredient.svc.impl;

import com.cocktail.common.exceptions.businessError.EntityNotFoundException;
import com.cocktail.common.exceptions.businessError.OutOfStockException;
import com.cocktail.core.ingredient.domain.Ingredient;
import com.cocktail.core.ingredient.repo.IngredientRepo;
import com.cocktail.core.ingredient.svc.IngredientSvc;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IngredientSvcImpl implements IngredientSvc {

    private final IngredientRepo ingredientRepo;

    @Override
    public Ingredient create(Ingredient ingredient) {
        return ingredientRepo.save(ingredient);
    }

    @Override
    public List<Ingredient> createAll(List<Ingredient> ingredients) {
        return ingredientRepo.saveAll(ingredients);
    }

    @Override
    public List<Ingredient> getAll() {
        return ingredientRepo.findAll();
    }

    @Override
    public Ingredient getById(Long id) {
        return ingredientRepo.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Ingredient updateById(Long id, Ingredient ingredient) {

        Ingredient target = getById(id);
        Optional.ofNullable(ingredient.getName()).ifPresent(n -> target.setName(n));
        Optional.ofNullable(ingredient.getCount()).ifPresent(c -> target.setCount(c));
        Optional.ofNullable(ingredient.getPricePerOne()).ifPresent(p -> target.setPricePerOne(p));

        return ingredientRepo.save(target);
    }

    @Override
    public Ingredient deleteById(Long id) {
        Ingredient target = getById(id);
        ingredientRepo.deleteById(id);

        return target;
    }

    @Override
    public Ingredient addCount(Long id, Double count) {
        Ingredient target = getById(id);
        target.setCount(target.getCount() + count);

        return ingredientRepo.save(target);
    }

    @Override
    public Ingredient use(Long id, Double amount) {
        Ingredient target = getById(id);

        checkStock(amount, target.getCount());
        target.setCount(target.getCount() - amount);
        return ingredientRepo.save(target);
    }

    private void checkStock(Double requestCount, Double remainCount) {
        if(isStockFulfillment(requestCount, remainCount)) {
            throw new OutOfStockException(requestCount, remainCount);
        }
    }

    private boolean isStockFulfillment(Double requestCount, Double remainCount) {
        return requestCount <= remainCount;
    }
}
