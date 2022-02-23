package com.cocktail.core.cocktail.svc.impl;

import com.cocktail.common.exceptions.businessError.EntityNotFoundException;
import com.cocktail.core.cocktail.domain.Cocktail;
import com.cocktail.core.cocktail.repo.CocktailRepo;
import com.cocktail.core.cocktail.svc.CocktailSvc;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CocktailSvcImpl implements CocktailSvc {

    private final CocktailRepo cocktailRepo;

    @Override
    public Cocktail create(Cocktail cocktail) {
        return cocktailRepo.save(cocktail);
    }

    @Override
    public List<Cocktail> createAll(List<Cocktail> cocktails) {
        return cocktailRepo.saveAll(cocktails);
    }

    @Override
    public List<Cocktail> getAll() {
        return cocktailRepo.findAll();
    }

    @Override
    public Cocktail getById(Long aLong) {
        return cocktailRepo.findById(aLong)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Cocktail updateById(Long id, Cocktail cocktail) {

        Cocktail target = getById(id);
        Optional.ofNullable(cocktail.getName()).ifPresent(n -> target.setName(n));
        Optional.ofNullable(cocktail.getPrice()).ifPresent(p -> target.setPrice(p));
        Optional.ofNullable(cocktail.getAlcoholLevel()).ifPresent(al -> target.setAlcoholLevel(al));

        return cocktailRepo.save(target);
    }

    @Override
    public Cocktail deleteById(Long id) {
        Cocktail target = getById(id);
        cocktailRepo.deleteById(id);
        return target;
    }

}
