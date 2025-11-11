package com.ruoyi.flowable.service.impl;

import com.ruoyi.flowable.domain.vo.FlwModelSaveReqVO;
import com.ruoyi.flowable.service.IFlwModelService;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.Model;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
