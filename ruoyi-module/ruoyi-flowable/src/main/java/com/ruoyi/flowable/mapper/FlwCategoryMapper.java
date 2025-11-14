package com.ruoyi.flowable.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.flowable.domain.category.FlwCategoryDO;
import com.ruoyi.starters.mybatisplus.core.mapper.BaseMapperX;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

/**
 * @description: 流程分类 Mapper
 * @author: wjn
 * @create: 2025-11-15 00:48
 * @version:v1.0
 */
@Mapper
public interface FlwCategoryMapper extends BaseMapperX<FlwCategoryDO> {
    @Insert("INSERT INTO flw_category(`id`,`code`,`name`,`sort`,`status`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) " +
            "VALUES(#{id},#{code},#{name},#{sort},#{status},#{createBy},#{createTime},#{updateBy},#{updateTime},#{remark})")
    @Options(useGeneratedKeys = false)
    int insertCategory(FlwCategoryDO entity);
}
