package com.ruoyi.business.controller;

import com.ruoyi.business.domain.OrderInfo;
import com.ruoyi.business.domain.dto.CreateOrderDTO;
import com.ruoyi.business.domain.dto.EvaluateOrderDTO;
import com.ruoyi.business.service.OrderService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController extends BaseController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/getOrders")
    public R<List<OrderInfo>> getOrders(Long userId, String userType) {
        return R.ok(orderService.getOrders(userId, userType));
    }

    @PostMapping("/createOrder")
    public R<Boolean> crateOrder(@RequestBody CreateOrderDTO createOrderDTO) {
        return R.ok(orderService.crateOrder(createOrderDTO));
    }

    @PostMapping("/evaluateOrder")
    public R<Boolean> evaluateOrder(@RequestBody EvaluateOrderDTO evaluateOrderDTO) {
        return R.ok(orderService.evaluateOrder(evaluateOrderDTO));
    }

}

