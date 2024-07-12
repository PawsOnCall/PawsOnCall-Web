package com.ruoyi.business.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.business.domain.OrderInfo;
import com.ruoyi.business.domain.Payment;
import com.ruoyi.business.domain.UserInfo;
import com.ruoyi.business.mapper.OrderInfoMapper;
import com.ruoyi.business.mapper.PaymentMapper;
import com.ruoyi.business.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class PaymentService {
    @Autowired
    private PaymentMapper paymentMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private OrderInfoMapper orderInfoMapper;

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

    @Transactional
    public Boolean recharge(Long userId, BigDecimal balance) {
        Payment payment = getPayment(userId);
        if (payment != null) {
            payment.setBalance(payment.getBalance().add(balance));
            paymentMapper.updateById(payment);

            addOrder(userId, balance, "RECHARGE");
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public Boolean withdraw(Long userId, BigDecimal balance) {
        Payment payment = getPayment(userId);
        if (payment != null) {
            payment.setBalance(payment.getBalance().subtract(balance));
            paymentMapper.updateById(payment);

            addOrder(userId, balance, "WITHDRAW");
            return true;
        } else {
            return false;
        }
    }

    private void addOrder(Long userId, BigDecimal balance, String status) {
        UserInfo userInfo = userInfoMapper.selectOne(new LambdaQueryWrapper<UserInfo>()
                .eq(UserInfo::getUserId, userId));
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setStatus(status);
        orderInfo.setServiceFee(balance);
        orderInfo.setCreateTime(new Date());
        if ("customer".equals(userInfo.getUserType())) {
            orderInfo.setConsumerUserId(userId);
        } else if ("groomer".equals(userInfo.getUserType())) {
            orderInfo.setProviderUserId(userId);
        } else {
            throw new RuntimeException("unknown user type");
        }
        orderInfoMapper.insert(orderInfo);
    }
}
