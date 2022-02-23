package com.cocktail.api.cocktail.dto.request;

import com.cocktail.common.DTO;
import com.cocktail.core.cocktail.domain.Cocktail;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@NoArgsConstructor
@Getter
@Setter
public class CreateCocktailDTO implements DTO<Cocktail> {
    private String name;
    private Integer price;
    private Double alcoholLevel;

    private Map<Long, Double> ingredients;


    @Override
    public Cocktail toEntity() {
        return Cocktail.builder().name(name).price(price).alcoholLevel(alcoholLevel).build();
    }
}
