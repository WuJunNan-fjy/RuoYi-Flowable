package com.ruoyi.flowable;

import com.ruoyi.RuoYiApplication;
import com.ruoyi.flowable.api.category.request.FlwCategoryQueryReq;
import com.ruoyi.flowable.domain.category.FlwCategoryDO;
import com.ruoyi.flowable.mapper.FlwCategoryMapper;
import com.ruoyi.flowable.service.impl.FlwCategoryServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = RuoYiApplication.class)
public class FlwCategorySelectListTest {

    @Autowired
    private FlwCategoryMapper flwCategoryMapper;

    @Autowired
    private FlwCategoryServiceImpl flwCategoryService;

    @Test
    public void testSelectListWithWrapper() {
        FlwCategoryDO entity = FlwCategoryDO.builder()
                .id(System.currentTimeMillis())
                .code("TEST_CODE")
                .name("测试分类")
                .sort(1L)
                .status(1L)
                .build();
        flwCategoryMapper.insert(entity);

        FlwCategoryQueryReq req = new FlwCategoryQueryReq();
        req.setCode("TEST_CODE");
        req.setName("测试");
        List<FlwCategoryDO> list = flwCategoryService.queryCategoryList(req);
        Assertions.assertFalse(list.isEmpty());
        Assertions.assertEquals("TEST_CODE", list.get(0).getCode());
    }
}

