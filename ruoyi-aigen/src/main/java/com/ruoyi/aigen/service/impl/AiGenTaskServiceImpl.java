package com.ruoyi.aigen.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.aigen.domain.AiGenTask;
import com.ruoyi.aigen.mapper.AiGenTaskMapper;
import com.ruoyi.aigen.service.IAiGenTaskService;
import com.ruoyi.aigen.service.IAiService;

/**
 * AI代码生成服务实现
 * 
 * @author ruoyi
 */
@Service
public class AiGenTaskServiceImpl implements IAiGenTaskService {
    @Autowired
    private AiGenTaskMapper aiGenTaskMapper;
    
    @Autowired(required = false)
    private IAiService aiService;

    /**
     * 查询AI生成任务
     * 
     * @param taskId 任务ID
     * @return AI生成任务
     */
    @Override
    public AiGenTask selectAiGenTaskById(Long taskId) {
        return aiGenTaskMapper.selectAiGenTaskById(taskId);
    }

    /**
     * 查询AI生成任务列表
     * 
     * @param aiGenTask 查询条件
     * @return AI生成任务集合
     */
    @Override
    public List<AiGenTask> selectAiGenTaskList(AiGenTask aiGenTask) {
        return aiGenTaskMapper.selectAiGenTaskList(aiGenTask);
    }

    /**
     * 新增AI生成任务
     * 
     * @param aiGenTask AI生成任务
     * @return 结果
     */
    @Override
    public int insertAiGenTask(AiGenTask aiGenTask) {
        aiGenTask.setCreateBy(SecurityUtils.getUsername());
        aiGenTask.setCreateTime(DateUtils.getNowDate());
        aiGenTask.setStatus("0"); // 初始状态为进行中
        return aiGenTaskMapper.insertAiGenTask(aiGenTask);
    }

    /**
     * 修改AI生成任务
     * 
     * @param aiGenTask AI生成任务
     * @return 结果
     */
    @Override
    public int updateAiGenTask(AiGenTask aiGenTask) {
        aiGenTask.setUpdateBy(SecurityUtils.getUsername());
        aiGenTask.setUpdateTime(DateUtils.getNowDate());
        return aiGenTaskMapper.updateAiGenTask(aiGenTask);
    }

    /**
     * 删除AI生成任务信息
     * 
     * @param taskIds 需要删除的任务ID
     * @return 结果
     */
    @Override
    public int deleteAiGenTaskByIds(Long[] taskIds) {
        return aiGenTaskMapper.deleteAiGenTaskByIds(taskIds);
    }

    /**
     * 解析自然语言并生成数据库表结构
     * 
     * @param taskId 任务ID
     */
    @Override
    public void parseAndGenTable(Long taskId) {
        // 获取任务信息
        AiGenTask task = aiGenTaskMapper.selectAiGenTaskById(taskId);
        if (task == null) {
            throw new RuntimeException("任务不存在");
        }
        
        try {
            // 调用AI服务解析自然语言
            if (aiService != null) {
                String parsedResult = aiService.parseDescription(task.getDescription());
                // 生成数据库表结构
                String tableStructure = aiService.generateTableStructure(parsedResult);
                // TODO: 保存生成的表结构到ai_gen_table表
            } else {
                throw new RuntimeException("AI服务未配置");
            }
            
            // 更新任务状态为已完成
            task.setStatus("1");
            task.setUpdateBy(SecurityUtils.getUsername());
            task.setUpdateTime(DateUtils.getNowDate());
            aiGenTaskMapper.updateAiGenTask(task);
        } catch (Exception e) {
            // 更新任务状态为失败
            task.setStatus("2");
            task.setUpdateBy(SecurityUtils.getUsername());
            task.setUpdateTime(DateUtils.getNowDate());
            task.setRemark("失败原因: " + e.getMessage());
            aiGenTaskMapper.updateAiGenTask(task);
            throw new RuntimeException("解析失败: " + e.getMessage());
        }
    }

    /**
     * 预览代码
     * 
     * @param taskId 任务ID
     * @return 代码预览数据
     */
    @Override
    public Map<String, String> previewCode(Long taskId) {
        // 获取任务信息
        AiGenTask task = aiGenTaskMapper.selectAiGenTaskById(taskId);
        if (task == null) {
            throw new RuntimeException("任务不存在");
        }
        
        // 模拟代码预览数据
        Map<String, String> previewData = new HashMap<>();
        previewData.put("entity", "// 实体类代码\npublic class Example {\n    private Long id;\n    private String name;\n    // getter and setter\n}");
        previewData.put("mapper", "// Mapper接口代码\npublic interface ExampleMapper {\n    // 查询方法\n}");
        previewData.put("service", "// Service接口代码\npublic interface IExampleService {\n    // 业务方法\n}");
        previewData.put("controller", "// Controller代码\n@RestController\npublic class ExampleController {\n    // 接口方法\n}");
        
        // TODO: 实际实现时，应该从ai_gen_table和ai_gen_table_column表中获取数据，调用代码生成服务生成代码
        
        return previewData;
    }

    /**
     * 生成代码
     * 
     * @param taskId 任务ID
     */
    @Override
    public void genCode(Long taskId) {
        // 获取任务信息
        AiGenTask task = aiGenTaskMapper.selectAiGenTaskById(taskId);
        if (task == null) {
            throw new RuntimeException("任务不存在");
        }
        
        try {
            // 先确保已经解析并生成了表结构
            if (!"1".equals(task.getStatus())) {
                parseAndGenTable(taskId);
            }
            
            // TODO: 实际实现时，应该从ai_gen_table和ai_gen_table_column表中获取数据，调用代码生成服务生成代码并保存到指定目录
            
            // 更新任务状态为已完成
            task.setStatus("1");
            task.setUpdateBy(SecurityUtils.getUsername());
            task.setUpdateTime(DateUtils.getNowDate());
            aiGenTaskMapper.updateAiGenTask(task);
        } catch (Exception e) {
            // 更新任务状态为失败
            task.setStatus("2");
            task.setUpdateBy(SecurityUtils.getUsername());
            task.setUpdateTime(DateUtils.getNowDate());
            task.setRemark("失败原因: " + e.getMessage());
            aiGenTaskMapper.updateAiGenTask(task);
            throw new RuntimeException("代码生成失败: " + e.getMessage());
        }
    }
}