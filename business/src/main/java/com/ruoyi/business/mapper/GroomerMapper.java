package com.ruoyi.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.business.domain.Groomer;
import com.ruoyi.business.domain.dto.GroomerDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface GroomerMapper extends BaseMapper<Groomer> {

    IPage<GroomerDTO> page(Page<GroomerDTO> page,
                           @Param("address") String address,
                           @Param("serviceType") String serviceType,
                           @Param("startDate") Date startDate,
                           @Param("endDate") Date endDate);

    GroomerDTO getGroomer(@Param("userId") Long userId);
}
