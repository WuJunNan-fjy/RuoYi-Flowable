package com.ruoyi.flowable.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.page.TableDataInfoBuilder;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.flowable.convert.model.FlwModelConvert;
import com.ruoyi.flowable.domain.model.vo.FlwModelQueryReqVO;
import com.ruoyi.flowable.domain.model.vo.FlwModelRespVO;
import com.ruoyi.flowable.domain.model.vo.FlwModelSaveReqVO;
import com.ruoyi.flowable.service.IFlwModelService;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.Model;
import org.flowable.engine.repository.ModelQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 流程模型 Service 实现类
 * @author: wjn
 * @create: 2025-11-11 00:19
 * @version:v1.0
 */
@Slf4j
@Service
public class FlwModelServiceImpl implements IFlwModelService {

    @Resource
    private RepositoryService repositoryService;

    /**
     * 功能描述: 查询流程模型列表
     * @author wjn
     * @create 2025/11/12 21:28
     * @param: [reqVO]
     * @return java.lang.String
     */
    @Override
    public TableDataInfo queryModelList(FlwModelQueryReqVO reqVO) {
        PageHelper.startPage(reqVO.getPageNum(), reqVO.getPageSize());
        // 分页查询
        ModelQuery modelQuery = repositoryService.createModelQuery();

        if(StringUtils.isNotEmpty(reqVO.getCategory())){
            modelQuery.modelCategory(reqVO.getCategory());
        }
        if(StringUtils.isNotEmpty(reqVO.getName())){
            modelQuery.modelNameLike(reqVO.getName());
        }
        if(StringUtils.isNotEmpty(reqVO.getKey())){
            modelQuery.modelKey(reqVO.getKey());
        }
        // 分页查询
        List<Model> modelList = modelQuery.list();
        PageInfo<Model> pageInfo = new PageInfo<>(modelList);

        // // 流程模型转换
        List<FlwModelRespVO> vos = pageInfo.getList().stream()
                .map(FlwModelConvert.INSTANCE::toModelResList)
                .collect(Collectors.toList());

        return TableDataInfoBuilder.build(vos, pageInfo.getTotal());
    }

    /**
     * 功能描述: 创建流程模型
     * @author wjn
     * @create 2025/11/11 0:22
     * @param: [reqVO]
     * @return java.lang.String
     */
    @Override
    public String createModel(FlwModelSaveReqVO reqVO) {
        Model model = repositoryService.newModel();
        model.setName(reqVO.getName());
        model.setKey(reqVO.getKey());

        // 保存模型
        repositoryService.saveModel(model);
        return model.getId();
    }
}
