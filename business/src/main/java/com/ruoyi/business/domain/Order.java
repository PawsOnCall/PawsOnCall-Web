package com.ruoyi.business.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Order {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String status;

    private Long providerUserId;

    private Long consumerUserId;

    private BigDecimal groomerFee;

    private BigDecimal serviceFee;

    private Integer reviewStars;

    private String reviewContent;

    private Date serviceTime;

    private Date createTime;
}
