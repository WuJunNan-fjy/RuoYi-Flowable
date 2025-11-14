# mybatis-plus-spring-boot-starter

依赖引入：

```
<dependency>
  <groupId>com.ruoyi</groupId>
  <artifactId>mybatis-plus-spring-boot-starter</artifactId>
</dependency>
```

配置示例（application.yml）：

```
ruoyi:
  mybatis-plus:
    mapper-base-packages: com.ruoyi.**.mapper
```

功能与使用：

- 自动配置 MyBatis-Plus 分页拦截器（MySQL 默认，可通过代码调整 `DbType`）
- 自动扫描 Mapper 包（`ruoyi.mybatis-plus.mapperBasePackages`）
- 提供通用填充器，按需填充 `BaseEntity` 的 `createBy/createTime/updateBy/updateTime`
- 提供增强查询封装：`LambdaQueryWrapperX`、`QueryWrapperX`、`MPJLambdaWrapperX`
- 提供增强 Mapper 接口：`BaseMapperX`（批量插入、便捷查询等）

示例：

```
@TableName("flw_category")
public class FlwCategoryDO extends BaseEntity { @TableId(type = IdType.AUTO) private Long id; }

@Mapper
public interface FlwCategoryMapper extends BaseMapper<FlwCategoryDO> {}

LambdaQueryWrapperX<FlwCategoryDO> qw = new LambdaQueryWrapperX<>()
        .eqIfPresent(FlwCategoryDO::getCode, "demo")
        .likeIfPresent(FlwCategoryDO::getName, "分类");
List<FlwCategoryDO> list = flwCategoryMapper.selectList(qw);
```
