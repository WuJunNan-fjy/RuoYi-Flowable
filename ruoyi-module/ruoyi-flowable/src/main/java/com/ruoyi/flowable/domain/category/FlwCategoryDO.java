package com.ruoyi.flowable.domain.category;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.NoArgsConstructor;

/**
 * @description: 流程分类对象 flw_category
 * @author: wjn
 * @create: 2025-11-12 23:30
 * @version:v1.0
 */
@Data
@Builder
@TableName("flw_category")
@NoArgsConstructor
@AllArgsConstructor
public class FlwCategoryDO extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @TableId
    @Schema(description = "主键")
    private Long id;

    /** 分类编码 */
    @Schema(description = "分类编码")
    private String code;

    /** 分类名称 */
    @Schema(description = "分类名称")
    private String name;

    /** 排序码 */
    @Schema(description = "排序码")
    private Long sort;

    /** 状态 */
    @Schema(description = "状态")
    private Long status;
}
