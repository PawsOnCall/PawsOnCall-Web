package com.ruoyi.business.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.business.domain.UserInfo;
import com.ruoyi.business.domain.dto.UserInfoDTO;
import com.ruoyi.business.mapper.UserInfoMapper;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.RegisterBody;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.framework.web.service.SysRegisterService;
import com.ruoyi.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private SysRegisterService registerService;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    public Boolean register(UserInfo userInfo) {
        UserInfo user = userInfoMapper.selectOne(new LambdaQueryWrapper<UserInfo>().eq(UserInfo::getEmail, userInfo.getEmail()));
        if (user != null) {
            throw new RuntimeException("email already existed");
        }

        RegisterBody registerBody = new RegisterBody();
        registerBody.setUsername(userInfo.getEmail());
        registerBody.setPassword(userInfo.getPassword());
        registerService.register(registerBody);

        SysUser sysUser = sysUserMapper.selectUserByUserName(userInfo.getEmail());

        userInfo.setUserId(sysUser.getUserId());
        userInfoMapper.insert(userInfo);
        return true;
    }

    public UserInfoDTO getUserInfo(String email) {
        UserInfo userInfo = userInfoMapper.selectOne(new LambdaQueryWrapper<UserInfo>().eq(UserInfo::getEmail, email));
        UserInfoDTO retVal = new UserInfoDTO();
        BeanUtils.copyBeanProp(retVal, userInfo);
        return retVal;
    }
}
