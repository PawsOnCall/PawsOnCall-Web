package com.ruoyi.business.controller;

import com.ruoyi.business.domain.Customer;
import com.ruoyi.business.domain.Photo;
import com.ruoyi.business.service.CustomerService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
public class CustomerController extends BaseController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/getProfile")
    public R<Customer> getProfile(Long userId) {
        return R.ok(customerService.getProfile(userId));
    }

    @PostMapping("/saveProfile")
    public R<Boolean> saveProfile(@RequestBody Customer customer) {
        return R.ok(customerService.saveProfile(customer));
    }

    @GetMapping("/getPhoto")
    public R<Photo> getPhoto(Long userId) {
        return R.ok(customerService.getPhoto(userId));
    }

    @PostMapping("/savePhoto")
    public R<Boolean> savePhoto(@RequestBody Photo photo) {
        return R.ok(customerService.savePhoto(photo));
    }

}

