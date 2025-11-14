package com.ruoyi.flowable.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.flowable.api.category.dto.FlwCategoryDTO;
import com.ruoyi.flowable.api.category.request.FlwCategorySaveReq;
import com.ruoyi.flowable.api.category.request.FlwCategoryQueryReq;
import com.ruoyi.flowable.api.category.request.FlwCategorySaveReq;
import com.ruoyi.flowable.convert.category.FlwCategoryConvert;
import com.ruoyi.flowable.domain.category.FlwCategoryDO;
import com.ruoyi.flowable.mapper.FlwCategoryMapper;
import com.ruoyi.flowable.service.IFlwCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @description: 流程分类 ServiceImpl
 * @author: wjn
 * @create: 2025-11-15 00:42
 * @version:v1.0
 */
@Service
public class FlwCategoryServiceImpl implements IFlwCategoryService {

    @Resource
    private FlwCategoryMapper flwCategoryMapper;

    /**
     * 功能描述: 查询流程分类列表
     * @author wjn
     * @create 2025/11/15 0:46
     * @param: [queryReq]
     * @return java.util.List<com.ruoyi.flowable.domain.category.FlwCategoryDO>
     */
    @Override
    public List<FlwCategoryDO> queryCategoryList(FlwCategoryQueryReq queryReq) {
        LambdaQueryWrapper<FlwCategoryDO> qw = new LambdaQueryWrapper<>();
        if (queryReq.getCode() != null && !queryReq.getCode().isEmpty()) {
            qw.eq(FlwCategoryDO::getCode, queryReq.getCode());
        }
        if (queryReq.getName() != null && !queryReq.getName().isEmpty()) {
            qw.like(FlwCategoryDO::getName, queryReq.getName());
        }
        List<FlwCategoryDO> list = flwCategoryMapper.selectList(qw);
        return list;
    }

    /**
     * 功能描述: 查询流程分类详情
     * @author wjn
     * @create 2025/11/15 1:14
     * @param: [id]
     * @return com.ruoyi.flowable.api.category.dto.FlwCategoryDTO
     */
    @Override
    public FlwCategoryDTO queryCategoryDetail(Long id) {
        FlwCategoryDO flwCategoryDO = flwCategoryMapper.selectById(id);
        return FlwCategoryConvert.INSTANCE.toDTO(flwCategoryDO);
    }

    /**
     * 功能描述: 创建流程分类
     * @author wjn
     * @create 2025/11/15 0:50
     * @param: [req]
     * @return java.lang.String
     */
    @Override
    public Long create(FlwCategorySaveReq req) {
        FlwCategoryDO flwCategoryDO = FlwCategoryConvert.INSTANCE.toDO(req);
        flwCategoryMapper.insert(flwCategoryDO);
        return flwCategoryDO.getId();
    }

    /**
     * 功能描述: 修改流程分类
     * @author wjn
     * @create 2025/11/15 1:09
     * @param: [req]
     * @return void
     */
    @Override
    public void update(FlwCategorySaveReq req) {
        FlwCategoryDO flwCategoryDO = FlwCategoryConvert.INSTANCE.toDO(req);
        flwCategoryMapper.updateById(flwCategoryDO);
    }
}
