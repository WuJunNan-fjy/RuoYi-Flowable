package com.ruoyi.flowable.domain.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * @description: 模型响应VO
 * @author: wjn
 * @create: 2025-11-12 21:31
 * @version:v1.0
 */
@Data
public class FlwModelRespVO {

    @Schema(description = "模型ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private String id;

    @Schema(description = "流程名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "流程名称")
    private String name;

    @Schema(description = "流程标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "P_雪花id")
    private String key;

    @Schema(description = "流程分类", requiredMode = Schema.RequiredMode.REQUIRED, example = "流程分类")
    private String category;

    @Schema(description = "流程分类名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "流程分类名称")
    private String categoryName;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED, example = "2025-11-12 21:31")
    private Date createTime;

    @Schema(description = "最后更新时间", requiredMode = Schema.RequiredMode.REQUIRED, example = "2025-11-12 21:31")
    private Date lastUpdateTime;

    @Schema(description = "版本", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Integer version;

    @Schema(description = "元数据", requiredMode = Schema.RequiredMode.REQUIRED, example = "{}")
    private String metaInfo;
}
