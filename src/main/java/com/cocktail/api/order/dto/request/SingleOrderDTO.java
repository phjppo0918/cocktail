package com.cocktail.api.order.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SingleOrderDTO {
    private Long paymentId;
    private Long cocktailId;
    private Integer amount;
}
