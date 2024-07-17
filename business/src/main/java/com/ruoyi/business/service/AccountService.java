package com.ruoyi.business.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.ruoyi.business.domain.UserInfo;
import com.ruoyi.business.domain.dto.UserInfoDTO;
import com.ruoyi.business.mapper.UserInfoMapper;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.domain.model.RegisterBody;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.framework.web.service.SysPermissionService;
import com.ruoyi.framework.web.service.SysRegisterService;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.system.mapper.SysUserMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AccountService {
    @Autowired
    private SysRegisterService registerService;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private SysPermissionService permissionService;

    public UserInfo register(UserInfo userInfo) {
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
        userInfo.setPassword(SecurityUtils.encryptPassword(userInfo.getPassword()));
        userInfoMapper.insert(userInfo);
        return userInfo;
    }

    public UserInfoDTO getUserInfo(String email) {
        UserInfo userInfo = userInfoMapper.selectOne(new LambdaQueryWrapper<UserInfo>().eq(UserInfo::getEmail, email));
        UserInfoDTO retVal = new UserInfoDTO();
        BeanUtils.copyBeanProp(retVal, userInfo);
        return retVal;
    }

    /**
     * {
     * "aud": "752262675079-50infhc9612qnojv0kjjhpcjs05oq0k2.apps.googleusercontent.com",
     * "azp": "752262675079-50infhc9612qnojv0kjjhpcjs05oq0k2.apps.googleusercontent.com",
     * "email": "weiqian@zgxjt.net",
     * "email_verified": true,
     * "exp": 1720536280,
     * "iat": 1720532680,
     * "iss": "https://accounts.google.com",
     * "jti": "6af3238e96bf01255d8ec8e2216fe47ef2225dc6",
     * "nbf": 1720532380,
     * "sub": "108480620957401201798",
     * "name": "qian wei",
     * "picture": "https://lh3.googleusercontent.com/a/ACg8ocLVaCErVWeW4Mv8i34GfD38y26Iscc9IJM3f5rsDuU_-7VlJA=s96-c",
     * "given_name": "qian",
     * "family_name": "wei"
     * }
     */
    public UserInfoDTO googleLogin(String credential) {
        try {
            JsonFactory jsonFactory = GsonFactory.getDefaultInstance();
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(GoogleNetHttpTransport.newTrustedTransport(), jsonFactory)
                    .setAudience(Collections.singletonList("752262675079-50infhc9612qnojv0kjjhpcjs05oq0k2.apps.googleusercontent.com"))
                    .build();
            GoogleIdToken idToken = verifier.verify(credential);
            if (idToken != null) {
                GoogleIdToken.Payload payload = idToken.getPayload();
                String email = payload.getEmail();
                String firstName = (String) payload.get("given_name");
                String lastName = (String) payload.get("family_name");

                UserInfo userInfo = userInfoMapper.selectOne(new LambdaQueryWrapper<UserInfo>().eq(UserInfo::getEmail, email));
                if (userInfo == null) {
                    userInfo = new UserInfo();
                    userInfo.setUserType("customer");
                    userInfo.setFirstName(firstName);
                    userInfo.setLastName(lastName);
                    userInfo.setEmail(email);
                    userInfo.setPassword(RandomStringUtils.random(8, true, true));
                    userInfo = register(userInfo);
                }

                SysUser user = sysUserMapper.selectUserByUserName(email);
                LoginUser loginUser = new LoginUser(user.getUserId(), user.getDeptId(), user, permissionService.getMenuPermission(user));
                String token = tokenService.createToken(loginUser);

                UserInfoDTO userInfoDTO = new UserInfoDTO();
                BeanUtils.copyBeanProp(userInfoDTO, userInfo);
                userInfoDTO.setToken(token);
                return userInfoDTO;
            } else {
                throw new RuntimeException("GoogleIdTokenVerifier verify failed");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
