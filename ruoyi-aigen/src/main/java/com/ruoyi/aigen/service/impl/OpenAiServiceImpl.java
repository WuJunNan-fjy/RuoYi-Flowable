package com.ruoyi.aigen.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import com.ruoyi.aigen.service.IAiService;
import com.ruoyi.aigen.config.AiConfig;
import com.ruoyi.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * OpenAI服务实现
 *
 * @author ruoyi
 */
@Service
public class OpenAiServiceImpl implements IAiService {
    private static final Logger log = LoggerFactory.getLogger(OpenAiServiceImpl.class);

    @Autowired(required = false)
    private AiConfig aiConfig;

    /**
     * 解析自然语言描述
     */
    @Override
    public String parseDescription(String description) {
        if (StringUtils.isEmpty(description)) {
            throw new RuntimeException("功能描述不能为空");
        }
        log.info("开始解析自然语言描述: {}", description);
        // 调用OpenAI API解析自然语言描述
        log.info("开始调用OpenAI API");
        // 返回结构化的解析结果
        return callOpenAiApi("parse", description);
    }

    /**
     * 生成数据库表结构
     */
    @Override
    public String generateTableStructure(String parsedResult) {
        if (StringUtils.isEmpty(parsedResult)) {
            throw new RuntimeException("解析结果不能为空");
        }
        log.info("开始生成数据库表结构");
        // 调用OpenAI API生成数据库表结构
        return callOpenAiApi("table", parsedResult);
    }

    /**
     * 生成代码
     */
    @Override
    public String generateCode(String tableStructure, String templateType) {
        if (StringUtils.isEmpty(tableStructure)) {
            throw new RuntimeException("表结构不能为空");
        }
        log.info("开始生成代码，模板类型: {}", templateType);
        // 调用OpenAI API生成代码
        return callOpenAiApi("code", tableStructure + "\n" + templateType);
    }

    /**
     * 调用OpenAI API
     */
    private String callOpenAiApi(String type, String prompt) {
        // 检查配置是否存在
        if (aiConfig == null) {
            log.error("AI配置未初始化");
            throw new RuntimeException("AI服务未配置");
        }

        try {
            // 构建请求URL
            String apiUrl = aiConfig.getOpenaiApiUrl();

            // 构建请求头
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(aiConfig.getOpenaiApiKey());

            // 构建请求体
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("model", StringUtils.defaultIfEmpty(aiConfig.getOpenaiModel(), "gpt-3.5-turbo"));

            // 根据不同类型构建不同的提示词
            String systemPrompt = "";
            switch (type) {
                case "parse":
                    systemPrompt = "你是一个专业的需求分析师，请分析以下功能描述，提取关键信息并给出结构化的分析结果：";
                    break;
                case "table":
                    systemPrompt = "你是一个专业的数据库设计师，请根据以下需求分析结果，设计合适的数据库表结构：";
                    break;
                case "code":
                    systemPrompt = "你是一个专业的Java开发工程师，请根据以下数据库表结构生成对应的Java代码：";
                    break;
                default:
                    throw new RuntimeException("未知的API调用类型");
            }

            List<Map<String, String>> messages = new ArrayList<>();

            Map<String, String> systemMessage = new HashMap<>();
            systemMessage.put("role", "system");
            systemMessage.put("content", systemPrompt);
            messages.add(systemMessage);

            Map<String, String> userMessage = new HashMap<>();
            userMessage.put("role", "user");
            userMessage.put("content", prompt);
            messages.add(userMessage);

            requestBody.put("messages", messages);

            // 发送请求
            HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);
            RestTemplate restTemplate = new RestTemplate();

            log.info("开始调用OpenAI API，类型: {}", type);
            ResponseEntity<Map> response = restTemplate.postForEntity(apiUrl, requestEntity, Map.class);

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                List<Map<String, Object>> choices = (List<Map<String, Object>>) response.getBody().get("choices");
                if (!choices.isEmpty()) {
                    Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
                    String content = (String) message.get("content");
                    log.info("OpenAI API调用成功");
                    return content.trim();
                }
            }

            throw new RuntimeException("API调用失败: " + response.getStatusCode());
        } catch (Exception e) {
            log.error("调用OpenAI API失败", e);
            throw new RuntimeException("AI服务调用失败: " + e.getMessage());
        }
    }
}
