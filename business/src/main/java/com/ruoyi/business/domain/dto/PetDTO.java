package com.ruoyi.business.domain.dto;

import com.ruoyi.business.domain.Pet;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PetDTO extends Pet {
    private String photo;
}
