import request from '@/utils/request';

export type CategoryVO = {
    id: number
    code: string
    name: string
    sort: number
    status: number
    remark: string
}

/**
 * 获取流程分类列表
 * @param params
 */
export const getCategoryList = async (params: object) => {
    return await request.get({ url: '/flowable/category/queryCategoryList', params })
}

/**
 * 获取流程分类详情
 * @param params
 */
export const getCategoryDetails = async (id: string) => {
    return await request.detail({ url: '/flowable/category/details', id})
}

/**
 * 创建流程分类
 * @param data
 */
export const createCategory = async (data: CategoryVO) => {
    return await request.post({ url: '/flowable/category/create', data })
}

/**
 * 更新流程分类
 * @param data
 */
export const updateCategory = async (data: CategoryVO) => {
    return await request.put({ url: '/flowable/category/update', data })
}

/**
 * 删除流程分类
 * @param id
 */
export const deleteCategory = async (id: number) => {
    return await request.delete({ url: '/flowable/category/delete?id=' + id })
}
