package com.ruoyi.business.domain.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CustomerDashboardDTO {
    private Long userId;

    private String firstName;

    private String lastName;

    private String photo;

    private BigDecimal balance;

    private List<PetDTO> pets;
}
