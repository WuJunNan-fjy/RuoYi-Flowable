import request from '@/utils/request';
import { FlwModelQuery } from '@/types/flowable/model';

export type ModelVO = {
    id: number
    key: string
    name: string
}

/**
 * 获取流程模型列表
 * @param params
 */
export const getModelList = async (params: FlwModelQuery) => {
    return await request.get({ url: '/flowable/model/queryModelList', params })
}

/**
 * 创建流程模型
 * @param data
 */
export const createModel = async (data: ModelVO) => {
    return await request.post({ url: '/flowable/model/create', data })
}

