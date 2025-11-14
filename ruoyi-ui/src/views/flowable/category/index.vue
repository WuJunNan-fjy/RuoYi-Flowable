<template>
    <div>
        <!-- 搜索工作栏 -->
        <ContentWrap>
            <el-form
                class="compact-form"
                :model="queryParams"
                ref="queryFormRef"
                :inline="true"
                label-width="68px"
            >
                <el-form-item label="分类编码" prop="code">
                    <el-input
                        v-model="queryParams.code"
                        placeholder="请输入分类编码"
                        clearable
                        @keyup.enter="handleQuery"
                        style="width: 240px"
                    />
                </el-form-item>

                <el-form-item label="分类名称" prop="name">
                    <el-input
                        v-model="queryParams.name"
                        placeholder="请输入分类名称"
                        clearable
                        @keyup.enter="handleQuery"
                        style="width: 240px"
                    />
                </el-form-item>

                <el-form-item>
                    <el-button @click="handleQuery">
                        <Icon icon="ep:search" class="mr-5px"/>
                        搜索
                    </el-button>
                    <el-button @click="resetQuery">
                        <Icon icon="ep:refresh" class="mr-5px"/>
                        重置
                    </el-button>
                    <el-button
                        type="primary"
                        plain
                        @click="openForm('create')"
                        v-hasPermi="['flowable:category:create']"
                    >
                        <Icon icon="ep:plus" class="mr-5px"/>
                        新增分类
                    </el-button>
                </el-form-item>
            </el-form>
        </ContentWrap>

        <!-- 列表 -->
        <ContentWrap>
            <el-table :data="tableData" style="width: 100%">
                <el-table-column prop="code" label="分类编码" />
                <el-table-column prop="name" label="分类名称" />
                <el-table-column prop="name" label="排序" />
                <el-table-column prop="createTime" label="创建时间" />
                <el-table-column prop="status" label="状态" />
                <el-table-column fixed="right" label="操作" >
                    <template #default>
                        <el-button link type="primary" size="small" @click="handleDelClick">
                            删除
                        </el-button>
                        <el-button link type="primary" size="small">修改</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <!-- 分页 -->
            <Pagination
                :total="total"
                v-model:page="queryParams.pageNo"
                v-model:limit="queryParams.pageSize"
                @pagination="loadData"
            />
        </ContentWrap>

        <!-- 表单弹窗：添加/修改流程 -->
        <CategoryForm ref="formRef" @success="loadData" />
    </div>
</template>

<script lang="ts" setup name="FlwCategory">
import CategoryForm from './CategoryForm.vue'
import * as CategoryApi from '@/api/flowable/category'

/* ----------------------------------------------------------------
 *  data数据
 * --------------------------------------------------------------*/
const tableData = ref([])   // 表格数据
const formRef = ref()
const queryFormRef = ref()
const total = ref(0)

// 查询参数 & 表单参数
const queryParams = ({
    pageNum: 1,
    pageSize: 10,
    code: '',
    name: '',
})

// 条件查询
const handleQuery = () => {
    loadData()
}

// 重置查询条件
const resetQuery = () => {
    Object.assign(queryParams, {
        key: '',
        name: '',
        category: ''
    })
    handleQuery()
}

// 查询分类列表
const loadData = async () => {
    try {
        const { rows, total: totalCount } = await CategoryApi.getCategoryList(toRaw(queryParams))
        tableData.value = rows;
        total.value = totalCount;
    } catch (e) {
        console.error(e)
    }
}

// 新增/修改分类
const openForm = (type: 'create' | 'update', id?: string) => {
    /* 弹窗逻辑待实现 */
    console.log(type, id)
    formRef.value.open(type, id)
}

const handleDelClick = () => {
    console.log('handleDelClick')
}

onMounted(async () => {
    await loadData()
})
</script>
<style scoped>
.compact-form {
    margin-bottom: -15px;
}
</style>
