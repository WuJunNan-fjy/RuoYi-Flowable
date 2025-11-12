package com.ruoyi.flowable.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.flowable.domain.model.vo.FlwModelQueryReqVO;
import com.ruoyi.flowable.domain.model.vo.FlwModelRespVO;
import com.ruoyi.flowable.domain.model.vo.FlwModelSaveReqVO;
import com.ruoyi.flowable.service.IFlwModelService;
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
    private IFlwModelService flwModelService;

    /**
     * 功能描述: 查询流程模型列表
     * @author wjn
     * @create 2025/11/12 21:25
     * @param: [reqVO]
     * @return com.ruoyi.common.core.domain.AjaxResult
     */
    @GetMapping("/queryModelList")
    public TableDataInfo queryModelList(FlwModelQueryReqVO reqVO) {
        return flwModelService.queryModelList(reqVO);
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
        return AjaxResult.success(flwModelService.createModel(reqVO));
    }

}
