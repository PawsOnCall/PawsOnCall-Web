package com.ruoyi.business.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.business.domain.OrderInfo;
import com.ruoyi.business.mapper.CustomerMapper;
import com.ruoyi.business.mapper.GroomerMapper;
import com.ruoyi.business.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private GroomerMapper groomerMapper;

    public List<OrderInfo> getOrders(Long userId, String userType) {
        if ("customer".equals(userType)) {
            return orderMapper.selectList(new LambdaQueryWrapper<OrderInfo>()
                    .eq(OrderInfo::getConsumerUserId, userId));
        } else if ("groomer".equals(userType)) {
            return orderMapper.selectList(new LambdaQueryWrapper<OrderInfo>()
                    .eq(OrderInfo::getProviderUserId, userId));
        } else {
            return null;
        }
    }

    public Boolean crateOrder(OrderInfo orderInfo) {
        return null;
    }
}