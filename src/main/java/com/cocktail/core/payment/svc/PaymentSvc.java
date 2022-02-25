package com.cocktail.core.payment.svc;

import com.cocktail.common.InitSvc;
import com.cocktail.core.payment.domain.Payment;
import com.cocktail.core.payment.domain.PaymentMethod;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface PaymentSvc extends InitSvc<Payment, Long> {

    List<Payment> getPaymentByDateTimeRange(LocalDateTime start, LocalDateTime end);
    Payment pay(Long id, PaymentMethod method);

    Map<Integer, Integer> getTotalSalesByDay();
    Map<Integer, Integer> getTotalSalesByWeek();
    Map<Integer, Integer> getTotalSalesByMonth();
}
