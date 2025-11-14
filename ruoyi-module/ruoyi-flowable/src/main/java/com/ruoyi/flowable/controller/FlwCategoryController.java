package com.ruoyi.flowable.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.page.TableDataInfoBuilder;
import com.ruoyi.flowable.api.category.FlowableCategoryApi;
import com.ruoyi.flowable.api.category.dto.FlwCategoryDTO;
import com.ruoyi.flowable.api.category.request.FlwCategorySaveReq;
import com.ruoyi.flowable.api.category.request.FlwCategoryQueryReq;
import com.ruoyi.flowable.service.IFlwCategoryService;
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
    private IFlwCategoryService flwCategoryService;

    /**
     * 功能描述: 查询流程分类列表
     * @author wjn
     * @create 2025/11/15 0:44
     * @param: [queryReq]
     * @return com.ruoyi.common.core.page.TableDataInfo
     */
    @GetMapping("/queryCategoryList")
    public TableDataInfo list(FlwCategoryQueryReq queryReq) {
        startPage();
        return getDataTable(flwCategoryService.queryCategoryList(queryReq));
    }

    /**
     * 功能描述: 查询流程分类详情
     * @author wjn
     * @create 2025/11/15 1:11
     * @param:
     * @return
     */
    @GetMapping("/detail/{id}")
    public AjaxResult queryCategoryDetail(@PathVariable("id") Long id) {
        return AjaxResult.success(flwCategoryService.queryCategoryDetail(id));
    }

    /**
     * 功能描述: 创建流程分类
     * @author wjn
     * @create 2025/11/15 0:50
     * @param: [req]
     * @return com.ruoyi.common.core.domain.AjaxResult
     */
    @PostMapping("/create")
    public AjaxResult create(@RequestBody FlwCategorySaveReq req) {
        return AjaxResult.success(flwCategoryService.create(req));
    }

    /**
     * 功能描述:
     * @author wjn
     * @create 2025/11/15 1:09
     * @param: [req]
     * @return com.ruoyi.common.core.domain.AjaxResult
     */
    @PostMapping("/update")
    public AjaxResult update(@RequestBody FlwCategorySaveReq req) {
        flwCategoryService.update(req);
        return AjaxResult.success("修改成功");
    }
}
