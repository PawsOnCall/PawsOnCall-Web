package com.ruoyi.business.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.business.domain.AvailableDate;
import com.ruoyi.business.domain.OrderInfo;
import com.ruoyi.business.domain.Payment;
import com.ruoyi.business.domain.dto.CreateOrderDTO;
import com.ruoyi.business.domain.dto.EvaluateOrderDTO;
import com.ruoyi.business.domain.dto.OrderInfoDTO;
import com.ruoyi.business.mapper.AvailableDateMapper;
import com.ruoyi.business.mapper.GroomerMapper;
import com.ruoyi.business.mapper.OrderInfoMapper;
import com.ruoyi.business.mapper.PaymentMapper;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.bean.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private GroomerMapper groomerMapper;

    @Autowired
    private PaymentMapper paymentMapper;

    @Autowired
    private AvailableDateMapper availableDateMapper;

    @Autowired
    private GroomerService groomerService;

    @Autowired
    private CustomerService customerService;

    public List<OrderInfo> getOrders(Long userId, String userType, String status) {
        if ("customer".equals(userType)) {
            if (StringUtils.isEmpty(status)) {
                return orderInfoMapper.selectList(new LambdaQueryWrapper<OrderInfo>()
                        .eq(OrderInfo::getConsumerUserId, userId));
            } else {
                return orderInfoMapper.selectList(new LambdaQueryWrapper<OrderInfo>()
                        .eq(OrderInfo::getConsumerUserId, userId)
                        .eq(OrderInfo::getStatus, status)
                );
            }
        } else if ("groomer".equals(userType)) {
            if (StringUtils.isEmpty(status)) {
                return orderInfoMapper.selectList(new LambdaQueryWrapper<OrderInfo>()
                        .eq(OrderInfo::getProviderUserId, userId));
            } else {
                return orderInfoMapper.selectList(new LambdaQueryWrapper<OrderInfo>()
                        .eq(OrderInfo::getProviderUserId, userId)
                        .eq(OrderInfo::getStatus, status)
                );
            }
        } else {
            return new ArrayList<>();
        }
    }

    @Transactional
    public Boolean crateOrder(CreateOrderDTO createOrderDTO) {
        Payment providerPayment = paymentMapper.selectOne(new LambdaQueryWrapper<Payment>()
                .eq(Payment::getUserId, createOrderDTO.getProviderUserId()));
        if (providerPayment == null) {
            throw new RuntimeException("groomer has no payment method");
        }
        Payment consumerPayment = paymentMapper.selectOne(new LambdaQueryWrapper<Payment>()
                .eq(Payment::getUserId, createOrderDTO.getConsumerUserId()));
        if (consumerPayment == null) {
            throw new RuntimeException("customer has no payment method");
        }

        BigDecimal serviceFee = createOrderDTO.getServiceFee();

        providerPayment.setBalance(providerPayment.getBalance().add(serviceFee));
        consumerPayment.setBalance(consumerPayment.getBalance().subtract(serviceFee));
        paymentMapper.updateById(providerPayment);
        paymentMapper.updateById(consumerPayment);

        List<AvailableDate> availableDates = availableDateMapper.selectList(new LambdaQueryWrapper<AvailableDate>()
                .eq(AvailableDate::getUserId, createOrderDTO.getProviderUserId()));
        availableDates.forEach(availableDate -> {
            if (availableDate.getAvailableDate().equals(createOrderDTO.getServiceTime())) {
                availableDate.setStatus("ORDERED");
                availableDateMapper.updateById(availableDate);
            }
        });

        OrderInfo orderInfo = new OrderInfo();
        BeanUtils.copyBeanProp(orderInfo, createOrderDTO);
        orderInfo.setGroomerFee(serviceFee);
        orderInfo.setStatus("PENDING");
        orderInfo.setId(null);
        orderInfo.setCreateTime(new Date());
        orderInfoMapper.insert(orderInfo);

        return true;
    }

    public Boolean evaluateOrder(EvaluateOrderDTO evaluateOrderDTO) {
        OrderInfo orderInfo = orderInfoMapper.selectById(evaluateOrderDTO.getOrderId());
        if (orderInfo != null) {
            BeanUtils.copyBeanProp(orderInfo, evaluateOrderDTO);
            orderInfo.setStatus("FINISHED");
            orderInfoMapper.updateById(orderInfo);
            return true;
        } else {
            return false;
        }
    }

    public OrderInfoDTO getOrderDetail(Long id) {
        OrderInfoDTO retVal = new OrderInfoDTO();
        OrderInfo orderInfo = orderInfoMapper.selectById(id);
        BeanUtils.copyBeanProp(retVal, orderInfo);
        retVal.setGroomerDashboardDTO(groomerService.dashboard(orderInfo.getProviderUserId()));
        retVal.setCustomerDashboardDTO(customerService.dashboard(orderInfo.getConsumerUserId()));
        return retVal;
    }
}
