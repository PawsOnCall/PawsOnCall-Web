package com.ruoyi.business.domain.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PetDTO {
    private Integer id;

    private Long userId;

    private String type;

    private String name;

    private Float weight;

    private Integer ageYr;

    private Integer ageMo;

    private String sex;

    private String breed;

    private Boolean microChipped;

    private Boolean spayed;

    private Boolean houseTrained;

    private Boolean friendlyWithChildren;

    private Boolean friendlyWithDog;

    private Boolean friendlyWithCat;

    private Date adoptionDate;

    private String about;

    private String photo;
}
