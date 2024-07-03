package com.ruoyi.business.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.business.domain.Payment;
import com.ruoyi.business.mapper.PaymentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentService {
    @Autowired
    private PaymentMapper paymentMapper;

    public Payment getPayment(Long userId) {
        return paymentMapper.selectOne(new LambdaQueryWrapper<Payment>()
                .eq(Payment::getUserId, userId));
    }

    public Boolean savePayment(Payment payment) {
        Payment paymentInDb = getPayment(payment.getUserId());
        if (paymentInDb == null) {
            payment.setBalance(new BigDecimal(0));
            payment.setId(null);
            paymentMapper.insert(payment);
        } else {
            payment.setBalance(paymentInDb.getBalance());
            payment.setId(paymentInDb.getId());
            paymentMapper.updateById(payment);
        }
        return true;
    }

    public Boolean recharge(Long userId, BigDecimal balance) {
        Payment payment = getPayment(userId);
        if (payment != null) {
            payment.setBalance(payment.getBalance().add(balance));
            paymentMapper.updateById(payment);
            return true;
        } else {
            return false;
        }
    }
}
