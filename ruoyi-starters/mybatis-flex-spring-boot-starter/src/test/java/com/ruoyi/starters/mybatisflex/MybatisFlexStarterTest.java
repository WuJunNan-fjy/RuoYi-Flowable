package com.ruoyi.starters.mybatisflex;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

class MybatisFlexStarterTest {
    @Test
    void contextLoads() {
        new ApplicationContextRunner()
                .withConfiguration(AutoConfigurations.of(MybatisFlexAutoConfiguration.class))
                .run(context -> {
                    assert context.containsBean("flexGlobalConfig");
                });
    }
}

