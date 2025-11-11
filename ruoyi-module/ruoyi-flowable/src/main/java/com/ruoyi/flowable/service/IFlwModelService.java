package com.ruoyi.flowable.service;

import com.ruoyi.flowable.domain.vo.FlwModelSaveReqVO;

/**
 * @description: 流程模型 Service 接口
 * @author: wjn
 * @create: 2025-11-11 00:18
 * @version:v1.0
 */
public interface IFlwModelService {

    /**
     * 功能描述: 创建流程模型
     * @author wjn
     * @create 2025/11/11 0:21
     * @param: [reqVO]
     * @return java.lang.String
     */
    String createModel(FlwModelSaveReqVO reqVO);
}
