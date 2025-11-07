package com.ruoyi.aigen.service;

import java.util.List;
import java.util.Map;
import com.ruoyi.aigen.domain.AiGenTask;

/**
 * AI代码生成服务接口
 * 
 * @author ruoyi
 */
public interface IAiGenTaskService {
    /**
     * 查询AI生成任务
     * 
     * @param taskId 任务ID
     * @return AI生成任务
     */
    public AiGenTask selectAiGenTaskById(Long taskId);
    
    /**
     * 查询AI生成任务列表
     * 
     * @param aiGenTask 查询条件
     * @return AI生成任务集合
     */
    public List<AiGenTask> selectAiGenTaskList(AiGenTask aiGenTask);
    
    /**
     * 新增AI生成任务
     * 
     * @param aiGenTask AI生成任务
     * @return 结果
     */
    public int insertAiGenTask(AiGenTask aiGenTask);
    
    /**
     * 修改AI生成任务
     * 
     * @param aiGenTask AI生成任务
     * @return 结果
     */
    public int updateAiGenTask(AiGenTask aiGenTask);
    
    /**
     * 删除AI生成任务信息
     * 
     * @param taskIds 需要删除的任务ID
     * @return 结果
     */
    public int deleteAiGenTaskByIds(Long[] taskIds);
    
    /**
     * 解析自然语言并生成数据库表结构
     * 
     * @param taskId 任务ID
     */
    public void parseAndGenTable(Long taskId);
    
    /**
     * 预览代码
     * 
     * @param taskId 任务ID
     * @return 代码预览数据
     */
    public Map<String, String> previewCode(Long taskId);
    
    /**
     * 生成代码
     * 
     * @param taskId 任务ID
     */
    public void genCode(Long taskId);
}