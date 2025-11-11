package com.ruoyi.flowable.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @description: 模型保存请求参数
 * @author: wjn
 * @create: 2025-11-11 00:07
 * @version:v1.0
 */
@Data
@Schema(description = "流程模型的保存 Request VO")
public class FlwModelSaveReqVO {

    @Schema(description = "流程标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "P_雪花id")
    private String key;

    @Schema(description = "流程名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "流程名称")
    private String name;

    @Schema(description = "流程描述", requiredMode = Schema.RequiredMode.REQUIRED, example = "流程描述")
    private String description;
}
