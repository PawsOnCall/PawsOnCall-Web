package com.ruoyi.business.domain.dto;

import com.ruoyi.business.domain.AvailableDate;
import com.ruoyi.business.domain.Groomer;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class GroomerListDTO extends Groomer {
    List<AvailableDate> availableDates;

    private Double totalStars;

    private Long totalComments;
}
