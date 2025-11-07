package com.ruoyi.aigen.domain;

import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * AI生成表结构对象 ai_gen_table
 * 
 * @author ruoyi
 */
public class AiGenTable extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long tableId;

    /** 关联的任务ID */
    @Excel(name = "关联的任务ID")
    private Long taskId;

    /** 表名称 */
    @Excel(name = "表名称")
    private String tableName;

    /** 表描述 */
    @Excel(name = "表描述")
    private String tableComment;

    /** 实体类名称 */
    @Excel(name = "实体类名称")
    private String className;

    /** 生成包路径 */
    @Excel(name = "生成包路径")
    private String packageName;

    /** 生成模块名 */
    @Excel(name = "生成模块名")
    private String moduleName;

    /** 生成业务名 */
    @Excel(name = "生成业务名")
    private String businessName;

    /** 生成功能名 */
    @Excel(name = "生成功能名")
    private String functionName;

    /** 生成功能作者 */
    @Excel(name = "生成功能作者")
    private String functionAuthor;

    /** 创建表SQL */
    private String createSql;

    /** 其它生成选项 */
    private String options;

    /** 表字段 */
    private List<AiGenTableColumn> columns;

    /** 主键信息 */
    private AiGenTableColumn pkColumn;

    /** 关联的任务 */
    private AiGenTask task;

    public void setTableId(Long tableId) 
    {
        this.tableId = tableId;
    }

    public Long getTableId() 
    {
        return tableId;
    }
    
    public void setTaskId(Long taskId) 
    {
        this.taskId = taskId;
    }

    public Long getTaskId() 
    {
        return taskId;
    }
    
    public void setTableName(String tableName) 
    {
        this.tableName = tableName;
    }

    public String getTableName() 
    {
        return tableName;
    }
    
    public void setTableComment(String tableComment) 
    {
        this.tableComment = tableComment;
    }

    public String getTableComment() 
    {
        return tableComment;
    }
    
    public void setClassName(String className) 
    {
        this.className = className;
    }

    public String getClassName() 
    {
        return className;
    }
    
    public void setPackageName(String packageName) 
    {
        this.packageName = packageName;
    }

    public String getPackageName() 
    {
        return packageName;
    }
    
    public void setModuleName(String moduleName) 
    {
        this.moduleName = moduleName;
    }

    public String getModuleName() 
    {
        return moduleName;
    }
    
    public void setBusinessName(String businessName) 
    {
        this.businessName = businessName;
    }

    public String getBusinessName() 
    {
        return businessName;
    }
    
    public void setFunctionName(String functionName) 
    {
        this.functionName = functionName;
    }

    public String getFunctionName() 
    {
        return functionName;
    }
    
    public void setFunctionAuthor(String functionAuthor) 
    {
        this.functionAuthor = functionAuthor;
    }

    public String getFunctionAuthor() 
    {
        return functionAuthor;
    }
    
    public void setCreateSql(String createSql) 
    {
        this.createSql = createSql;
    }

    public String getCreateSql() 
    {
        return createSql;
    }
    
    public void setOptions(String options) 
    {
        this.options = options;
    }

    public String getOptions() 
    {
        return options;
    }
    
    public List<AiGenTableColumn> getColumns()
    {
        return columns;
    }

    public void setColumns(List<AiGenTableColumn> columns)
    {
        this.columns = columns;
    }

    public AiGenTableColumn getPkColumn()
    {
        return pkColumn;
    }

    public void setPkColumn(AiGenTableColumn pkColumn)
    {
        this.pkColumn = pkColumn;
    }
    
    public AiGenTask getTask()
    {
        return task;
    }

    public void setTask(AiGenTask task)
    {
        this.task = task;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("tableId", getTableId())
            .append("taskId", getTaskId())
            .append("tableName", getTableName())
            .append("tableComment", getTableComment())
            .append("className", getClassName())
            .append("packageName", getPackageName())
            .append("moduleName", getModuleName())
            .append("businessName", getBusinessName())
            .append("functionName", getFunctionName())
            .append("functionAuthor", getFunctionAuthor())
            .append("createSql", getCreateSql())
            .append("options", getOptions())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}