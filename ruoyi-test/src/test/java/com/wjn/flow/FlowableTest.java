package com.wjn.flow;

import org.flowable.bpmn.model.BpmnModel;
import org.flowable.bpmn.model.ItemDefinition;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.Model;
import org.flowable.engine.repository.ModelQuery;
import org.flowable.engine.repository.ProcessDefinition;
import org.junit.Before;
import org.junit.Test;

public class FlowableTest {

    private ProcessEngine processEngine;

    @Test
    public void test() {
        ProcessEngineConfiguration configuration = new StandaloneProcessEngineConfiguration()
                .setJdbcUrl("jdbc:mysql://localhost:3307/ruoyi?serverTimezone=UTC&nullCatalogMeansCurrent=true&characterEncoding=UTF-8")
                .setJdbcDriver("com.mysql.cj.jdbc.Driver")
                .setJdbcUsername("root")
                .setJdbcPassword("1234")
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);  // 如果没有表就创建

        processEngine = configuration.buildProcessEngine();
    }

    // /**
    //  * 连接数据库
    //  */
    // @Before
    // public void initConnectDataSource(){
    //     ProcessEngineConfiguration configuration = new StandaloneProcessEngineConfiguration()
    //             .setJdbcUrl("jdbc:mysql://localhost:3307/sky?serverTimezone=UTC&nullCatalogMeansCurrent=true&characterEncoding=UTF-8")
    //             .setJdbcDriver("com.mysql.cj.jdbc.Driver")
    //             .setJdbcUsername("root")
    //             .setJdbcPassword("1234")
    //             .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);  // 如果没有表就创建
    //
    //     processEngine = configuration.buildProcessEngine();
    // }

    /**
     * 流程定义测试
     *
     */
    @Test
    public void ProcessDefinitionTest(){
        try {
            // 获取流程定义Service
            Deployment deploy = processEngine.getRepositoryService()
                    .createDeployment()
                    // .addZipInputStream(new ZipInputStream(in))
                    .addClasspathResource("bpmn/test.bpmn20.xml")
                    .name("请假流程")
                    .key("P_11111")
                    .deploy();
            System.out.println("deploy.getId() = " + deploy.getId());
            System.out.println("deploy.getName() = " + deploy.getName());
            System.out.println("deploy.getDeploymentTime() = " + deploy.getDeploymentTime());
            System.out.println("deploy.getResources() = " + deploy.getResources());
            System.out.println("deploy.getParentDeploymentId() = " + deploy.getParentDeploymentId());
            System.out.println("deploy.getEngineVersion() = " + deploy.getEngineVersion());
            System.out.println("deploy.getDerivedFrom() = " + deploy.getDerivedFrom());
            System.out.println("deploy.getDerivedFromRoot() = " + deploy.getDerivedFromRoot());
            System.out.println("deploy.getCategory() = " + deploy.getCategory());
            System.out.println("deploy.getTenantId() = " + deploy.getTenantId());
            System.out.println("deploy.isNew() = " + deploy.isNew());
            System.out.println("deploy.getKey() = " + deploy.getKey());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询流程定义
     */
    @Test
    public void ProcessDefinitionQueryTest(){
        ProcessDefinition processDefinition = processEngine.getRepositoryService()
                .createProcessDefinitionQuery()
                .deploymentId("12501").singleResult();

        System.out.println("processDefinition.getId() = " + processDefinition.getId());
        System.out.println("processDefinition.getName() = " + processDefinition.getName());
        System.out.println("processDefinition.getDescription() = " + processDefinition.getDescription());
        System.out.println("processDefinition.getDeploymentId() = " + processDefinition.getDeploymentId());
        System.out.println("processDefinition.getDiagramResourceName() = " + processDefinition.getDiagramResourceName());
    }

    /**
     * 删除流程定义
     */
    @Test
    public void deleteProcessDefinition(){
        processEngine.getRepositoryService()
                .deleteDeployment("10001", true);
    }

    @Test
    public void getBpmnModel(){
        RepositoryService repositoryService = processEngine.getRepositoryService();
        // BpmnModel bpmnModel = repositoryService.getBpmnModel("12501");
        // for (ItemDefinition itemDefinition : bpmnModel.getItemDefinitions().values()) {
        //     System.out.println("itemDefinition.getId() = " + itemDefinition.getId());
        //     System.out.println("itemDefinition.getName() = " + itemDefinition.getItemKind());
        // }

        Model p1864553692993159168 = repositoryService.createModelQuery()
                .modelKey("P_1864553692993159168").singleResult();

        ModelQuery modelQuery = repositoryService.createModelQuery();
        System.out.println("p1864553692993159168.list() = " + p1864553692993159168);
    }
}
