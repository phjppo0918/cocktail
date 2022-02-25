package com.cocktail.api.payment.dto.request;

import com.cocktail.common.DTO;
import com.cocktail.core.payment.domain.PaymentMethod;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PayMethodDTO implements DTO<PaymentMethod> {
    private String paymentMethod;

    @Override
    public PaymentMethod toEntity() {
        return PaymentMethod.valueOf(paymentMethod);
    }
}
