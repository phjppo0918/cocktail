package com.cocktail.core.Order.repo;

import com.cocktail.core.Order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {

    List<Order> findAllByPaymentId(Long paymentId);
}
