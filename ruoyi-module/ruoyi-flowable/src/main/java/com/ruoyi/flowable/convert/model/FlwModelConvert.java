package com.ruoyi.flowable.convert.model;

import com.ruoyi.flowable.domain.model.vo.FlwModelRespVO;
import liquibase.pro.packaged.M;
import org.flowable.engine.repository.Model;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @description: 流程模型转换类
 * @author: wjn
 * @create: 2025-11-12 21:33
 * @version:v1.0
 */
@Mapper
public interface FlwModelConvert {

    FlwModelConvert INSTANCE = Mappers.getMapper(FlwModelConvert.class);

    /**
     * 功能描述: 模型转换
     * @author wjn
     * @create 2025/11/12 21:33
     * @param: [model]
     * @return com.ruoyi.flowable.domain.model.vo.FlwModelRespVO
     */
    default FlwModelRespVO toModelResList(Model model) {
        FlwModelRespVO vo = new FlwModelRespVO();
        vo.setId(model.getId());
        vo.setName(model.getName());
        vo.setKey(model.getKey());
        vo.setCategory(model.getCategory());
        vo.setCreateTime(model.getCreateTime());
        vo.setLastUpdateTime(model.getLastUpdateTime());
        vo.setVersion(model.getVersion());
        return vo;
    }
}
