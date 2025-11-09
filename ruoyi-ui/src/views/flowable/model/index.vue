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
                <el-form-item label="流程标识" prop="key">
                    <el-input
                        v-model="queryParams.key"
                        placeholder="请输入流程标识"
                        clearable
                        @keyup.enter="handleQuery"
                        style="width: 240px"
                    />
                </el-form-item>

                <el-form-item label="流程名称" prop="name">
                    <el-input
                        v-model="queryParams.name"
                        placeholder="请输入流程名称"
                        clearable
                        @keyup.enter="handleQuery"
                        style="width: 240px"
                    />
                </el-form-item>

                <el-form-item label="流程分类" prop="category">
                    <el-select
                        v-model="queryParams.category"
                        placeholder="请选择流程分类"
                        clearable
                        style="width: 240px"
                    >
                        <el-option
                            v-for="category in categoryList"
                            :key="category.code"
                            :label="category.name"
                            :value="category.code"
                        />
                    </el-select>
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
                        v-hasPermi="['bpm:model:create']"
                    >
                        <Icon icon="ep:plus" class="mr-5px"/>
                        新增流程
                    </el-button>
                </el-form-item>
            </el-form>
        </ContentWrap>

        <!-- 列表 -->
        <ContentWrap>
            <el-table :data="tableData" style="width: 100%">
                <el-table-column prop="key" label="流程标识" />
                <el-table-column prop="category" label="流程分类" />
                <el-table-column prop="name" label="流程名称" />
                <el-table-column fixed="right" label="操作" >
                    <template #default>
                        <el-button link type="primary" size="small" @click="handleClick">
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
        <ModelForm ref="formRef" @success="loadData" />
    </div>
</template>

<script lang="ts" setup name="FlwModel">
import ModelForm from './ModelForm.vue'
import type {FlwModelQuery, FlwModelTableItem} from '@/types'

/* ----------------------------------------------------------------
 *  搜索表单 & 表格数据
 * --------------------------------------------------------------*/
const queryFormRef = ref()
const categoryList = ref<any[]>([])               // 流程分类下拉
const tableData = ref<FlwModelTableItem[]>([])   // 表格数据
const total = ref(0)


const queryParams = reactive<FlwModelQuery>({
    pageNo: 1,
    pageSize: 10,
    key: '',
    name: '',
    category: ''
})

const handleQuery = () => {
    loadData()
}

const resetQuery = () => {
    Object.assign(queryParams, {key: '', name: '', category: ''})
    handleQuery()
}

const loadData = async () => {
    try {
        // const { list } = await ModelApi.getModelPage(toRaw(queryParams))
        // tableData.value = list
    } catch (e) {
        console.error(e)
    }
}

const handleClick = () => {
    console.log('handleClick')
}

const formRef = ref()
const openForm = (type: 'create' | 'update', id?: string) => {
    /* 弹窗逻辑待实现 */
    console.log(type, id)
    formRef.value.open(type, id)
}

onMounted(async () => {
    // 1. 取流程分类下拉数据
    // categoryList.value = await CategoryApi.getCategorySimpleList()
    // 2. 拉列表
    await loadData()
})
</script>
<style scoped>
.compact-form {
    margin-bottom: -15px;
}
</style>
