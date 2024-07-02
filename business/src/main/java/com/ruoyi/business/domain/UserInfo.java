package com.ruoyi.business.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class UserInfo {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Long userId;

    private String userType;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String phone;

    private String city;

    private String postCode;

}
