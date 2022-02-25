package com.cocktail.core.Order.svc;

import com.cocktail.common.InitSvc;
import com.cocktail.core.Order.domain.Order;

import java.util.List;
import java.util.Map;

public interface OrderSvc extends InitSvc<Order, Long> {

    Order order(Long PaymentId, Long cocktailId, Integer amount);
    List<Order> order(Long PaymentId, Map<Long, Integer> requests);
    Order cancel(Long id);

    List<Order> getAllByOrder(Long paymentId);

}
