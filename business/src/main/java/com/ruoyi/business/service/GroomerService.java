package com.ruoyi.business.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.business.domain.*;
import com.ruoyi.business.domain.dto.CustomerDTO;
import com.ruoyi.business.domain.dto.GroomerCustomerViewDTO;
import com.ruoyi.business.domain.dto.GroomerDTO;
import com.ruoyi.business.domain.dto.GroomerDashboardDTO;
import com.ruoyi.business.mapper.*;
import com.ruoyi.common.utils.bean.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private AvailableDateMapper availableDateMapper;

    @Autowired
    private CustomerService customerService;

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

            retVal.setNotifications(orderInfoMapper.selectList(new LambdaQueryWrapper<OrderInfo>()
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

    public List<AvailableDate> getAvailableDate(Long userId) {
        return availableDateMapper.selectList(new LambdaQueryWrapper<AvailableDate>()
                .eq(AvailableDate::getUserId, userId));
    }

    @Transactional
    public Boolean setAvailableDate(List<AvailableDate> availableDates) {
        availableDates.forEach(availableDate -> {
            availableDate.setStatus("AVAILABLE");
            availableDateMapper.insert(availableDate);
        });
        return true;
    }

    public GroomerCustomerViewDTO customerView(Long userId) {
        GroomerCustomerViewDTO retVal = new GroomerCustomerViewDTO();
        GroomerDTO profile = getProfile(userId);
        BeanUtils.copyBeanProp(retVal, profile);

        retVal.setAvailableDates(getAvailableDate(userId));

        List<OrderInfo> orderInfos = orderInfoMapper.selectList(new LambdaQueryWrapper<OrderInfo>()
                .eq(OrderInfo::getProviderUserId, userId));
        retVal.setReviews(orderInfos.stream().map(orderInfo -> {
            GroomerCustomerViewDTO.Review review = new GroomerCustomerViewDTO.Review();
            BeanUtils.copyBeanProp(review, orderInfo);
            CustomerDTO customerProfile = customerService.getProfile(orderInfo.getConsumerUserId());
            review.setPhoto(customerProfile.getPhoto());
            return review;
        }).collect(Collectors.toList()));

        return retVal;
    }
}
