package com.ruoyi.flowable.api.impl;

import com.mybatisflex.core.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.flowable.api.common.PageResult;
import com.ruoyi.flowable.api.category.FlowableCategoryApi;
import com.ruoyi.flowable.api.category.dto.FlwCategoryDTO;
import com.ruoyi.flowable.api.category.request.FlwCategoryCreateReq;
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
        if (req.getPageNum() != null && req.getPageSize() != null && req.getPageNum() > 0 && req.getPageSize() > 0) {
            PageHelper.startPage(req.getPageNum(), req.getPageSize());
        }
        QueryWrapper qw = new QueryWrapper();
        qw.from("flw_category");
        if (req.getCode() != null && !req.getCode().isEmpty()) {
            qw.eq("code", req.getCode());
        }
        if (req.getName() != null && !req.getName().isEmpty()) {
            qw.like("name", req.getName());
        }
        List<FlwCategoryDO> list = flwCategoryMapper.selectListByQuery(qw);
        PageInfo<FlwCategoryDO> pageInfo = new PageInfo<>(list);
        List<FlwCategoryDTO> dtoList = pageInfo.getList().stream().map(this::toDto).collect(Collectors.toList());
        return new PageResult<>(dtoList, pageInfo.getTotal());
    }

    @Override
    public Long create(FlwCategoryCreateReq req) {
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
        flwCategoryMapper.update(entity);
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
