package com.ruoyi.starters.mybatisplus;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

class MybatisPlusStarterTest {
    @Test
    void contextLoads() {
        new ApplicationContextRunner()
                .withConfiguration(AutoConfigurations.of(MybatisPlusAutoConfiguration.class))
                .run(context -> {
                    assert context.containsBean("mybatisPlusInterceptor");
                });
    }
}
