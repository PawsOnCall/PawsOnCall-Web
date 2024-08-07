package com.ruoyi.business.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.business.domain.*;
import com.ruoyi.business.domain.dto.CustomerDTO;
import com.ruoyi.business.domain.dto.CustomerDashboardDTO;
import com.ruoyi.business.domain.dto.PetDTO;
import com.ruoyi.business.mapper.*;
import com.ruoyi.common.utils.bean.BeanUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private PhotoMapper photoMapper;

    @Autowired
    private PetMapper petMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private PaymentMapper paymentMapper;

    public CustomerDTO getProfile(Long userId) {
        Customer customer = customerMapper.selectOne(new LambdaQueryWrapper<Customer>().eq(Customer::getUserId, userId));
        if (customer != null) {
            CustomerDTO retVal = new CustomerDTO();
            BeanUtils.copyBeanProp(retVal, customer);

            Photo photo = photoMapper.selectOne(new LambdaQueryWrapper<Photo>()
                    .eq(Photo::getTargetId, customer.getId())
                    .eq(Photo::getType, "customer"));
            if (photo != null) {
                retVal.setPhoto(photo.getBase64());
            }
            return retVal;
        } else {
            return new CustomerDTO();
        }
    }

    @Transactional
    public Boolean saveProfile(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        BeanUtils.copyBeanProp(customer, customerDTO);
        CustomerDTO customerDTOInDb = getProfile(customerDTO.getUserId());
        if (customerDTOInDb.getUserId() == null) {
            UserInfo userInfo = userInfoMapper.selectOne(new LambdaQueryWrapper<UserInfo>().eq(UserInfo::getUserId, customerDTO.getUserId()));
            BeanUtils.copyBeanProp(customer, userInfo);
            customer.setId(null);
            customerMapper.insert(customer);
        } else {
            customer.setId(customerDTOInDb.getId());
            customerMapper.updateById(customer);
        }

        Photo photoInDb = photoMapper.selectOne(new LambdaQueryWrapper<Photo>()
                .eq(Photo::getTargetId, customer.getId())
                .eq(Photo::getType, "customer"));
        Photo photo = new Photo();
        if (photoInDb == null) {
            photo.setType("customer");
            photo.setTargetId(customer.getId());
            photo.setBase64(customerDTO.getPhoto());
            photo.setId(null);
            photoMapper.insert(photo);
        } else {
            BeanUtils.copyBeanProp(photo, photoInDb);
            photo.setBase64(customerDTO.getPhoto());
            photo.setId(photoInDb.getId());
            photoMapper.updateById(photo);
        }

        return true;
    }


    public PetDTO getPet(Integer id) {
        Pet pet = petMapper.selectById(id);
        if (pet != null) {
            PetDTO retVal = new PetDTO();
            BeanUtils.copyBeanProp(retVal, pet);

            Photo photo = photoMapper.selectOne(new LambdaQueryWrapper<Photo>()
                    .eq(Photo::getTargetId, pet.getId())
                    .eq(Photo::getType, "pet"));
            if (photo != null) {
                retVal.setPhoto(photo.getBase64());
            }
            return retVal;
        } else {
            return new PetDTO();
        }
    }

    public Boolean savePet(PetDTO petDTO) {
        Pet pet = new Pet();
        BeanUtils.copyBeanProp(pet, petDTO);
        PetDTO petDTOInDb = getPet(petDTO.getId());
        if (petDTOInDb.getUserId() == null) {
            pet.setId(null);
            petMapper.insert(pet);
        } else {
            pet.setId(petDTOInDb.getId());
            petMapper.updateById(pet);
        }

        Photo photoInDb = photoMapper.selectOne(new LambdaQueryWrapper<Photo>()
                .eq(Photo::getTargetId, pet.getId())
                .eq(Photo::getType, "pet"));
        Photo photo = new Photo();
        if (photoInDb == null) {
            photo.setType("pet");
            photo.setTargetId(pet.getId());
            photo.setBase64(petDTO.getPhoto());
            photo.setId(null);
            photoMapper.insert(photo);
        } else {
            BeanUtils.copyBeanProp(photo, photoInDb);
            photo.setBase64(petDTO.getPhoto());
            photo.setId(photoInDb.getId());
            photoMapper.updateById(photo);
        }

        return true;
    }

    public CustomerDashboardDTO dashboard(Long userId) {
        UserInfo userInfo = userInfoMapper.selectOne(new LambdaQueryWrapper<UserInfo>().eq(UserInfo::getUserId, userId));
        if (userInfo != null) {
            CustomerDashboardDTO retVal = new CustomerDashboardDTO();
            BeanUtils.copyBeanProp(retVal, userInfo);

            CustomerDTO profile = getProfile(userId);
            retVal.setPhoto(profile.getPhoto());

            Payment payment = paymentMapper.selectOne(new LambdaQueryWrapper<Payment>()
                    .eq(Payment::getUserId, userId));
            if (payment == null) {
                retVal.setBalance(new BigDecimal(0));
            } else {
            retVal.setBalance(payment.getBalance());
            }

            List<Pet> pets = petMapper.selectList(new LambdaQueryWrapper<Pet>()
                    .eq(Pet::getUserId, userId));
            if (CollectionUtils.isEmpty(pets)) {
                pets = new ArrayList<>();
            }
            retVal.setPets(pets.stream().map(pet -> getPet(pet.getId())).collect(Collectors.toList()));

            return retVal;
        } else {
            return new CustomerDashboardDTO();
        }
    }
}
