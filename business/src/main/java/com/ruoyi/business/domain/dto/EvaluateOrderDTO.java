package com.ruoyi.business.domain.dto;

import lombok.Data;

@Data
public class EvaluateOrderDTO {
    private Integer orderId;

    private Integer reviewStars;

    private String reviewContent;
}
