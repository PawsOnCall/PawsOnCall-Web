package com.ruoyi.business.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ServiceInfo {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Long userId;

    private String type;

    private BigDecimal fee;

    private String canceledPolicy;
}
