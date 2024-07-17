package com.ruoyi.business.domain.dto;

import com.ruoyi.business.domain.OrderInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class OrderInfoDTO extends OrderInfo {
    private GroomerDashboardDTO groomerDashboardDTO;

    private CustomerDashboardDTO customerDashboardDTO;
}
