package com.ruoyi.business.domain.dto;

import com.ruoyi.business.domain.AvailableDate;
import com.ruoyi.business.domain.Groomer;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class GroomerCustomerViewDTO extends Groomer {
    private String photo;

    private List<AvailableDate> availableDates;

    private List<Review> reviews;

    private double star;

    @Data
    public static class Review {
        private Long consumerUserId;

        private String consumerName;

        private String photo;

        private Integer reviewStars;

        private String reviewContent;
    }
}
