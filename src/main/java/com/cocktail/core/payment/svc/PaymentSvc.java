package com.cocktail.core.payment.svc;

import com.cocktail.common.InitSvc;
import com.cocktail.core.payment.domain.Payment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface PaymentSvc extends InitSvc<Payment, Long> {

    List<Payment> getPaymentByHour(LocalDateTime dateTime);
    List<Payment> getPaymentByDay(LocalDate localDate);
    List<Payment> getPaymentByMonth(LocalDate localDate);

    List<Integer> getTotalSalesByDay();
    List<Integer> getTotalSalesByWeek();
    List<Integer> getTotalSalesByMonth();
}
