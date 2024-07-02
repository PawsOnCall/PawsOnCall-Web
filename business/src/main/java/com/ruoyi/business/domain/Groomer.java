package com.ruoyi.business.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

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
}
