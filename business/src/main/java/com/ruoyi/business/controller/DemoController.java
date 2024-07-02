package com.ruoyi.business.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.business.domain.User;
import com.ruoyi.business.mapper.UserDemoMapper;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/demo/user")
public class DemoController extends BaseController {
    @Autowired
    private UserDemoMapper userDemoMapper;

    @GetMapping("/list")
    public R<List<User>> userList(String name) {
        return R.ok(userDemoMapper.selectList(new LambdaQueryWrapper<User>().eq(User::getName, name)));
    }
}

