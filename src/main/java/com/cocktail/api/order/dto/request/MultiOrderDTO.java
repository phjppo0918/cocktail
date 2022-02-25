package com.cocktail.api.order.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@NoArgsConstructor
@Getter
@Setter
public class MultiOrderDTO {
    private Long paymentId;
    private Map<Long, Integer> orders;
}
