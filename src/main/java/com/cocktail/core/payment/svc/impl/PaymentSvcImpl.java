package com.cocktail.core.payment.svc.impl;

import com.cocktail.common.exceptions.businessError.EntityNotFoundException;
import com.cocktail.core.payment.domain.Payment;
import com.cocktail.core.payment.domain.PaymentMethod;
import com.cocktail.core.payment.repo.PaymentRepo;
import com.cocktail.core.payment.svc.PaymentSvc;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<Payment> getPaymentByDateTimeRange(LocalDateTime start, LocalDateTime end) {
        return paymentRepo.findAllByDatetimeRange(start, end);
    }

    @Override
    public Payment pay(Long id, PaymentMethod method) {
        Payment target = getById(id);
        target.setMethod(method);
        target.setDatetime(LocalDateTime.now());
        return paymentRepo.save(target);
    }

    @Override
    public Map<Integer, Integer> getTotalSalesByDay() {
        Map<Integer, List<Payment>> collects =
                getAll().stream().collect(Collectors.groupingBy(p ->
                        p.getDatetime().getDayOfYear()));

        return statisticPayment(collects);
    }

    @Override
    public Map<Integer, Integer> getTotalSalesByWeek() {
        Map<Integer, List<Payment>> collects =
                getAll().stream().collect(Collectors.groupingBy(p -> p.getDatetime().getDayOfWeek().getValue()));


        return statisticPayment(collects);
    }

    @Override
    public Map<Integer, Integer> getTotalSalesByMonth() {
        Map<Integer, List<Payment>> collects =
                getAll().stream().collect(Collectors.groupingBy(p -> p.getDatetime().getMonthValue()));
        return statisticPayment(collects);
    }

    private Map<Integer, Integer> statisticPayment(Map<Integer, List<Payment>> data) {
        Map<Integer, Integer> result = new HashMap();
        for (Integer k : data.keySet()) {
            result.put(k, data.get(k).stream().
                    map(Payment::getAmount)
                    .reduce(Integer::sum).orElse(0));
        }
        return result;
    }
}
