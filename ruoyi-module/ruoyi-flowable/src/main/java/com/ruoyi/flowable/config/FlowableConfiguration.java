package com.ruoyi.flowable.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncListenableTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Flowable 配置类
 *
 * @author wjn
 */
@Configuration(proxyBeanMethods = false)
public class FlowableConfiguration {

    /**
     * 参考 {@link org.flowable.spring.boot.FlowableJobConfiguration} 类，创建对应的 AsyncListenableTaskExecutor Bean
     *
     * 如果不创建，会导致项目启动时，Flowable 报错的问题
     */
    @Bean(name = "applicationTaskExecutor")
    @ConditionalOnMissingBean(name = "applicationTaskExecutor")
    public AsyncListenableTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(8);
        executor.setMaxPoolSize(8);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("flowable-task-Executor-");
        executor.setAwaitTerminationSeconds(30);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAllowCoreThreadTimeOut(true);
        executor.initialize();
        return executor;
    }
    //
    // /**
    //  * BPM 模块的 ProcessEngineConfigurationConfigurer 实现类：
    //  *
    //  * 1. 设置各种监听器
    //  * 2. 设置自定义的 ActivityBehaviorFactory 实现
    //  */
    // @Bean
    // public EngineConfigurationConfigurer<SpringProcessEngineConfiguration> bpmProcessEngineConfigurationConfigurer(
    //         ObjectProvider<FlowableEventListener> listeners,
    //         ObjectProvider<FlowableFunctionDelegate> customFlowableFunctionDelegates,
    //         BpmActivityBehaviorFactory bpmActivityBehaviorFactory) {
    //     return configuration -> {
    //         // 注册监听器，例如说 BpmActivityEventListener
    //         configuration.setEventListeners(ListUtil.toList(listeners.iterator()));
    //         // 设置 ActivityBehaviorFactory 实现类，用于流程任务的审核人的自定义
    //         configuration.setActivityBehaviorFactory(bpmActivityBehaviorFactory);
    //         // 设置自定义的函数
    //         configuration.setCustomFlowableFunctionDelegates(ListUtil.toList(customFlowableFunctionDelegates.stream().iterator()));
    //     };
    // }
    //
    // // =========== 审批人相关的 Bean ==========
    //
    // @Bean
    // public BpmActivityBehaviorFactory bpmActivityBehaviorFactory(BpmTaskCandidateInvoker bpmTaskCandidateInvoker) {
    //     BpmActivityBehaviorFactory bpmActivityBehaviorFactory = new BpmActivityBehaviorFactory();
    //     bpmActivityBehaviorFactory.setTaskCandidateInvoker(bpmTaskCandidateInvoker);
    //     return bpmActivityBehaviorFactory;
    // }
    //
    // @Bean
    // @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") // adminUserApi 可以注入成功
    // public BpmTaskCandidateInvoker bpmTaskCandidateInvoker(List<BpmTaskCandidateStrategy> strategyList,
    //                                                        AdminUserApi adminUserApi) {
    //     return new BpmTaskCandidateInvoker(strategyList, adminUserApi);
    // }
    //
    // // =========== 自己拓展的 Bean ==========
    //
    // @Bean
    // public BpmProcessInstanceEventPublisher processInstanceEventPublisher(ApplicationEventPublisher publisher) {
    //     return new BpmProcessInstanceEventPublisher(publisher);
    // }

}
