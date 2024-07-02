package com.ruoyi.business.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Payment {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Long userId;

    private BigDecimal balance;

    private String cardName;

    private String cardNumber;

    private String expiration;

    private String cvc;

    private String country;

    private String addressLine1;

    private String addressLine2;

    private String city;

    private String province;

    private String postCode;
}
