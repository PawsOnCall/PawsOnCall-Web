package com.ruoyi.business.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Photo {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer targetId;

    private String type;

    private String base64;
}
