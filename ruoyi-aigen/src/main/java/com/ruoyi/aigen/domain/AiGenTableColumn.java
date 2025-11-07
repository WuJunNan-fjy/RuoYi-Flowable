package com.ruoyi.aigen.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.utils.StringUtils;

/**
 * AI生成表字段对象 ai_gen_table_column
 * 
 * @author ruoyi
 */
public class AiGenTableColumn extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long columnId;

    /** 归属表编号 */
    private Long tableId;

    /** 列名称 */
    private String columnName;

    /** 列描述 */
    private String columnComment;

    /** 列类型 */
    private String columnType;

    /** JAVA类型 */
    private String javaType;

    /** JAVA字段名 */
    @Excel(name = "JAVA字段名")
    private String javaField;

    /** 是否主键（1是） */
    private String isPk;

    /** 是否自增（1是） */
    private String isIncrement;

    /** 是否必填（1是） */
    private String isRequired;

    /** 是否为插入字段（1是） */
    private String isInsert;

    /** 是否编辑字段（1是） */
    private String isEdit;

    /** 是否列表字段（1是） */
    private String isList;

    /** 是否查询字段（1是） */
    private String isQuery;

    /** 查询方式（等于、不等于、大于、小于、范围） */
    private String queryType;

    /** 显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件） */
    private String htmlType;

    /** 字典类型 */
    private String dictType;

    /** 排序 */
    private Integer sort;

    public void setColumnId(Long columnId) 
    {
        this.columnId = columnId;
    }

    public Long getColumnId() 
    {
        return columnId;
    }
    public void setTableId(Long tableId) 
    {
        this.tableId = tableId;
    }

    public Long getTableId() 
    {
        return tableId;
    }
    public void setColumnName(String columnName) 
    {
        this.columnName = columnName;
    }

    public String getColumnName() 
    {
        return columnName;
    }
    public void setColumnComment(String columnComment) 
    {
        this.columnComment = columnComment;
    }

    public String getColumnComment() 
    {
        return columnComment;
    }
    public void setColumnType(String columnType) 
    {
        this.columnType = columnType;
    }

    public String getColumnType() 
    {
        return columnType;
    }
    public void setJavaType(String javaType) 
    {
        this.javaType = javaType;
    }

    public String getJavaType() 
    {
        return javaType;
    }
    public void setJavaField(String javaField) 
    {
        this.javaField = javaField;
    }

    public String getJavaField() 
    {
        return javaField;
    }
    public void setIsPk(String isPk) 
    {
        this.isPk = isPk;
    }

    public String getIsPk() 
    {
        return isPk;
    }
    public void setIsIncrement(String isIncrement) 
    {
        this.isIncrement = isIncrement;
    }

    public String getIsIncrement() 
    {
        return isIncrement;
    }
    public void setIsRequired(String isRequired) 
    {
        this.isRequired = isRequired;
    }

    public String getIsRequired() 
    {
        return isRequired;
    }
    public void setIsInsert(String isInsert) 
    {
        this.isInsert = isInsert;
    }

    public String getIsInsert() 
    {
        return isInsert;
    }
    public void setIsEdit(String isEdit) 
    {
        this.isEdit = isEdit;
    }

    public String getIsEdit() 
    {
        return isEdit;
    }
    public void setIsList(String isList) 
    {
        this.isList = isList;
    }

    public String getIsList() 
    {
        return isList;
    }
    public void setIsQuery(String isQuery) 
    {
        this.isQuery = isQuery;
    }

    public String getIsQuery() 
    {
        return isQuery;
    }
    public void setQueryType(String queryType) 
    {
        this.queryType = queryType;
    }

    public String getQueryType() 
    {
        return queryType;
    }
    public void setHtmlType(String htmlType) 
    {
        this.htmlType = htmlType;
    }

    public String getHtmlType() 
    {
        return htmlType;
    }
    public void setDictType(String dictType) 
    {
        this.dictType = dictType;
    }

    public String getDictType() 
    {
        return dictType;
    }
    public void setSort(Integer sort) 
    {
        this.sort = sort;
    }

    public Integer getSort() 
    {
        return sort;
    }

    public boolean isPk()
    {
        return isPk(this.isPk);
    }

    public boolean isPk(String isPk)
    {
        return isPk != null && StringUtils.equals("1", isPk);
    }

    public boolean isIncrement()
    {
        return isIncrement(this.isIncrement);
    }

    public boolean isIncrement(String isIncrement)
    {
        return isIncrement != null && StringUtils.equals("1", isIncrement);
    }

    public boolean isRequired()
    {
        return isRequired(this.isRequired);
    }

    public boolean isRequired(String isRequired)
    {
        return isRequired != null && StringUtils.equals("1", isRequired);
    }

    public boolean isInsert()
    {
        return isInsert(this.isInsert);
    }

    public boolean isInsert(String isInsert)
    {
        return isInsert != null && StringUtils.equals("1", isInsert);
    }

    public boolean isEdit()
    {
        return isInsert(this.isEdit);
    }

    public boolean isEdit(String isEdit)
    {
        return isEdit != null && StringUtils.equals("1", isEdit);
    }

    public boolean isList()
    {
        return isList(this.isList);
    }

    public boolean isList(String isList)
    {
        return isList != null && StringUtils.equals("1", isList);
    }

    public boolean isQuery()
    {
        return isQuery(this.isQuery);
    }

    public boolean isQuery(String isQuery)
    {
        return isQuery != null && StringUtils.equals("1", isQuery);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("columnId", getColumnId())
            .append("tableId", getTableId())
            .append("columnName", getColumnName())
            .append("columnComment", getColumnComment())
            .append("columnType", getColumnType())
            .append("javaType", getJavaType())
            .append("javaField", getJavaField())
            .append("isPk", getIsPk())
            .append("isIncrement", getIsIncrement())
            .append("isRequired", getIsRequired())
            .append("isInsert", getIsInsert())
            .append("isEdit", getIsEdit())
            .append("isList", getIsList())
            .append("isQuery", getIsQuery())
            .append("queryType", getQueryType())
            .append("htmlType", getHtmlType())
            .append("dictType", getDictType())
            .append("sort", getSort())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}