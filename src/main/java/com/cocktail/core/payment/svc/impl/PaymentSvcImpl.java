package com.cocktail.core.payment.svc.impl;

import com.cocktail.common.exceptions.businessError.EntityNotFoundException;
import com.cocktail.core.payment.domain.Payment;
import com.cocktail.core.payment.repo.PaymentRepo;
import com.cocktail.core.payment.svc.PaymentSvc;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentSvcImpl implements PaymentSvc {

    private final PaymentRepo paymentRepo;

    @Override
    public Payment create(Payment payment) {
        return paymentRepo.save(payment);
    }

    @Override
    public List<Payment> createAll(List<Payment> payments) {
        return paymentRepo.saveAll(payments);
    }

    @Override
    public List<Payment> getAll() {
        return paymentRepo.findAll();
    }

    @Override
    public Payment getById(Long id) {
        return paymentRepo.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Payment updateById(Long id, Payment payment) {

        Payment target = getById(id);
        Optional.ofNullable(payment.getAmount()).ifPresent(a -> target.setAmount(a));
        Optional.ofNullable(payment.getDatetime()).ifPresent(dt -> target.setDatetime(dt));
        Optional.ofNullable(payment.getMethod()).ifPresent(m -> target.setMethod(m));
        return paymentRepo.save(target);
    }

    @Override
    public Payment deleteById(Long id) {
        Payment target = getById(id);
        paymentRepo.deleteById(id);
        return target;
    }

    @Override
    public List<Payment> getPaymentByHour(LocalDateTime dateTime) {
        return null;
    }

    @Override
    public List<Payment> getPaymentByDay(LocalDate localDate) {
        return null;
    }

    @Override
    public List<Payment> getPaymentByMonth(LocalDate localDate) {
        return null;
    }

    @Override
    public List<Integer> getTotalSalesByDay() {
        return null;
    }

    @Override
    public List<Integer> getTotalSalesByWeek() {
        return null;
    }

    @Override
    public List<Integer> getTotalSalesByMonth() {
        return null;
    }
}
