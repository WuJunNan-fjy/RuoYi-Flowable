package com.ruoyi.flowable.domain.model.vo;

import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @description: 查询流程模型VO
 * @author: wjn
 * @create: 2025-11-12 21:23
 * @version:v1.0
 */
@Data
public class FlwModelQueryReqVO extends BaseEntity {

    @Schema(description = "当前页", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Integer pageNum;

    @Schema(description = "每页数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "10")
    private Integer pageSize;

    @Schema(description = "流程标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "P_雪花id")
    private String key;

    @Schema(description = "流程名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "流程名称")
    private String name;

    @Schema(description = "流程分类")
    private String category;
}
