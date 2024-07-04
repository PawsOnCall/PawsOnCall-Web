package com.ruoyi.business.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class AvailableDate {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Long userId;

    private Date availableDate;

    private String status;
}
