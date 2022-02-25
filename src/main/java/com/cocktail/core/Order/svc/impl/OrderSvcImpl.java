package com.cocktail.core.Order.svc.impl;

import com.cocktail.common.exceptions.businessError.EntityNotFoundException;
import com.cocktail.core.Order.domain.Order;
import com.cocktail.core.Order.repo.OrderRepo;
import com.cocktail.core.Order.svc.OrderSvc;
import com.cocktail.core.cocktail.domain.Cocktail;
import com.cocktail.core.cocktail.svc.CocktailSvc;
import com.cocktail.core.cocktailRecipe.svc.CocktailRecipeSvc;
import com.cocktail.core.payment.domain.Payment;
import com.cocktail.core.payment.svc.PaymentSvc;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderSvcImpl implements OrderSvc {

    private final OrderRepo orderRepo;

    private final PaymentSvc paymentSvc;
    private final CocktailSvc cocktailSvc;
    private final CocktailRecipeSvc cocktailRecipeSvc;

    @Override
    public Order create(Order order) {
        return orderRepo.save(order);
    }

    @Override
    public List<Order> createAll(List<Order> orders) {
        return orderRepo.saveAll(orders);
    }

    @Override
    public List<Order> getAll() {
        return orderRepo.findAll();
    }

    @Override
    public Order getById(Long id) {
        return orderRepo.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Order updateById(Long id, Order order) {
        Order target = getById(id);
        Optional.ofNullable(order.getCocktail()).ifPresent(c -> target.setCocktail(c));
        Optional.ofNullable(order.getPayment()).ifPresent(p -> target.setPayment(p));
        Optional.ofNullable(order.getCocktailCount()).ifPresent(cc -> target.setCocktailCount(cc));
        return orderRepo.save(target);
    }

    @Override
    public Order deleteById(Long id) {
        Order target = getById(id);
        orderRepo.deleteById(id);
        return target;
    }

    @Override
    public Order order(Long paymentId, Long cocktailId, Integer amount) {
        cocktailRecipeSvc.makeCocktail(cocktailId, amount);

        Payment payment = paymentSvc.getById(paymentId);
        Cocktail cocktail = cocktailSvc.getById(cocktailId);

        Order order = Order.builder().payment(payment)
                .cocktail(cocktail)
                .cocktailCount(amount).build();

        addPrice(payment, cocktail, amount);

        return create(order);
    }

    @Override
    public List<Order> order(Long paymentId, Map<Long, Integer> requests) {
        cocktailRecipeSvc.makeCocktails(requests);

        return requests.keySet().stream()
                .map(c -> order(paymentId, c, requests.get(c)))
                .collect(Collectors.toList());

    }

    @Override
    public Order cancel(Long id) {
        Order target = getById(id);
        refund(target.getPayment(),
                getCocktailTotalPrice(target.getCocktail(),
                        target.getCocktailCount()));

        return deleteById(id);
    }

    private void refund(Payment payment, int price) {
        paymentSvc.updateById(payment.getId(),
                Payment.builder().amount(
                        payment.getAmount()
                            - price
                ).build());
    }

    @Override
    public List<Order> getAllByOrder(Long paymentId) {
        return orderRepo.findAllByPaymentId(paymentId);
    }

    private void addPrice(Payment payment, Cocktail cocktail, Integer amount) {
        paymentSvc.updateById(payment.getId(),
                Payment.builder()
                        .amount(payment.getAmount()
                                + getCocktailTotalPrice(cocktail, amount))
                        .build());
    }

    private Integer getCocktailTotalPrice(Cocktail cocktail, Integer amount) {
        return cocktail.getPrice() * amount;
    }
}
