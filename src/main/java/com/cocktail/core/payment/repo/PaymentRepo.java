package com.cocktail.core.payment.repo;

import com.cocktail.core.payment.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PaymentRepo extends JpaRepository<Payment, Long> {

    @Query("SELECT p FROM Payment p " +
            "WHERE p.datetime >= :start AND p.datetime <= :end")
    List<Payment> findAllByDatetimeRange(LocalDateTime start, LocalDateTime end);
}
