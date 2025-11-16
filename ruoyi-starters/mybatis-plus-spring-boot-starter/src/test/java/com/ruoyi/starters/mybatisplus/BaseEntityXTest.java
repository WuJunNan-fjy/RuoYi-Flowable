package com.ruoyi.starters.mybatisplus;

import com.ruoyi.starters.mybatisplus.demo.DemoDO;
import com.ruoyi.starters.mybatisplus.demo.DemoMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest(classes = TestConfig.class)
public class BaseEntityXTest {

    @Autowired
    private DemoMapper demoMapper;

    @Test
    public void testInsertFill() {
        DemoDO d = new DemoDO();
        d.setId(1L);
        d.setCode("C1");
        d.setName("N1");
        demoMapper.insert(d);
        DemoDO db = demoMapper.selectById(1L);
        Assertions.assertNotNull(db.getCreateTime());
        Assertions.assertNotNull(db.getUpdateTime());
        Assertions.assertTrue(db.getCreateTime() instanceof Date);
        Assertions.assertTrue(db.getUpdateTime() instanceof Date);
    }
}
