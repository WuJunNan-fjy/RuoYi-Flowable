package com.ruoyi.aigen.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.aigen.domain.AiGenTask;
import com.ruoyi.aigen.service.IAiGenTaskService;

/**
 * AI代码生成控制器
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/tool/aigen/task")
public class AiGenTaskController extends BaseController {
    @Autowired
    private IAiGenTaskService aiGenTaskService;
    
    /**
     * 查询AI生成任务列表
     */
    @PreAuthorize("@ss.hasPermi('tool:aigen:list')")
    @GetMapping("/list")
    public TableDataInfo list(AiGenTask aiGenTask) {
        startPage();
        List<AiGenTask> list = aiGenTaskService.selectAiGenTaskList(aiGenTask);
        return getDataTable(list);
    }
    
    /**
     * 获取AI生成任务详细信息
     */
    @PreAuthorize("@ss.hasPermi('tool:aigen:query')")
    @GetMapping(value = "/{taskId}")
    public AjaxResult getInfo(@PathVariable("taskId") Long taskId) {
        return success(aiGenTaskService.selectAiGenTaskById(taskId));
    }
    
    /**
     * 新增AI生成任务
     */
    @PreAuthorize("@ss.hasPermi('tool:aigen:add')")
    @Log(title = "AI代码生成", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AiGenTask aiGenTask) {
        return toAjax(aiGenTaskService.insertAiGenTask(aiGenTask));
    }
    
    /**
     * 修改AI生成任务
     */
    @PreAuthorize("@ss.hasPermi('tool:aigen:edit')")
    @Log(title = "AI代码生成", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AiGenTask aiGenTask) {
        return toAjax(aiGenTaskService.updateAiGenTask(aiGenTask));
    }

    /**
     * 删除AI生成任务
     */
    @PreAuthorize("@ss.hasPermi('tool:aigen:remove')")
    @Log(title = "AI代码生成", businessType = BusinessType.DELETE)
    @DeleteMapping("/{taskIds}")
    public AjaxResult remove(@PathVariable Long[] taskIds) {
        return toAjax(aiGenTaskService.deleteAiGenTaskByIds(taskIds));
    }
    
    /**
     * 生成代码
     */
    @PreAuthorize("@ss.hasPermi('tool:aigen:code')")
    @Log(title = "AI代码生成", businessType = BusinessType.GENCODE)
    @GetMapping("/code/{taskId}")
    public AjaxResult genCode(@PathVariable("taskId") Long taskId) {
        aiGenTaskService.genCode(taskId);
        return success();
    }
    
    /**
     * 预览代码
     */
    @PreAuthorize("@ss.hasPermi('tool:aigen:preview')")
    @GetMapping("/preview/{taskId}")
    public AjaxResult previewCode(@PathVariable("taskId") Long taskId) {
        Map<String, String> dataMap = aiGenTaskService.previewCode(taskId);
        return success(dataMap);
    }
}