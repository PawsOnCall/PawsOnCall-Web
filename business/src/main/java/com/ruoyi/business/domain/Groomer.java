package com.ruoyi.business.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Groomer {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Long userId;

    private String firstName;

    private String lastName;

    private String email;

    private String level;

    private String mailingAddress;

    private String groomingExperience;

    private String phone;

    private String type;

    private Integer groomedCount;

    private String petType;

    private Boolean hasCertification;

    private String certification;

    private String availabilityType;

    private String selfIntroduction;

    private String facebook;

    private String instagram;

    private String country;

    private String street;

    private String streetNumber;

    private String locality;

    private String postCode;

    private String areaLevel1;

    private String areaLevel2;

    private String serviceType;

    private BigDecimal serviceFee;

    private String serviceCanceledPolicy;

    private String acceptedDogAge;

    private String acceptedDogSize;

    private String otherPetTypes;

    private String headline;

    private String description;
}
