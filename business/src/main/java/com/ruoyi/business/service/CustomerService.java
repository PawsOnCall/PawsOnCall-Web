package com.ruoyi.business.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.business.domain.Customer;
import com.ruoyi.business.domain.Photo;
import com.ruoyi.business.mapper.CustomerMapper;
import com.ruoyi.business.mapper.PhotoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private PhotoMapper photoMapper;

    public Boolean saveProfile(Customer customer) {
        Customer profile = getProfile(customer.getUserId());
        if (profile == null) {
            customerMapper.insert(customer);
        } else {
            customer.setId(profile.getId());
            customerMapper.updateById(customer);
        }
        return true;
    }

    public Customer getProfile(Long userId) {
        return customerMapper.selectOne(new LambdaQueryWrapper<Customer>().eq(Customer::getUserId, userId));
    }

    public Boolean savePhoto(Photo photo) {
        photo.setType("customer");
        Photo photoInDb = getPhoto(photo.getUserId());
        if (photoInDb == null) {
            photoMapper.insert(photo);
        } else {
            photo.setId(photoInDb.getId());
            photoMapper.updateById(photo);
        }
        return true;
    }

    public Photo getPhoto(Long userId) {
        return photoMapper.selectOne(new LambdaQueryWrapper<Photo>().eq(Photo::getUserId, userId));
    }
}
