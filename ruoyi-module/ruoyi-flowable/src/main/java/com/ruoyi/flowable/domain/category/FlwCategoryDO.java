package com.ruoyi.flowable.domain.category;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.ruoyi.starters.mybatisplus.base.BaseEntityX;
import io.swagger.v3.oas.annotations.media.Schema;
import liquibase.pro.packaged.S;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Map;

/**
 * @description: 流程分类对象 flw_category
 * @author: wjn
 * @create: 2025-11-12 23:30
 * @version:v1.0
 */
@Data
@Builder
@TableName(value = "flw_category")
@NoArgsConstructor
@AllArgsConstructor
public class FlwCategoryDO extends BaseEntityX
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @TableId(type = IdType.ASSIGN_ID)
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

    /** 分类备注 */
    @Schema(description = "分类备注")
    private String remark;
}
