package com.ruoyi.business.controller;

import com.ruoyi.business.domain.UserInfo;
import com.ruoyi.business.domain.dto.UserInfoDTO;
import com.ruoyi.business.service.AccountService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/account")
public class AccountController extends BaseController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/register")
    public R<Boolean> register(@RequestBody UserInfo userInfo) {
        return R.ok(accountService.register(userInfo) != null);
    }

    @GetMapping("/getUserInfo")
    public R<UserInfoDTO> getUserInfo(String email) {
        return R.ok(accountService.getUserInfo(email));
    }

    @PostMapping("/googleLogin")
    public R<UserInfoDTO> googleLogin(@RequestBody Map<String, String> payload) {
        return R.ok(accountService.googleLogin(payload.get("credential")));
    }
}

