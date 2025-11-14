package com.ruoyi.flowable.convert.category;

import com.ruoyi.common.utils.SnowFlake;
import com.ruoyi.flowable.api.category.dto.FlwCategoryDTO;
import com.ruoyi.flowable.api.category.request.FlwCategorySaveReq;
import com.ruoyi.flowable.domain.category.FlwCategoryDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @description: 流程分类转换类
 * @author: wjn
 * @create: 2025-11-15 00:54
 * @version:v1.0
 */
@Mapper
public interface FlwCategoryConvert {

    FlwCategoryConvert INSTANCE = Mappers.getMapper(FlwCategoryConvert.class);

    default FlwCategoryDO toDO(FlwCategorySaveReq categoryCreateReq) {
        FlwCategoryDO entity = new FlwCategoryDO();
        entity.setId(categoryCreateReq.getId() == null ? SnowFlake.getId() : categoryCreateReq.getId());
        entity.setCode(categoryCreateReq.getCode());
        entity.setName(categoryCreateReq.getName());
        entity.setSort(categoryCreateReq.getSort());
        entity.setStatus(categoryCreateReq.getStatus());
        entity.setRemark(categoryCreateReq.getRemark());
        return entity;
    }

    FlwCategoryDTO toDTO(FlwCategoryDO flwCategoryDO);
}
