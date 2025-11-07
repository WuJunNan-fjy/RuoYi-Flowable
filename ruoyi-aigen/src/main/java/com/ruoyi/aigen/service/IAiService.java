package com.ruoyi.aigen.service;

/**
 * AI服务接口
 * 
 * @author ruoyi
 */
public interface IAiService {
    /**
     * 解析自然语言描述
     * 
     * @param description 功能描述
     * @return 解析结果
     */
    public String parseDescription(String description);
    
    /**
     * 生成数据库表结构
     * 
     * @param parsedResult 解析结果
     * @return 表结构定义
     */
    public String generateTableStructure(String parsedResult);
    
    /**
     * 生成代码
     * 
     * @param tableStructure 表结构
     * @param templateType 模板类型
     * @return 生成的代码
     */
    public String generateCode(String tableStructure, String templateType);
}