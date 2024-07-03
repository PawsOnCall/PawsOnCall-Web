package com.ruoyi.business.controller;

import com.ruoyi.business.domain.Order;
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
    public R<List<Order>> getOrders(Long userId, String userType) {
        return R.ok(orderService.getOrders(userId, userType));
    }

    @PostMapping("/crateOrder")
    public R<Boolean> crateOrder(@RequestBody Order order) {
        return R.ok(orderService.crateOrder(order));
    }

}

