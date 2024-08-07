package com.ruoyi.business.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderInfo {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String status;

    private Long providerUserId;

    private String providerName;

    private Long consumerUserId;

    private String consumerName;

    private BigDecimal groomerFee;

    private BigDecimal serviceFee;

    private Integer reviewStars;

    private String reviewContent;

    private Date serviceTime;

    private Date createTime;

    private Date dropOffTimeStart;

    private Date dropOffTimeEnd;

    private Date pickUpTimeStart;

    private Date pickUpTimeEnd;

    private String snapshot;
}
