package com.ruoyi.business.domain.dto;

import lombok.Data;

@Data
public class CustomerDTO {
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
