# mybatis-flex-spring-boot-starter

依赖引入：

```
<dependency>
  <groupId>com.ruoyi</groupId>
  <artifactId>mybatis-flex-spring-boot-starter</artifactId>
</dependency>
```

配置示例（application.yml）：

```
ruoyi:
  mybatis-flex:
    mapper-base-packages: com.ruoyi.**.mapper
```

基本使用：

- 在实体上使用 `@Table` 与 `@Id` 注解
- Mapper 接口继承 `com.mybatisflex.core.BaseMapper`
- 通过 `QueryWrapper` 构造查询并使用 `selectListByQuery`

示例：

```
@Table("flw_category")
public class FlwCategoryDO { @Id(keyType = KeyType.Auto) private Long id; }

@Mapper
public interface FlwCategoryMapper extends BaseMapper<FlwCategoryDO> {}

QueryWrapper qw = new QueryWrapper().from("flw_category").eq("code", "demo");
List<FlwCategoryDO> list = flwCategoryMapper.selectListByQuery(qw);
```
