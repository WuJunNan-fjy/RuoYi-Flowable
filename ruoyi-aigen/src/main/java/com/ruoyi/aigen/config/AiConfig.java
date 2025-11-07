package com.ruoyi.aigen.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * AI配置类
 * 
 * @author ruoyi
 */
@Component
@ConfigurationProperties(prefix = "ai")
public class AiConfig {
    /** OpenAI API密钥 */
    private String openaiApiKey;
    
    /** OpenAI API地址 */
    private String openaiApiUrl;
    
    /** OpenAI 模型 */
    private String openaiModel;
    
    /** 代码生成路径 */
    private String genPath;
    
    /** 作者 */
    private String author;
    
    /** 默认包路径 */
    private String packageName;

    public String getOpenaiApiKey() {
        return openaiApiKey;
    }

    public void setOpenaiApiKey(String openaiApiKey) {
        this.openaiApiKey = openaiApiKey;
    }

    public String getOpenaiApiUrl() {
        return openaiApiUrl;
    }

    public void setOpenaiApiUrl(String openaiApiUrl) {
        this.openaiApiUrl = openaiApiUrl;
    }

    public String getOpenaiModel() {
        return openaiModel;
    }

    public void setOpenaiModel(String openaiModel) {
        this.openaiModel = openaiModel;
    }

    public String getGenPath() {
        return genPath;
    }

    public void setGenPath(String genPath) {
        this.genPath = genPath;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}