package com.ruoyi.flowable.api.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.flowable.api.category.request.FlwCategorySaveReq;
import com.ruoyi.flowable.api.common.PageResult;
import com.ruoyi.flowable.api.category.FlowableCategoryApi;
import com.ruoyi.flowable.api.category.dto.FlwCategoryDTO;
import com.ruoyi.flowable.api.category.request.FlwCategorySaveReq;
import com.ruoyi.flowable.api.category.request.FlwCategoryQueryReq;
import com.ruoyi.flowable.api.category.request.FlwCategoryUpdateReq;
import com.ruoyi.flowable.domain.category.FlwCategoryDO;
import com.ruoyi.flowable.mapper.FlwCategoryMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlowableCategoryApiImpl implements FlowableCategoryApi {

    @Resource
    private FlwCategoryMapper flwCategoryMapper;

    @Override
    public PageResult<FlwCategoryDTO> list(FlwCategoryQueryReq req) {
        LambdaQueryWrapper<FlwCategoryDO> qw = new LambdaQueryWrapper<>();
        if (req.getCode() != null && !req.getCode().isEmpty()) {
            qw.eq(FlwCategoryDO::getCode, req.getCode());
        }
        if (req.getName() != null && !req.getName().isEmpty()) {
            qw.like(FlwCategoryDO::getName, req.getName());
        }
        long pageNum = req.getPageNum() != null && req.getPageNum() > 0 ? req.getPageNum() : 1L;
        long pageSize = req.getPageSize() != null && req.getPageSize() > 0 ? req.getPageSize() : 10L;
        Page<FlwCategoryDO> page = new Page<>(pageNum, pageSize);
        Page<FlwCategoryDO> result = (Page<FlwCategoryDO>) flwCategoryMapper.selectPage(page, qw);
        List<FlwCategoryDTO> dtoList = result.getRecords().stream().map(this::toDto).collect(Collectors.toList());
        return new PageResult<>(dtoList, result.getTotal());
    }

    @Override
    public Long create(FlwCategorySaveReq req) {
        FlwCategoryDO entity = new FlwCategoryDO();
        entity.setCode(req.getCode());
        entity.setName(req.getName());
        entity.setSort(req.getSort());
        entity.setStatus(req.getStatus());
        flwCategoryMapper.insert(entity);
        return entity.getId();
    }

    @Override
    public void update(FlwCategoryUpdateReq req) {
        FlwCategoryDO entity = new FlwCategoryDO();
        entity.setId(req.getId());
        entity.setCode(req.getCode());
        entity.setName(req.getName());
        entity.setSort(req.getSort());
        entity.setStatus(req.getStatus());
        flwCategoryMapper.updateById(entity);
    }

    @Override
    public void delete(Long id) {
        flwCategoryMapper.deleteById(id);
    }

    private FlwCategoryDTO toDto(FlwCategoryDO entity) {
        FlwCategoryDTO dto = new FlwCategoryDTO();
        dto.setId(entity.getId());
        dto.setCode(entity.getCode());
        dto.setName(entity.getName());
        dto.setSort(entity.getSort());
        dto.setStatus(entity.getStatus());
        return dto;
    }
}
