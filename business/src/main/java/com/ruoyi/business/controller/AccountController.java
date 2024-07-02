package com.ruoyi.business.controller;

import com.ruoyi.business.domain.UserInfo;
import com.ruoyi.business.domain.dto.UserInfoDTO;
import com.ruoyi.business.service.AccountService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
public class AccountController extends BaseController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/register")
    public R<Boolean> register(@RequestBody UserInfo userInfo) {
        return R.ok(accountService.register(userInfo));
    }

    @GetMapping("/getUserInfo")
    public R<UserInfoDTO> getUserInfo(String email) {
        return R.ok(accountService.getUserInfo(email));
    }
}

