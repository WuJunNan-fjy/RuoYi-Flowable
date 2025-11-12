package com.ruoyi.common.core.page;

import com.github.pagehelper.PageInfo;
import java.util.List;

/**
 * 快速构建 TableDataInfo 的静态工具
 */
public class TableDataInfoBuilder {

    /**
     * 从已分页的 List 中直接构建 TableDataInfo
     * 无需 new，无需 PageHelper.startPage 再次调用
     */
    public static TableDataInfo build(List<?> list) {
        PageInfo<?> pageInfo = new PageInfo<>(list);
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(200);
        rspData.setMsg("查询成功");
        rspData.setRows(pageInfo.getList());
        rspData.setTotal(pageInfo.getTotal());
        return rspData;
    }

    /**
     * 构建 TableDataInfo
     * 无需 new，无需 PageHelper.startPage 再次调用
     */
    public static TableDataInfo build(List<?> list, long total) {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(200);
        rspData.setMsg("查询成功");
        rspData.setRows(list);
        rspData.setTotal(total);
        return rspData;
    }
}
