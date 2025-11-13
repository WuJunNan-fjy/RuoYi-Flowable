package com.ruoyi.flowable.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.page.TableDataInfoBuilder;
import com.ruoyi.flowable.api.category.FlowableCategoryApi;
import com.ruoyi.flowable.api.category.dto.FlwCategoryDTO;
import com.ruoyi.flowable.api.category.request.FlwCategoryCreateReq;
import com.ruoyi.flowable.api.category.request.FlwCategoryQueryReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @description: 流程分类
 * @author: wjn
 * @create: 2025-11-12 23:30
 * @version:v1.0
 */
@Slf4j
@RestController
@RequestMapping("/flowable/category")
public class FlwCategoryController extends BaseController {

    @Resource
    private FlowableCategoryApi flowableCategoryApi;

    @GetMapping("/list")
    public TableDataInfo list(@RequestParam(required = false) String code,
                              @RequestParam(required = false) String name) {
        FlwCategoryQueryReq req = new FlwCategoryQueryReq();
        req.setCode(code);
        req.setName(name);
        // req.setPageNum(getPageNum());
        // req.setPageSize(getPageSize());
        // var page = flowableCategoryApi.list(req);
        // return TableDataInfoBuilder.build(page.getList(), page.getTotal());
        return null;
    }

    @PostMapping("/create")
    public AjaxResult create(@RequestBody FlwCategoryCreateReq req) {
        return AjaxResult.success(flowableCategoryApi.create(req));
    }
}
