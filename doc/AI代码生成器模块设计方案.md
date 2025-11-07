# AI代码生成器模块设计方案

## 1. 模块概述

AI代码生成器是若依(RuoYi-Vue)框架的一个扩展模块，通过接入AI大模型API，实现基于自然语言描述自动生成完整业务功能代码的能力。该模块将作为若依系统的一个独立模块，遵循若依的代码规范和目录结构，与现有代码生成器功能协同工作。

## 2. 系统架构

### 2.1 整体架构

```
AI代码生成器
├── 前端模块
│   ├── 功能描述输入界面
│   ├── 生成进度展示
│   ├── 代码预览与编辑
│   └── 一键部署功能
└── 后端模块
    ├── 自然语言解析服务
    ├── 数据库表结构生成器
    ├── 代码生成服务
    ├── 前端页面生成器
    └── 代码部署服务
```

### 2.2 技术栈

- **后端**：Spring Boot, MyBatis, Spring Security (与若依框架保持一致)
- **前端**：Vue.js, Element UI (与若依框架保持一致)
- **数据库**：MySQL
- **AI接口**：OpenAI API (可扩展支持其他AI大模型API)

## 3. 数据库设计

### 3.1 AI代码生成器相关表

#### 3.1.1 AI生成任务表 (ai_gen_task)

| 字段名 | 类型 | 说明 |
| --- | --- | --- |
| task_id | bigint(20) | 主键 |
| task_name | varchar(100) | 任务名称 |
| description | text | 功能描述(自然语言) |
| status | char(1) | 状态（0进行中 1已完成 2失败） |
| create_by | varchar(64) | 创建者 |
| create_time | datetime | 创建时间 |
| update_by | varchar(64) | 更新者 |
| update_time | datetime | 更新时间 |
| remark | varchar(500) | 备注 |

#### 3.1.2 AI生成表结构表 (ai_gen_table)

| 字段名 | 类型 | 说明 |
| --- | --- | --- |
| table_id | bigint(20) | 主键 |
| task_id | bigint(20) | 关联的任务ID |
| table_name | varchar(200) | 表名称 |
| table_comment | varchar(500) | 表描述 |
| class_name | varchar(100) | 实体类名称 |
| package_name | varchar(100) | 生成包路径 |
| module_name | varchar(30) | 生成模块名 |
| business_name | varchar(30) | 生成业务名 |
| function_name | varchar(50) | 生成功能名 |
| function_author | varchar(50) | 生成功能作者 |
| create_sql | text | 创建表SQL |
| create_by | varchar(64) | 创建者 |
| create_time | datetime | 创建时间 |
| update_by | varchar(64) | 更新者 |
| update_time | datetime | 更新时间 |

#### 3.1.3 AI生成表字段表 (ai_gen_table_column)

| 字段名 | 类型 | 说明 |
| --- | --- | --- |
| column_id | bigint(20) | 主键 |
| table_id | bigint(20) | 归属表编号 |
| column_name | varchar(200) | 列名称 |
| column_comment | varchar(500) | 列描述 |
| column_type | varchar(100) | 列类型 |
| java_type | varchar(500) | JAVA类型 |
| java_field | varchar(200) | JAVA字段名 |
| is_pk | char(1) | 是否主键（1是） |
| is_increment | char(1) | 是否自增（1是） |
| is_required | char(1) | 是否必填（1是） |
| is_insert | char(1) | 是否为插入字段（1是） |
| is_edit | char(1) | 是否编辑字段（1是） |
| is_list | char(1) | 是否列表字段（1是） |
| is_query | char(1) | 是否查询字段（1是） |
| query_type | varchar(200) | 查询方式（等于、不等于等） |
| html_type | varchar(200) | 显示类型（文本框、文本域等） |
| dict_type | varchar(200) | 字典类型 |
| sort | int | 排序 |
| create_by | varchar(64) | 创建者 |
| create_time | datetime | 创建时间 |
| update_by | varchar(64) | 更新者 |
| update_time | datetime | 更新时间 |

#### 3.1.4 AI配置表 (ai_config)

| 字段名 | 类型 | 说明 |
| --- | --- | --- |
| config_id | int(5) | 主键 |
| config_name | varchar(100) | 配置名称 |
| config_key | varchar(100) | 配置键名 |
| config_value | varchar(500) | 配置键值 |
| config_type | char(1) | 系统内置（Y是 N否） |
| create_by | varchar(64) | 创建者 |
| create_time | datetime | 创建时间 |
| update_by | varchar(64) | 更新者 |
| update_time | datetime | 更新时间 |
| remark | varchar(500) | 备注 |

## 4. 核心类设计

### 4.1 后端核心类

#### 4.1.1 控制器

```java
package com.ruoyi.aigen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.aigen.domain.AiGenTask;
import com.ruoyi.aigen.service.IAiGenTaskService;

/**
 * AI代码生成控制器
 */
@RestController
@RequestMapping("/aigen/task")
public class AiGenTaskController extends BaseController {
    @Autowired
    private IAiGenTaskService aiGenTaskService;
    
    /**
     * 查询AI生成任务列表
     */
    @PreAuthorize("@ss.hasPermi('aigen:task:list')")
    @GetMapping("/list")
    public TableDataInfo list(AiGenTask aiGenTask) {
        startPage();
        List<AiGenTask> list = aiGenTaskService.selectAiGenTaskList(aiGenTask);
        return getDataTable(list);
    }
    
    /**
     * 获取AI生成任务详细信息
     */
    @PreAuthorize("@ss.hasPermi('aigen:task:query')")
    @GetMapping(value = "/{taskId}")
    public AjaxResult getInfo(@PathVariable("taskId") Long taskId) {
        return success(aiGenTaskService.selectAiGenTaskById(taskId));
    }
    
    /**
     * 新增AI生成任务
     */
    @PreAuthorize("@ss.hasPermi('aigen:task:add')")
    @Log(title = "AI代码生成", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AiGenTask aiGenTask) {
        return toAjax(aiGenTaskService.insertAiGenTask(aiGenTask));
    }
    
    /**
     * 生成代码
     */
    @PreAuthorize("@ss.hasPermi('aigen:task:code')")
    @Log(title = "AI代码生成", businessType = BusinessType.GENCODE)
    @GetMapping("/genCode/{taskId}")
    public AjaxResult genCode(@PathVariable("taskId") Long taskId) {
        aiGenTaskService.genCode(taskId);
        return success();
    }
    
    /**
     * 预览代码
     */
    @PreAuthorize("@ss.hasPermi('aigen:task:preview')")
    @GetMapping("/preview/{taskId}")
    public AjaxResult preview(@PathVariable("taskId") Long taskId) {
        Map<String, String> dataMap = aiGenTaskService.previewCode(taskId);
        return success(dataMap);
    }
}
```

#### 4.1.2 服务接口

```java
package com.ruoyi.aigen.service;

import java.util.List;
import java.util.Map;
import com.ruoyi.aigen.domain.AiGenTask;

/**
 * AI代码生成服务接口
 */
public interface IAiGenTaskService {
    /**
     * 查询AI生成任务
     */
    public AiGenTask selectAiGenTaskById(Long taskId);
    
    /**
     * 查询AI生成任务列表
     */
    public List<AiGenTask> selectAiGenTaskList(AiGenTask aiGenTask);
    
    /**
     * 新增AI生成任务
     */
    public int insertAiGenTask(AiGenTask aiGenTask);
    
    /**
     * 修改AI生成任务
     */
    public int updateAiGenTask(AiGenTask aiGenTask);
    
    /**
     * 删除AI生成任务信息
     */
    public int deleteAiGenTaskByIds(Long[] taskIds);
    
    /**
     * 解析自然语言并生成数据库表结构
     */
    public void parseAndGenTable(Long taskId);
    
    /**
     * 预览代码
     */
    public Map<String, String> previewCode(Long taskId);
    
    /**
     * 生成代码
     */
    public void genCode(Long taskId);
}
```

#### 4.1.3 AI服务接口

```java
package com.ruoyi.aigen.service;

/**
 * AI服务接口
 */
public interface IAiService {
    /**
     * 解析自然语言描述
     * @param description 功能描述
     * @return 解析结果
     */
    public String parseDescription(String description);
    
    /**
     * 生成数据库表结构
     * @param parsedResult 解析结果
     * @return 表结构定义
     */
    public String generateTableStructure(String parsedResult);
    
    /**
     * 生成代码
     * @param tableStructure 表结构
     * @param templateType 模板类型
     * @return 生成的代码
     */
    public String generateCode(String tableStructure, String templateType);
}
```

#### 4.1.4 OpenAI服务实现

```java
package com.ruoyi.aigen.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.aigen.service.IAiService;
import com.ruoyi.aigen.config.AiConfig;

/**
 * OpenAI服务实现
 */
@Service
public class OpenAiServiceImpl implements IAiService {
    @Autowired
    private AiConfig aiConfig;
    
    /**
     * 解析自然语言描述
     */
    @Override
    public String parseDescription(String description) {
        // 调用OpenAI API解析自然语言描述
        // 返回结构化的解析结果
        return callOpenAiApi("parse", description);
    }
    
    /**
     * 生成数据库表结构
     */
    @Override
    public String generateTableStructure(String parsedResult) {
        // 调用OpenAI API生成数据库表结构
        return callOpenAiApi("table", parsedResult);
    }
    
    /**
     * 生成代码
     */
    @Override
    public String generateCode(String tableStructure, String templateType) {
        // 调用OpenAI API生成代码
        return callOpenAiApi("code", tableStructure + "\n" + templateType);
    }
    
    /**
     * 调用OpenAI API
     */
    private String callOpenAiApi(String type, String prompt) {
        // 实现OpenAI API调用
        // 根据不同类型构建不同的提示词
        // 返回API响应结果
        return "API响应结果";
    }
}
```

### 4.2 前端核心组件

#### 4.2.1 AI代码生成器主页面

```vue
<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="任务名称" prop="taskName">
        <el-input
          v-model="queryParams.taskName"
          placeholder="请输入任务名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="任务状态" clearable>
          <el-option
            v-for="dict in statusOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="创建时间">
        <el-date-picker
          v-model="dateRange"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['aigen:task:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['aigen:task:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['aigen:task:remove']"
        >删除</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="taskList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="任务ID" align="center" prop="taskId" />
      <el-table-column label="任务名称" align="center" prop="taskName" :show-overflow-tooltip="true" />
      <el-table-column label="功能描述" align="center" prop="description" :show-overflow-tooltip="true" />
      <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="statusOptions" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-view"
            @click="handlePreview(scope.row)"
            v-hasPermi="['aigen:task:preview']"
          >预览</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-download"
            @click="handleGenCode(scope.row)"
            v-hasPermi="['aigen:task:code']"
          >生成代码</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="任务名称" prop="taskName">
          <el-input v-model="form.taskName" placeholder="请输入任务名称" />
        </el-form-item>
        <el-form-item label="功能描述" prop="description">
          <el-input
            type="textarea"
            :rows="10"
            placeholder="请输入功能描述，尽可能详细描述您需要的功能，包括：业务需求、数据字段、页面功能等"
            v-model="form.description"
          ></el-input>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 代码预览 -->
    <el-dialog :title="preview.title" :visible.sync="preview.open" width="80%" top="5vh" append-to-body class="scrollbar">
      <el-tabs v-model="preview.activeName">
        <el-tab-pane
          v-for="(value, key) in preview.data"
          :label="key.substring(key.lastIndexOf('/')+1,key.indexOf('.vm'))"
          :name="key.substring(key.lastIndexOf('/')+1,key.indexOf('.vm'))"
          :key="key"
        >
          <el-link :underline="false" icon="el-icon-document-copy" v-clipboard:copy="value" v-clipboard:success="clipboardSuccess" style="float:right">复制</el-link>
          <pre><code class="hljs" v-html="highlightedCode(value, key)"></code></pre>
        </el-tab-pane>
      </el-tabs>
    </el-dialog>
  </div>
</template>
```

## 5. 集成到若依框架

### 5.1 模块集成步骤

1. **创建模块目录结构**
   - 在若依项目中创建新模块 `ruoyi-aigen`
   - 添加相应的 Maven 配置

2. **添加数据库表**
   - 执行SQL脚本创建所需的数据库表

3. **添加菜单和权限**
   - 在系统菜单中添加AI代码生成器模块
   - 配置相应的权限

4. **实现后端代码**
   - 实现控制器、服务、数据访问层等
   - 集成AI服务接口

5. **实现前端代码**
   - 添加前端页面和组件
   - 实现与后端的交互

### 5.2 菜单和权限配置

```sql
-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('AI代码生成器', '3', '5', 'aigen', 'aigen/task/index', 1, 0, 'C', '0', '0', 'aigen:task:list', 'code', 'admin', sysdate(), '', null, 'AI代码生成器菜单');

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('AI代码生成查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'aigen:task:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('AI代码生成新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'aigen:task:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('AI代码生成修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'aigen:task:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('AI代码生成删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'aigen:task:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('AI代码生成导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'aigen:task:export',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('代码生成', @parentId, '6',  '#', '', 1, 0, 'F', '0', '0', 'aigen:task:code',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('代码预览', @parentId, '7',  '#', '', 1, 0, 'F', '0', '0', 'aigen:task:preview',       '#', 'admin', sysdate(), '', null, '');
```

## 6. 使用示例

### 6.1 创建AI代码生成任务

1. 登录若依系统
2. 进入「工具」-「AI代码生成器」菜单
3. 点击「新增」按钮，填写任务信息：
   - 任务名称：客户管理系统
   - 功能描述：
     ```
     开发一个客户管理功能，包含以下需求：
     1. 客户基本信息管理：姓名、性别、年龄、电话、邮箱、地址、客户类型（个人/企业）、重要程度（普通/重要/VIP）
     2. 支持按客户姓名、电话、客户类型、重要程度进行查询
     3. 支持客户信息的增删改查操作
     4. 客户列表页面显示客户基本信息，并提供编辑、删除功能
     5. 客户详情页面显示客户的详细信息
     ```
4. 点击「确定」按钮提交任务

### 6.2 生成代码

1. 在任务列表中找到刚创建的任务
2. 点击「生成代码」按钮
3. 系统会自动解析需求，生成数据库表结构和代码
4. 生成完成后，可以点击「预览」按钮查看生成的代码
5. 确认无误后，可以将代码部署到系统中

## 7. 安全性考虑

### 7.1 API安全

- 使用环境变量或加密配置存储API密钥
- 实现API调用频率限制
- 记录API调用日志

### 7.2 输入验证

- 对用户输入进行安全验证
- 防止SQL注入和XSS攻击
- 限制输入长度和格式

### 7.3 代码安全

- 生成的代码遵循安全编码规范
- 自动添加必要的安全检查
- 避免生成包含