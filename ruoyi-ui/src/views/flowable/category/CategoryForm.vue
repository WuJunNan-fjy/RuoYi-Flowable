<template>
    <Dialog v-model="dialogVisible" :title="dialogTitle" width="600">
        <el-form
            ref="formRef"
            v-loading="formLoading"
            :model="formData"
            :rules="formRules"
            label-width="110px"
        >
            <el-form-item label="分类编码" prop="code">
                <el-input
                    v-model="formData.code"
                    :disabled="!!formData.id"
                    clearable
                    placeholder="请输入分类编码"
                />
            </el-form-item>
            <el-form-item label="分类名称" prop="name">
                <el-input
                    v-model="formData.name"
                    :disabled="!!formData.id"
                    clearable
                    placeholder="请输入流程名称"
                />
            </el-form-item>
            <el-form-item label="分类状态" prop="status">
                <el-radio-group v-model="formData.status">
                    <el-radio
                        v-for="dict in sysNormalDisable"
                        :key="dict.value"
                        :label="dict.value"
                    >
                        {{ dict.label }}
                    </el-radio>
                </el-radio-group>
            </el-form-item>
            <el-form-item label="分类排序" prop="sort">
                <el-input-number
                    v-model="formData.sort"
                    placeholder="请输入分类排序"
                    class="!w-1/1"
                    :precision="0"
                />
            </el-form-item>
            <el-form-item label="分类描述" prop="remark">
                <el-input
                    v-model="formData.remark"
                    clearable
                    placeholder="请输入分类描述"
                />
            </el-form-item>
        </el-form>
        <template #footer>
            <el-button :disabled="formLoading" type="primary" @click="submitForm">确 定</el-button>
            <el-button @click="dialogVisible = false">取 消</el-button>
        </template>
    </Dialog>
</template>
<script lang="ts" setup>
defineOptions({ name: 'CategoryForm' })
import * as CategoryApi from '@/api/flowable/category'
const message = useMessage() // 消息弹窗

const { sys_normal_disable: sysNormalDisable } = useDict('sys_normal_disable')

const { t } = useI18n() // 国际化
const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
    id: undefined,
    name: '',
    code: '',
    remark: '',
    sort: 1,
    status: '0'
})
const formRules = reactive({
    name: [{ required: true, message: '分类名称不能为空', trigger: 'blur' }],
    code: [{ required: true, message: '分类编码不能为空', trigger: 'blur' }],
    status: [{ required: true, message: '分类状态不能为空', trigger: 'change' }],
    sort: [{ required: true, message: '分类排序不能为空', trigger: 'blur' }]
})
const formRef = ref() // 表单 Ref

/** 打开弹窗 */
const open = async (type: string, id?: string) => {
    dialogVisible.value = true
    dialogTitle.value = t('action.' + type)
    formType.value = type
    resetForm()
    // 修改时，设置数据
    if (id) {
        formLoading.value = true
        try {
            formData.value = await CategoryApi.getCategoryDetails(id)
        } finally {
            formLoading.value = false
        }
    }
}
defineExpose({ open }) // 提供 open 方法，用于打开弹窗

/** 提交表单 */
const emit = defineEmits(['success']) // 定义 success 事件，用于操作成功后的回调
const submitForm = async () => {
    // 校验表单
    if (!formRef) return
    const valid = await formRef.value.validate()
    if (!valid) return
    // 提交请求
    formLoading.value = true
    try {
        const data = formData.value as unknown as CategoryApi.CategoryVO
        if (formType.value === 'create') {
            await CategoryApi.createCategory(data)
            message.success(t('common.createSuccess'))
        } else {
            await CategoryApi.updateCategory(data)
            message.success(t('common.updateSuccess'))
        }
        dialogVisible.value = false
        emit('success')
    } finally {
        formLoading.value = false
    }
}

/** 重置表单 */
const resetForm = () => {
    formData.value = {
        id: undefined,
        name: '',
        code: '',
        sort: 1,
        status: '0',
        remark: '',
    }
    formRef.value?.resetFields()
}
</script>
