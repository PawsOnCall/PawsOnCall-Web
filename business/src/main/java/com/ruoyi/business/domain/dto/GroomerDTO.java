package com.ruoyi.business.domain.dto;

import com.ruoyi.business.domain.Groomer;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class GroomerDTO extends Groomer {
    private String photo;

    private String password;
}
