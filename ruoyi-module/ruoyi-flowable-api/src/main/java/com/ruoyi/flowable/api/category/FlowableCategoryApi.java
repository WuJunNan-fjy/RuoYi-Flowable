package com.ruoyi.flowable.api.category;

import com.ruoyi.flowable.api.category.request.FlwCategorySaveReq;
import com.ruoyi.flowable.api.common.PageResult;
import com.ruoyi.flowable.api.category.dto.FlwCategoryDTO;
import com.ruoyi.flowable.api.category.request.FlwCategoryQueryReq;
import com.ruoyi.flowable.api.category.request.FlwCategoryUpdateReq;

public interface FlowableCategoryApi {
    PageResult<FlwCategoryDTO> list(FlwCategoryQueryReq req);
    Long create(FlwCategorySaveReq req);
    void update(FlwCategoryUpdateReq req);
    void delete(Long id);
}
