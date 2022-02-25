package com.cocktail.api.payment;

import com.cocktail.api.payment.dto.request.PayMethodDTO;
import com.cocktail.core.payment.domain.Payment;
import com.cocktail.core.payment.svc.PaymentSvc;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("payment")
public class PaymentCtrl {
    private final PaymentSvc paymentSvc;

    @PostMapping
    public void create() {
        paymentSvc.create(new Payment());
    }

    @GetMapping
    public List<Payment> gets() {
        return paymentSvc.getAll();
    }

    @GetMapping("{id}")
    public Payment get(@PathVariable Long id) {
        return paymentSvc.getById(id);
    }

    @PostMapping("{id}/pay")
    public Payment pay(@PathVariable Long id, @RequestBody PayMethodDTO dto) {
        return paymentSvc.pay(id, dto.toEntity());
    }

    @DeleteMapping("{id}")
    public void remove(@PathVariable Long id) {
        paymentSvc.deleteById(id);
    }

}
