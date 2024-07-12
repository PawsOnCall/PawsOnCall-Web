package com.ruoyi.business.domain.dto;

import com.ruoyi.business.domain.OrderInfo;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class GroomerDashboardDTO {
    private Long userId;

    private String firstName;

    private String lastName;

    private String photo;

    private BigDecimal balance;

    private BigDecimal upcomingEarning;

    private List<OrderInfo> notifications;
}
