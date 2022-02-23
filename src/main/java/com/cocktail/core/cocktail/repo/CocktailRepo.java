package com.cocktail.core.cocktail.repo;

import com.cocktail.core.cocktail.domain.Cocktail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CocktailRepo extends JpaRepository<Cocktail, Long> {
}
