package com.cocktail.api.order;

import com.cocktail.api.order.dto.request.MultiOrderDTO;
import com.cocktail.api.order.dto.request.SingleOrderDTO;
import com.cocktail.api.order.dto.response.GetOrderDTO;
import com.cocktail.core.Order.domain.Order;
import com.cocktail.core.Order.svc.OrderSvc;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("order")
public class OrderCtrl {
    private final OrderSvc orderSvc;

    @PostMapping
    public void order(@RequestBody SingleOrderDTO dto) {
        orderSvc.order(dto.getPaymentId(), dto.getCocktailId(), dto.getAmount());
    }

    @PostMapping("multiple")
    public void order(@RequestBody MultiOrderDTO dto) {
        orderSvc.order(dto.getPaymentId(), dto.getOrders());
    }

    @GetMapping
    public GetOrderDTO getTotal(@RequestParam(required = false) Long paymentId) {
        return GetOrderDTO.of(Optional.ofNullable(paymentId)
                .map(pi -> orderSvc.getAllByOrder(pi))
                .orElse(orderSvc.getAll()));
    }

    @GetMapping("{id}")
    public Order get(@PathVariable Long id) {
        return orderSvc.getById(id);
    }

    @DeleteMapping("{id}")
    public void cancel(@PathVariable Long id) {
        orderSvc.cancel(id);
    }

}
