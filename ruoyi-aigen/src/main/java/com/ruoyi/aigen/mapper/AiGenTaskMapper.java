package com.ruoyi.aigen.mapper;

import java.util.List;
import com.ruoyi.aigen.domain.AiGenTask;

/**
 * AI生成任务Mapper接口
 * 
 * @author ruoyi
 */
public interface AiGenTaskMapper {
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
     * 删除AI生成任务
     * 
     * @param taskId 任务ID
     * @return 结果
     */
    public int deleteAiGenTaskById(Long taskId);

    /**
     * 批量删除AI生成任务
     * 
     * @param taskIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteAiGenTaskByIds(Long[] taskIds);
}