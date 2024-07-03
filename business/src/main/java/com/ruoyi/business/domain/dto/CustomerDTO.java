package com.ruoyi.business.domain.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class CustomerDTO {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Long userId;

    private String addressLine1;

    private String addressLine2;

    private String city;

    private String province;

    private String postCode;

    private String country;

    private String phone;

    private String emeContactName;

    private String emeContactPhone;

    private String photo;
}
