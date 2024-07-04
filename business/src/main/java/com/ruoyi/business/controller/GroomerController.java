package com.ruoyi.business.controller;

import com.ruoyi.business.domain.AvailableDate;
import com.ruoyi.business.domain.dto.GroomerCustomerViewDTO;
import com.ruoyi.business.domain.dto.GroomerDTO;
import com.ruoyi.business.domain.dto.GroomerDashboardDTO;
import com.ruoyi.business.service.GroomerService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groomer")
public class GroomerController extends BaseController {

    @Autowired
    private GroomerService groomerService;

    @GetMapping("/getProfile")
    public R<GroomerDTO> getProfile(Long userId) {
        return R.ok(groomerService.getProfile(userId));
    }

    @PostMapping("/saveProfile")
    public R<Boolean> saveProfile(@RequestBody GroomerDTO groomerDTO) {
        return R.ok(groomerService.saveProfile(groomerDTO));
    }

    @PostMapping("/register")
    public R<Boolean> register(@RequestBody GroomerDTO groomerDTO) {
        return R.ok(groomerService.register(groomerDTO));
    }

    @GetMapping("/dashboard")
    public R<GroomerDashboardDTO> dashboard(Long userId) {
        return R.ok(groomerService.dashboard(userId));
    }

    @GetMapping("/getAvailableDate")
    public R<List<AvailableDate>> getAvailableDate(Long userId) {
        return R.ok(groomerService.getAvailableDate(userId));
    }

    @PostMapping("/setAvailableDate")
    public R<Boolean> setAvailableDate(@RequestBody List<AvailableDate> availableDates) {
        return R.ok(groomerService.setAvailableDate(availableDates));
    }

    @GetMapping("/customerView")
    public R<GroomerCustomerViewDTO> customerView(Long userId) {
        return R.ok(groomerService.customerView(userId));
    }

}

