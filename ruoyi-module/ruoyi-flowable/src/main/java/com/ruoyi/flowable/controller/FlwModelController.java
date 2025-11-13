package com.ruoyi.flowable.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.page.TableDataInfoBuilder;
import com.ruoyi.flowable.api.common.PageResult;
import com.ruoyi.flowable.api.model.FlowableModelApi;
import com.ruoyi.flowable.api.model.dto.FlwModelDTO;
import com.ruoyi.flowable.api.model.request.FlwModelCreateReq;
import com.ruoyi.flowable.api.model.request.FlwModelQueryReq;
import com.ruoyi.flowable.domain.model.vo.FlwModelQueryReqVO;
import com.ruoyi.flowable.domain.model.vo.FlwModelSaveReqVO;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @description: 流程模型
 * @author: wjn
 * @create: 2025-11-10 23:59
 * @version:v1.0
 */
@Slf4j
@RestController
@RequestMapping("/flowable/model")
public class FlwModelController extends BaseController {

    @Resource
    private FlowableModelApi flowableModelApi;

    /**
     * 功能描述: 查询流程模型列表
     * @author wjn
     * @create 2025/11/12 21:25
     * @param: [reqVO]
     * @return com.ruoyi.common.core.domain.AjaxResult
     */
    @GetMapping("/queryModelList")
    public TableDataInfo queryModelList(FlwModelQueryReqVO reqVO) {
        FlwModelQueryReq req = new FlwModelQueryReq();
        req.setCategory(reqVO.getCategory());
        req.setName(reqVO.getName());
        req.setKey(reqVO.getKey());
        req.setPageNum(reqVO.getPageNum());
        req.setPageSize(reqVO.getPageSize());
        PageResult<FlwModelDTO> page = flowableModelApi.list(req);
        return TableDataInfoBuilder.build(page.getList(), page.getTotal());
    }

    /**
     * 功能描述: 创建流程模型
     * @author wjn
     * @create 2025/11/11 0:35
     * @param: [reqVO]
     * @return com.ruoyi.common.core.domain.AjaxResult
     */
    @PostMapping("/create")
    public AjaxResult createModel(@RequestBody FlwModelSaveReqVO reqVO) {
        FlwModelCreateReq req = new FlwModelCreateReq();
        req.setName(reqVO.getName());
        req.setKey(reqVO.getKey());
        return AjaxResult.success(flowableModelApi.create(req));
    }

}
