package com.ruoyi.business.controller;

import com.ruoyi.business.domain.Payment;
import com.ruoyi.business.service.PaymentService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/payment")
public class PaymentController extends BaseController {
    @Autowired
    private PaymentService paymentService;

    @GetMapping("/getPayment")
    public R<Payment> getPayment(Long userId) {
        return R.ok(paymentService.getPayment(userId));
    }

    @PostMapping("/savePayment")
    public R<Boolean> savePayment(@RequestBody Payment payment) {
        return R.ok(paymentService.savePayment(payment));
    }

    @GetMapping("/recharge")
    public R<Boolean> recharge(Long userId, BigDecimal balance) {
        return R.ok(paymentService.recharge(userId, balance));
    }
}

