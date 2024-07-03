package com.ruoyi.business.controller;

import com.ruoyi.business.domain.dto.CustomerDTO;
import com.ruoyi.business.domain.dto.PetDTO;
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
    public R<CustomerDTO> getProfile(Long userId) {
        return R.ok(customerService.getProfile(userId));
    }

    @PostMapping("/saveProfile")
    public R<Boolean> saveProfile(@RequestBody CustomerDTO customerDTO) {
        return R.ok(customerService.saveProfile(customerDTO));
    }

    @GetMapping("/getPet")
    public R<PetDTO> getPet(Integer id) {
        return R.ok(customerService.getPet(id));
    }

    @PostMapping("/savePet")
    public R<Boolean> savePet(@RequestBody PetDTO petDTO) {
        return R.ok(customerService.savePet(petDTO));
    }
}

