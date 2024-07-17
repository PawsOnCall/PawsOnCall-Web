package com.ruoyi.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.business.domain.Groomer;
import com.ruoyi.business.domain.dto.GroomerDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface GroomerMapper extends BaseMapper<Groomer> {
    @Select("select g.*, p.base64 as photo from groomer g left join photo p ON g.id = p.target_id where p.type = 'groomer'")
    IPage<GroomerDTO> page(Page<GroomerDTO> page);

    @Select("select g.*, p.base64 as photo from groomer g left join photo p ON g.id = p.target_id where p.type = 'groomer' AND g.user_id = #{userId}")
    GroomerDTO getGroomer(@Param("userId") Long userId);
}
