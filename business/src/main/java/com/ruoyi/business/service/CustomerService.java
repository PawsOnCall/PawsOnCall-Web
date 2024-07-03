package com.ruoyi.business.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.business.domain.Customer;
import com.ruoyi.business.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private CustomerMapper customerMapper;

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
}
