package com.ruoyi.starters.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@SpringBootConfiguration
@EnableAutoConfiguration
@MapperScan("com.ruoyi.starters.mybatisplus.demo")
public class TestConfig {
}
