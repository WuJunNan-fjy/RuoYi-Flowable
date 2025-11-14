package com.ruoyi.flowable.service;

import com.ruoyi.flowable.api.category.dto.FlwCategoryDTO;
import com.ruoyi.flowable.api.category.request.FlwCategorySaveReq;
import com.ruoyi.flowable.api.category.request.FlwCategoryQueryReq;
import com.ruoyi.flowable.api.category.request.FlwCategorySaveReq;
import com.ruoyi.flowable.domain.category.FlwCategoryDO;

import java.util.List;

/**
 * @description: 流程分类 Service
 * @author: wjn
 * @create: 2025-11-15 00:42
 * @version:v1.0
 */
public interface IFlwCategoryService {

    /**
     * 功能描述: 查询流程分类列表
     * @author wjn
     * @create 2025/11/15 0:45
     * @param: [queryReq]
     * @return java.util.List<?>
     */
    List<FlwCategoryDO> queryCategoryList(FlwCategoryQueryReq queryReq);

    /**
     * 功能描述: 查询流程分类详情
     * @author wjn
     * @create 2025/11/15 1:13
     * @param: [id]
     * @return com.ruoyi.flowable.api.category.dto.FlwCategoryDTO
     */
    FlwCategoryDTO queryCategoryDetail(Long id);

    /**
     * 功能描述: 创建流程分类
     * @author wjn
     * @create 2025/11/15 0:50
     * @param: [req]
     * @return java.lang.String
     */
    Long create(FlwCategorySaveReq req);

    /**
     * 功能描述: 修改流程分类
     * @author wjn
     * @create 2025/11/15 1:06
     * @param: [req]
     * @return void
     */
    void update(FlwCategorySaveReq req);
}
