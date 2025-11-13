package com.ruoyi.flowable.api.impl;

import com.ruoyi.flowable.api.common.PageResult;
import com.ruoyi.flowable.api.model.FlowableModelApi;
import com.ruoyi.flowable.api.model.dto.FlwModelDTO;
import com.ruoyi.flowable.api.model.request.FlwModelCreateReq;
import com.ruoyi.flowable.api.model.request.FlwModelQueryReq;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.Model;
import org.flowable.engine.repository.ModelQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlowableModelApiImpl implements FlowableModelApi {

    @Resource
    private RepositoryService repositoryService;

    @Override
    public PageResult<FlwModelDTO> list(FlwModelQueryReq req) {
        ModelQuery query = repositoryService.createModelQuery();
        if (req.getCategory() != null && !req.getCategory().isEmpty()) {
            query.modelCategory(req.getCategory());
        }
        if (req.getName() != null && !req.getName().isEmpty()) {
            query.modelNameLike(req.getName());
        }
        if (req.getKey() != null && !req.getKey().isEmpty()) {
            query.modelKey(req.getKey());
        }
        long total = query.count();
        List<Model> models;
        if (req.getPageNum() != null && req.getPageSize() != null && req.getPageNum() > 0 && req.getPageSize() > 0) {
            int offset = (req.getPageNum() - 1) * req.getPageSize();
            models = query.listPage(offset, req.getPageSize());
        } else {
            models = query.list();
        }
        List<FlwModelDTO> list = models.stream().map(this::toDto).collect(Collectors.toList());
        return new PageResult<>(list, total);
    }

    @Override
    public String create(FlwModelCreateReq req) {
        Model model = repositoryService.newModel();
        model.setName(req.getName());
        model.setKey(req.getKey());
        if (req.getCategory() != null) {
            model.setCategory(req.getCategory());
        }
        repositoryService.saveModel(model);
        return model.getId();
    }

    private FlwModelDTO toDto(Model model) {
        FlwModelDTO dto = new FlwModelDTO();
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setKey(model.getKey());
        dto.setCategory(model.getCategory());
        dto.setVersion(model.getVersion());
        dto.setCreateTime(model.getCreateTime());
        dto.setLastUpdateTime(model.getLastUpdateTime());
        return dto;
    }
}
