package com.cocktail.core.cocktailRecipe.domain;

import com.cocktail.core.cocktail.domain.Cocktail;
import com.cocktail.core.ingredient.domain.Ingredient;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CocktailRecipe {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cocktail_id")
    @Column(nullable = false)
    private Cocktail cocktail;

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    @Column(nullable = false)
    private Ingredient ingredient;

    @Column(nullable = false)
    private Double ingredientAmount;
}
