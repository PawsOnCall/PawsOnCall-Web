package com.ruoyi.business.domain.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CreateOrderDTO {
    private Long providerUserId;

    private String providerName;

    private Long consumerUserId;

    private String consumerName;

    private Date serviceTime;

    private Date dropOffTimeStart;

    private Date dropOffTimeEnd;

    private Date pickUpTimeStart;

    private Date pickUpTimeEnd;

    private String snapshot;
}
