package com.ruoyi.business.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Customer {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Long userId;

    private String firstName;

    private String lastName;

    private String email;

    private String addressLine1;

    private String addressLine2;

    private String city;

    private String province;

    private String postCode;

    private String country;

    private String phone;

    private String emeContactName;

    private String emeContactPhone;
}
