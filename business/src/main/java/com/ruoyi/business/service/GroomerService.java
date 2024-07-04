package com.ruoyi.business.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.business.domain.*;
import com.ruoyi.business.domain.dto.GroomerDTO;
import com.ruoyi.business.domain.dto.GroomerDashboardDTO;
import com.ruoyi.business.mapper.*;
import com.ruoyi.common.utils.bean.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GroomerService {
    @Autowired
    private GroomerMapper groomerMapper;

    @Autowired
    private PhotoMapper photoMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private PaymentMapper paymentMapper;

    @Autowired
    private OrderMapper orderMapper;

    public GroomerDTO getProfile(Long userId) {
        Groomer groomer = groomerMapper.selectOne(new LambdaQueryWrapper<Groomer>().eq(Groomer::getUserId, userId));
        if (groomer != null) {
            GroomerDTO retVal = new GroomerDTO();
            BeanUtils.copyBeanProp(retVal, groomer);

            Photo photo = photoMapper.selectOne(new LambdaQueryWrapper<Photo>()
                    .eq(Photo::getTargetId, groomer.getId())
                    .eq(Photo::getType, "groomer"));
            if (photo != null) {
                retVal.setPhoto(photo.getBase64());
            }
            return retVal;
        } else {
            return null;
        }
    }

    @Transactional
    public Boolean saveProfile(GroomerDTO groomerDTO) {
        Groomer groomer = new Groomer();
        BeanUtils.copyBeanProp(groomer, groomerDTO);
        GroomerDTO groomerDTOInDb = getProfile(groomerDTO.getUserId());
        if (groomerDTOInDb == null) {
            groomer.setId(null);
            groomerMapper.insert(groomer);
        } else {
            groomer.setId(groomerDTOInDb.getId());
            groomerMapper.updateById(groomer);
        }

        Photo photoInDb = photoMapper.selectOne(new LambdaQueryWrapper<Photo>()
                .eq(Photo::getTargetId, groomer.getId())
                .eq(Photo::getType, "groomer"));
        Photo photo = new Photo();
        if (photoInDb == null) {
            photo.setType("groomer");
            photo.setTargetId(groomer.getId());
            photo.setBase64(groomerDTO.getPhoto());
            photo.setId(null);
            photoMapper.insert(photo);
        } else {
            BeanUtils.copyBeanProp(photo, photoInDb);
            photo.setBase64(groomerDTO.getPhoto());
            photo.setId(photoInDb.getId());
            photoMapper.updateById(photo);
        }

        return true;
    }

    public GroomerDashboardDTO dashboard(Long userId) {
        UserInfo userInfo = userInfoMapper.selectOne(new LambdaQueryWrapper<UserInfo>().eq(UserInfo::getUserId, userId));
        if (userInfo != null) {
            GroomerDashboardDTO retVal = new GroomerDashboardDTO();
            BeanUtils.copyBeanProp(retVal, userInfo);

            GroomerDTO profile = getProfile(userId);
            retVal.setPhoto(profile.getPhoto());

            Payment payment = paymentMapper.selectOne(new LambdaQueryWrapper<Payment>()
                    .eq(Payment::getUserId, userId));
            if (payment != null) {
                retVal.setBalance(payment.getBalance());
            }

            retVal.setNotifications(orderMapper.selectList(new LambdaQueryWrapper<OrderInfo>()
                    .eq(OrderInfo::getProviderUserId, userId)
                    .eq(OrderInfo::getStatus, "PENDING")));

            return retVal;
        } else {
            return null;
        }
    }

    @Transactional
    public Boolean register(GroomerDTO groomerDTO) {
        saveProfile(groomerDTO);
        UserInfo userInfo = userInfoMapper.selectOne(new LambdaQueryWrapper<UserInfo>()
                .eq(UserInfo::getUserId, groomerDTO.getUserId()));
        if (userInfo != null) {
            userInfo.setUserType("groomer");
            userInfoMapper.updateById(userInfo);
            return true;
        } else {
            return false;
        }
    }
}
