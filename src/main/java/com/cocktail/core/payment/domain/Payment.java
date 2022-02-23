package com.cocktail.core.payment.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue
    private Long id;

    private Integer amount = 0;
    private LocalDateTime datetime;

    @Enumerated(EnumType.STRING)
    private PaymentMethod method;

}
