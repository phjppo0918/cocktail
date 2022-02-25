package com.cocktail.api.order.dto.response;

import com.cocktail.core.Order.domain.Order;
import com.cocktail.core.payment.domain.Payment;
import com.cocktail.core.payment.domain.PaymentMethod;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GetOrderDTO {
    private Long paymentId;
    private Integer amount;
    private LocalDateTime datetime;
    private PaymentMethod method;

    private Map<String, Integer> cocktailOrder;

    public static GetOrderDTO of(List<Order> orders) {
        Map<String, Integer> cocktailOrder = new HashMap<>();
        orders.stream().peek(o ->
                cocktailOrder.put(o.getCocktail().getName(), o.getCocktailCount()));
        Payment payment = orders.get(0).getPayment();
        return new GetOrderDTO(
                payment.getId(),
                payment.getAmount(),
                payment.getDatetime(),
                payment.getMethod(),
                cocktailOrder);
    }

    private GetOrderDTO(Long paymentId, Integer amount, LocalDateTime datetime, PaymentMethod method, Map<String, Integer> cocktailOrder) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.datetime = datetime;
        this.method = method;
        this.cocktailOrder = cocktailOrder;
    }
}
