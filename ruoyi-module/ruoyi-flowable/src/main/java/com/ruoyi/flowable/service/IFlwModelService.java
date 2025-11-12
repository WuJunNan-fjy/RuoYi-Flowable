package com.ruoyi.flowable.service;

import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.flowable.domain.model.vo.FlwModelQueryReqVO;
import com.ruoyi.flowable.domain.model.vo.FlwModelRespVO;
import com.ruoyi.flowable.domain.model.vo.FlwModelSaveReqVO;

import java.util.List;

/**
 * @description: 流程模型 Service 接口
 * @author: wjn
 * @create: 2025-11-11 00:18
 * @version:v1.0
 */
public interface IFlwModelService {

    /**
     * 功能描述: 查询流程模型列表
     * @author wjn
     * @create 2025/11/12 21:28
     * @param: [reqVO]
     * @return java.lang.String
     */
    TableDataInfo queryModelList(FlwModelQueryReqVO reqVO);

    /**
     * 功能描述: 创建流程模型
     * @author wjn
     * @create 2025/11/11 0:21
     * @param: [reqVO]
     * @return java.lang.String
     */
    String createModel(FlwModelSaveReqVO reqVO);
}
