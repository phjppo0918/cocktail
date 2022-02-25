package com.cocktail.core.Order.domain;

import com.cocktail.core.cocktail.domain.Cocktail;
import com.cocktail.core.payment.domain.Payment;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "payment_id", nullable = false)
    private Payment payment;


    @ManyToOne
    @JoinColumn(name = "cocktail_id", nullable = false)
    private Cocktail cocktail;

    private Integer cocktailCount = 1;
}
