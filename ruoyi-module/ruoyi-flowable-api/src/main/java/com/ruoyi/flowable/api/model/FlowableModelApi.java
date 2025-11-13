package com.ruoyi.flowable.api.model;

import com.ruoyi.flowable.api.common.PageResult;
import com.ruoyi.flowable.api.model.dto.FlwModelDTO;
import com.ruoyi.flowable.api.model.request.FlwModelCreateReq;
import com.ruoyi.flowable.api.model.request.FlwModelQueryReq;

public interface FlowableModelApi {
    PageResult<FlwModelDTO> list(FlwModelQueryReq req);
    String create(FlwModelCreateReq req);
}
