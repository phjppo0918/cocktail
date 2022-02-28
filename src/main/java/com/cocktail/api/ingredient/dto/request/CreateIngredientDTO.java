package com.cocktail.api.ingredient.dto.request;

import com.cocktail.common.DTO;
import com.cocktail.core.ingredient.domain.Ingredient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@NoArgsConstructor
@Getter
@Setter
public class CreateIngredientDTO implements DTO<Ingredient> {
    private String name;
    private Double count;
    private Integer pricePerOne;

    @Override
    public Ingredient toEntity() {
        return Ingredient.builder()
                .name(name).pricePerOne(pricePerOne)
                .count(Optional.ofNullable(count).orElse(0.0))
                .build();
    }
}
