package com.ruoyi.starters.mybatisflex;

import com.mybatisflex.core.FlexGlobalConfig;
import com.mybatisflex.core.audit.AuditManager;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass({FlexGlobalConfig.class, org.apache.ibatis.session.Configuration.class})
@EnableConfigurationProperties(MybatisFlexProperties.class)
@AutoConfigureAfter(DataSourceAutoConfiguration.class)
public class MybatisFlexAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public FlexGlobalConfig flexGlobalConfig() {
        FlexGlobalConfig config = new FlexGlobalConfig();
        AuditManager.setAuditEnable(true);
        return config;
    }

    @Bean
    @ConditionalOnMissingBean
    public org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer flexConfigurationCustomizer() {
        return new org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer() {
            @Override
            public void customize(org.apache.ibatis.session.Configuration configuration) {
                configuration.setMapUnderscoreToCamelCase(true);
            }
        };
    }

    @Bean
    @ConditionalOnMissingBean
    public MapperScannerConfigurer mapperScannerConfigurer(MybatisFlexProperties properties) {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setBasePackage(properties.getMapperBasePackages());
        return configurer;
    }
}
