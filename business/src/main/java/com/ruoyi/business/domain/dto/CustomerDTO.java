package com.ruoyi.business.domain.dto;

import com.ruoyi.business.domain.Customer;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CustomerDTO extends Customer {
    private String photo;
}
